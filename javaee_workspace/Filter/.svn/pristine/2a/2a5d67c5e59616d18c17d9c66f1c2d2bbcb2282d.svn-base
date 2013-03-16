package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Displays number of page requests made during the current session
 */
@WebServlet("/ShowCount")
public class ShowCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
		doPost(req, rsp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/count.jsp").forward(req, rsp);
	}

}
