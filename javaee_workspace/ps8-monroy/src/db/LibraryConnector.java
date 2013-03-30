package db;

public class LibraryConnector extends Connector {

	public LibraryConnector(String userName, String password, String url)
			throws Exception {
		super(userName, password, url);
		// TODO Auto-generated constructor stub
	}
	
	public static LibraryConnector getConnection(){
		try {
			return new LibraryConnector("library", "library", "jdbc:mysql://atr.eng.utah.edu/collection");
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
}
