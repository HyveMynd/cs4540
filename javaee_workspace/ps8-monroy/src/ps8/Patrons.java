package ps8;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PatronCheckout;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class GetCheckoutStatus
 */
@WebServlet("/Patrons")
public class Patrons extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Patrons() {
        super();
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
		PatronCheckout patrons = new PatronCheckout();
		
		// Turn off caching and grab the incoming prefix parameter
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
				
		String login = request.getParameter("login");
		int register = Integer.parseInt(request.getParameter("register"));
		
		JSONObject results = new JSONObject();
		switch (register){
		case 0:
			results = patrons.loginPatron(login);
			break;
		case 1:
			results = patrons.registerPatron(login);
			break;
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("id", login);
		results.put("id", login);
		patrons.dispose();
		
		// Send back the result as an HTTP response
		response.setContentType("application/json");
		response.getWriter().print(results);
		response.getWriter().close();
	}

}
