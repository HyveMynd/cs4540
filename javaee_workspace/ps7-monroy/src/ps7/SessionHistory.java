package ps7;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import helpers.AppHistory;

/**
 * Servlet implementation class SessionHistory
 */
@WebServlet("/SessionHistory")
public class SessionHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelpers helper;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionHistory() {
        super();
        helper = new ServletHelpers();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		String rate = request.getParameter("rate");
		if (rate != null)
			response = helper.doRefresh(request, response);
		
		request.setAttribute("sessionHistory", AppHistory.getHistoryForSession(session.getId()));
		request.getRequestDispatcher("/WEB-INF/views/SessionHistory.jsp").forward(request, response);
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
			AppHistory.clearSessionHistory(request.getSession().getId());
			break;
		}
		
		request.setAttribute("sessionHistory", AppHistory.getHistoryForSession(request.getSession().getId()));
		request.getRequestDispatcher("/WEB-INF/views/SessionHistory.jsp").forward(request, response);
	}
	
}
