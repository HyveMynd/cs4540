package filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import beans.AppHistory;
import beans.SessionInfo;

/**
 * This is a filter.  When a page request is made of this application, Tomcat will
 * call the doFilter method before processing the request.  
 */
@WebFilter(urlPatterns = {"/*"})
public class Logger implements Filter {

	/**
	 * Updates a session variable that keeps track of the number
	 * of page requests that have been made
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// Cast the request to HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;
		
		/*************************************************************/
		// When you modify this method to suit your purposes, change
		// only the code in this block.
		
		AppHistory.addSession(SessionInfo.CreateSessionInfo(req.getSession(), req));

		/*************************************************************/
		
		// Pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	public void destroy() {
	}

}
