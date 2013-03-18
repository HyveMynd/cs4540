package ps7;

import helpers.AppHistory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApplicationHistory
 */
@WebServlet("/ApplicationHistory")
public class ApplicationHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelpers helper;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationHistory() {
        super();
        helper = new ServletHelpers();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true);

		String rate = request.getParameter("rate");
		if (rate != null)
			response = helper.doRefresh(request, response);
		
		
		request = setAllAttributes(request);
		request.getRequestDispatcher("/WEB-INF/views/ApplicationHistory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String submit = request.getParameter("submit");

		String rate = request.getParameter("rate");
		if (rate != null)
			response = helper.doRefresh(request, response);
		switch(submit){
		case "Refresh":
			response = helper.doRefresh(request, response);
			break;
		case "Reset":
			AppHistory.clearApplicationHsitory();
			break;
		}
		
		request = setAllAttributes(request);
		request.getRequestDispatcher("/WEB-INF/views/ApplicationHistory.jsp").forward(request, response);	
	}
	
	private HttpServletRequest setAllAttributes(HttpServletRequest request) {
		request.setAttribute("applicationHistory", AppHistory.getApplicationHistory());
		request.setAttribute("browsers", AppHistory.getCountDistinctBrowsers());
		request.setAttribute("uniqueSessions", AppHistory.getCountDistinctSession());
		return request;
	}
	
}

