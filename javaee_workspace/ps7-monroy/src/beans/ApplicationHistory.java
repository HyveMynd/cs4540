package beans;

import java.util.ArrayList;
import java.util.Collection;

public class ApplicationHistory {
	private ArrayList<SessionInfo> sessions;
	
	public ApplicationHistory() {
		super();
		sessions = new ArrayList<SessionInfo>();
	}
}
