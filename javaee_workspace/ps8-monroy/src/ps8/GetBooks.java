package ps8;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Books;
import model.CheckoutStatus;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class GetBooks
 */
@WebServlet("/GetBooks")
public class GetBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Books lib;
    private CheckoutStatus checkout;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBooks() {
        super();
		lib = new Books();
        checkout = new CheckoutStatus();
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		int action = Integer.parseInt(request.getParameter("action"));

		// Get offset from session storage
		Object os = session.getAttribute("offset");
		if (os == null){
			session.setAttribute("offset", 0);
			os = 0;
		}
		
		int offset = (int)os;
		
		switch (action){
		case -1:
			if (offset > 0)
				offset--;
			session.setAttribute("offset", offset);
			break;
		case 0:
			offset = 0;
			session.setAttribute("offset", offset);
			break;
		case 1:
			offset++;
			session.setAttribute("offset", offset);
			break;
		}
		
		
		// Turn off caching and grab the incoming prefix parameter
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		String filter = request.getParameter("filter");
		String order = request.getParameter("order");
		if (order == null) order = "title";
		if (filter == null) filter = "";
				
		JSONObject result = new JSONObject();
		JSONArray books = checkout.getCheckoutStatus(lib.getBooks(offset, filter, order));
		result.put("books", books);
		
		// Place information the size of the list
		boolean atTop = (offset == 0);
		boolean atBottom = (books.size() < 10);
		result.put("atTop", atTop);
		result.put("atBottom", atBottom);
		
		// Send back the result as an HTTP response
		response.setContentType("application/json");
		response.getWriter().print(result);
		response.getWriter().close();
	}

}
