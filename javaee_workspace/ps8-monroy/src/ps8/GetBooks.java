package ps8;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Books;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class GetBooks
 */
@WebServlet("/GetBooks")
public class GetBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Turn off caching and grab the incoming prefix parameter
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		Books lib = new Books();
		int offset = 0;
		String filter  = request.getParameter("filter");
		if (filter == null) filter = "";
		
		try{
			offset = Integer.parseInt(request.getParameter("offset"));
		} catch(Exception e){ };
		
		JSONObject result = new JSONObject();
		for (String title : lib.getBooks(offset, filter)){
			result.put("books", title);
		}
		
		// Send back the result as an HTTP response
		response.setContentType("application/json");
		response.getWriter().print(result);
		response.getWriter().close();
	}

}
