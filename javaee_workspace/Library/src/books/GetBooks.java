package books;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

import db.Utils;


@WebServlet("/GetBooks")
public class GetBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Defer to doPost
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Returns JSON object of the form {atTop: true/false, atBottom: true/false, books: [...]}
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {

		// Turn off caching and grab the incoming prefix parameter
		rsp.setHeader("Cache-Control", "no-cache");
		rsp.setHeader("Pragma", "no-cache");
		
		// Get the value of the offset parameter
		int offset = 0;
		try {
			offset = Integer.parseInt(req.getParameter("offset"));
		}
		catch (NumberFormatException e) {			
		}
		
		// Get the value of the filter parameter
		String filter = req.getParameter("filter");
		if (filter == null) filter = "";
		
		// Prepare to extract books from library
		Connection db = null;
		JSONArray books = new JSONArray();
		boolean atTop = (offset == 0);
		boolean atBottom = true;
				
		try {
			
			// Open a connection
			db = Utils.openConnection(this);
			
			// Get the next 6 books.  We only return 5 of them, but this will tell us
			// if we're at the bottom.
			String query = "select Title from books where Title like ? order by Title limit 6 offset ?";
			PreparedStatement stmt = db.prepareStatement(query);
			stmt.setString(1, "%" + filter + "%");
			stmt.setInt(2, offset*5);
			ResultSet results = stmt.executeQuery();
			
			// Put the first five results into the array.  If there's a sixth, we're
			//  not yet at the bottom.
			int count = 0;
			while (results.next()) {
				count++;
				if (count > 5) {
					atBottom = false;
				}
				else {
					books.put(results.getString("Title"));
				}
			}
			
			// Clean up
			results.close();
			stmt.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
		finally {
			Utils.close(db);
		}
		
		// Create a result object
		JSONObject result = new JSONObject();
		try {
			result.put("atTop", atTop);
			result.put("atBottom", atBottom);
			result.put("books", books);
			result.put("newOffset", offset);
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
