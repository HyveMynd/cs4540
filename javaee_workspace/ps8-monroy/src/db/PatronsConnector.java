package db;


public class PatronsConnector extends Connector {

	private PatronsConnector(String userName, String password, String url)
			throws Exception {
		super(userName, password, url);
	}

	public static PatronsConnector getConnection(){
		try {
			return new PatronsConnector("monroy", "00733037", "jdbc:mysql://atr.eng.utah.edu/ps8_monroy");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
