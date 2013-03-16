package ps7;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BrowserType;
import beans.SessionInfo;

/**
 * Servlet implementation class SessionHistory
 */
@WebServlet("/SessionHistory")
public class SessionHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute(session.getId(), CreateSessionInfo(session, request));
		request.getRequestDispatcher("/WEB-INF/views/SessionHistory.jsp").forward(request, response);
	}

	private SessionInfo CreateSessionInfo(HttpSession session, HttpServletRequest request) {
		return new SessionInfo(session.getId(), Calendar.getInstance(TimeZone.getTimeZone("GMT-07:00")), 
				request.getContextPath(), request.getRequestURI(), BrowserType.Unknown);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
