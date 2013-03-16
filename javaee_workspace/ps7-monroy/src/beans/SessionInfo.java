package beans;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionInfo {
	private String sessionId;
	private Date dateTime;
	private String url;
	private String fullUrl;
	private String browser;
	
	public SessionInfo (String sid, Date datetime, String url, 
			String fullUrl, String browser){
		this.sessionId = sid;
		this.dateTime = datetime;
		this.url = url;
		this.fullUrl = fullUrl;
		this.browser = browser;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionInfo other = (SessionInfo) obj;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}
	
	public static SessionInfo CreateSessionInfo(HttpSession session, HttpServletRequest request) {
		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("GMT-07:00"));
		SessionInfo sesh = new SessionInfo(session.getId(), today.getTime(), 
				request.getRequestURI(), request.getRequestURL().toString(), request.getHeader("user-agent"));
		return 	sesh;
	}
}
