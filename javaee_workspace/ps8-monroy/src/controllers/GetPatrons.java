package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Patrons;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class GetPatrons
 */
@WebServlet("/GetPatrons")
public class GetPatrons extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPatrons() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Patrons patron = new Patrons();
		
		// Turn off caching and grab the incoming prefix parameter
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		JSONObject results = patron.getAllPatrons();
		
		// Send back the result as an HTTP response
		response.setContentType("application/json");
		response.getWriter().print(results);
		response.getWriter().close();		
	}

}
