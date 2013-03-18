package helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import beans.SessionInfo;

import com.wagnerandade.coollection.query.order.Order;
import static com.wagnerandade.coollection.Coollection.*;

/**
 * @author Andres
 *
 */
public class AppHistory {

	private static ArrayList<SessionInfo> history = new ArrayList<>();
	
	/** Return an list of sessions with the given session Id ordered by the dateTime in with each request was made
	 * @param sessionId
	 * @return
	 */
	public static List<SessionInfo> getHistoryForSession(String sessionId){
		List<SessionInfo> list;
		synchronized (history){ 
			list = from(history).where("getSessionId", eq(sessionId)).orderBy("getDateTime", Order.ASC).all();
		}
		return list;
	}
	
	/** Return a list order by the dateTime of access for all requests made on the application
	 * @return
	 */
	public static List<SessionInfo> getApplicationHistory(){
		List<SessionInfo> list;
		synchronized (history){ 
			list = from (history).orderBy("getDateTime", Order.ASC).all();
		}
		return list;
	}
	
	/** Clear the application history of all session with the given session id.
	 * @param sessionId
	 */
	public static void clearSessionHistory(String sessionId){
		synchronized (history){
			List<SessionInfo> historyForSession = from(history).where("getSessionId", eq(sessionId)).all();
			history.removeAll(historyForSession);
		}
	}
	
	/** Clear the application history
	 * 
	 */
	public static void clearApplicationHsitory(){
		synchronized (history){
			history = new ArrayList<>();
		}
	}

	public static void addSession(SessionInfo session) {
		synchronized (history){
			history.add(session);
		}
	}
	
	public static int getCountDistinctBrowsers(){
		HashSet<String> browsers = new HashSet<>();
		synchronized (history){
			for (SessionInfo session : history)
				browsers.add(session.getBrowser());
		}
		return browsers.size();
	}
	
	public static int getCountDistinctSession(){
		HashSet<String> sessions = new HashSet<>();
		synchronized (history){
			for (SessionInfo session : history)
				sessions.add(session.getSessionId());
		}
		return sessions.size();
	}
}
