package ps7;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.AppHistory;
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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(true);
		
		synchronized (context){
			// Get the Application history
			@SuppressWarnings("unchecked")
			ArrayList<SessionInfo> appHistory = 
			    (ArrayList<SessionInfo>) context.getAttribute("history");
			
			// Create the history if there was not one
			if (appHistory == null){
				appHistory = new ArrayList<>();
				context.setAttribute("history", appHistory);
			}
			
			// Add the history
			appHistory.add(SessionInfo.CreateSessionInfo(session, request));
		}
		
		request.setAttribute("sessionHistory", AppHistory.getHistoryForSession(session.getId()));
		request.getRequestDispatcher("/WEB-INF/views/SessionHistory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
