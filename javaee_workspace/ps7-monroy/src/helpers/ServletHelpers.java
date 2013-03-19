package helpers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Preference;

public class ServletHelpers {

	public HttpServletResponse doRefresh(HttpServletRequest request,
			HttpServletResponse response) {
		String rate = request.getParameter("rate");
		
		if(rate == "no")
			return response;
		
		response.setHeader("Refresh", rate+";URL="+request.getRequestURL()+"?rate="+rate);
		return response;
	}
	
	public void setSessionPreferences(HttpServletRequest request, HttpSession session){
		if (request.getParameter("bgColor") != null){
			session.setAttribute("bgColor", request.getParameter("bgColor"));
			session.setAttribute("fontColor", request.getParameter("fontColor"));
			session.setAttribute("fontSize", request.getParameter("fontSize"));
		}
	}

	public void setApplicationPreference(HttpServletRequest request, HttpSession session, ServletContext context) {
		if (request.getParameter("bgColor") != null){
			context.setAttribute("bgColor", request.getParameter("bgColor"));
			context.setAttribute("fontColor", request.getParameter("fontColor"));
			context.setAttribute("fontSize", request.getParameter("fontSize"));
		}
	}
	
	public void setPreference(HttpServletRequest request, HttpSession session, ServletContext context){
		Preference pref = getPreference(request, session, context);
		if (pref != null)
			request.setAttribute("preferences", pref);
	}
	
	private Preference getPreference(HttpServletRequest request, HttpSession session, ServletContext context) {
		// Satisfy a request first
		Preference pref = getPreferenceFromParameters(request);
		
		// No request, get from session
		if(pref.getBgColor() == null){
			pref = getPreferenceFromSession(session);
		}
		
		// No session, get from application
		if(pref.getBgColor() == null){
			pref = getPreferenceFromContext(context);
		}
		return pref;
	}
	
	private Preference getPreferenceFromParameters(HttpServletRequest request){
		String bgColor = request.getParameter("bgColor");
		String fontColor = request.getParameter("fontColor");
		String fontSize = request.getParameter("fontSize");
		return Preference.CreatePreference(bgColor, fontColor, fontSize);
	}
	
	private Preference getPreferenceFromSession(HttpSession session){
		String bgColor = (String) session.getAttribute("bgColor");
		String fontColor = (String) session.getAttribute("fontColor");
		String fontSize = (String) session.getAttribute("fontSize");
		return Preference.CreatePreference(bgColor, fontColor, fontSize);
	}
	
	private Preference getPreferenceFromContext(ServletContext context) {
		String bgColor = (String) context.getAttribute("bgColor");
		String fontColor = (String) context.getAttribute("fontColor");
		String fontSize = (String) context.getAttribute("fontSize");
		return Preference.CreatePreference(bgColor, fontColor, fontSize);
	}
	
}
