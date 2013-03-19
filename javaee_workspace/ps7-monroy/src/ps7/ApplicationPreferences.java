package ps7;

import helpers.ServletHelpers;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ApplicationPreferences
 */
@WebServlet("/ApplicationPreferences")
public class ApplicationPreferences extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelpers helper;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationPreferences() {
        super();
        helper = new ServletHelpers();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(true);

		helper.setApplicationPreference(request, session, context);
		helper.setPreference(request, session, context);
		request.getRequestDispatcher("/WEB-INF/views/ApplicationPreferences.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
