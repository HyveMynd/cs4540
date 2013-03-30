package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Checkin;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class Checkin
 */
@WebServlet("/CheckinBooks")
public class CheckinBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckinBooks() {
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
		Checkin checkin = new Checkin();
		
		// Turn off caching and grab the incoming prefix parameter
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
				
		String patronId = request.getParameter("id");
		String bookId = request.getParameter("bookId");
		
		JSONObject results = checkin.checkInBook(patronId, bookId);
		
		// Send back the result as an HTTP response
		response.setContentType("application/json");
		response.getWriter().print(results);
		response.getWriter().close();	}

}
