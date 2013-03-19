package ps7;

import helpers.ServletHelpers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionPreferences
 */
@WebServlet("/SessionPreferences")
public class SessionPreferences extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelpers helper;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionPreferences() {
        super();
        helper = new ServletHelpers();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		helper.setPreference(request, session, getServletContext());
		request.getRequestDispatcher("/WEB-INF/views/SessionPreferences.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		helper.setSessionPreferences(request, session);
		helper.setPreference(request, session, getServletContext());
		request.getRequestDispatcher("/WEB-INF/views/SessionPreferences.jsp").forward(request, response);
	}

}
