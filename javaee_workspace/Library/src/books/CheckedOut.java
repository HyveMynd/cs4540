package books;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import db.Utils;


public class CheckedOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Defer to doGet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Produces a JSON string representing {patron: "name", books: [...]}
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {

		// Turn off caching
		rsp.setHeader("Cache-Control", "no-cache");
		rsp.setHeader("Pragma", "no-cache");
		
		// Grab the incoming idNumber
		String idNumber = req.getParameter("idNumber");
		if (idNumber == null) idNumber = "";
		
		// Prepare to query the DB for the patron name and the books that are checked out
		Connection db = null;
		JSONArray books = new JSONArray();
		String patron = "";
		
		try {
			
			// Open a connection and start a transaction
			db = Utils.openConnection(this);
			db.setAutoCommit(false);
			
			// Find out the patron's name
			String query = "select Name from clients where CardNumber=?";
			PreparedStatement stmt = db.prepareStatement(query);
			stmt.setString(1, idNumber);
			ResultSet results = stmt.executeQuery();
			patron = (results.next()) ? results.getString("Name") : "Unknown patron";
			results.close();
			stmt.close();
			
			// Find all the books
			query = "select Title from checkedout, books where " +
			               "books.SerialNumber = checkedout.SerialNumber and " +
			               "checkedout.CardNumber = ?";			
			stmt = db.prepareStatement(query);
			stmt.setString(1, idNumber);
			results = stmt.executeQuery();
			
			// Populate a JSON array with the books
			while (results.next()) {
				books.put(results.getString("Title"));
			}
			results.close();
			stmt.close();
			
		}
		catch (Exception e) {
			Utils.rollbackAndClose(db);
			e.printStackTrace();
			return;
		}
		finally {
			Utils.commitAndClose(db);
		}

		// Create an object with two fields
		JSONObject result = new JSONObject();
		try {
			result.put("patron", patron);
			result.put("books", books);
		}
		catch (JSONException e) {
			e.printStackTrace();
		}

		// Send back the result as an HTTP response
		rsp.setContentType("application/json");
		rsp.getWriter().print(result);
		rsp.getWriter().close();

	}

}
