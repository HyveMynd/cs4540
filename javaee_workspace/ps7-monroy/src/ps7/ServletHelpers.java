package ps7;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletHelpers {

	public HttpServletResponse doRefresh(HttpServletRequest request,
			HttpServletResponse response) {
		String rate = request.getParameter("rate");
		
		if(rate == "no")
			return response;
		
		response.setHeader("Refresh", rate+";URL="+request.getRequestURL()+"?rate="+rate);
		return response;
	}
}
