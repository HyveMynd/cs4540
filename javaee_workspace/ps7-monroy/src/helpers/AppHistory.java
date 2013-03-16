package helpers;

import java.util.ArrayList;
import java.util.List;
import com.wagnerandade.coollection.query.order.Order;
import static com.wagnerandade.coollection.Coollection.*;

/**
 * @author Andres
 *
 */
public class AppHistory {
	private static ArrayList<SessionInfo> history = new ArrayList<>();
	
	public static void addSession(SessionInfo session){
		history.add(session);
	}
	
	/** Return an list of sessions with the given session Id ordered by the dateTime in with each request was made
	 * @param sessionId
	 * @return
	 */
	public static List<SessionInfo> getHistoryForSession(String sessionId){
		List<SessionInfo> list = from(history).where("getSessionId", eq(sessionId)).orderBy("getDateTime", Order.DESC).all();
		return list;
	}
	
	/** Return a list order by the dateTime of access for all requests made on the application
	 * @return
	 */
	public static List<SessionInfo> getApplicationHistory(){
		return from (history).orderBy("getDateTime", Order.DESC).all();
	}
	
	/** Clear the application history of all session with the given session id.
	 * @param sessionId
	 */
	public static void clearSessionHistory(String sessionId){
		List<SessionInfo> historyForSession = from(history).where("getSessionId", eq(sessionId)).all();
		history.removeAll(historyForSession);
	}
	
	/** Clear the application history
	 * 
	 */
	public static void clearApplicationHsitory(){
		history = new ArrayList<>();
	}
}
