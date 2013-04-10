package db;

public class PS10Database extends Connector{

	public PS10Database(String userName, String password, String url) throws Exception {
		super(userName, password, url);
	}
	
	public static PS10Database getConnection(){
		try {
			return new PS10Database("monroy_sw", "00733037", "jdbc:mysql://atr.eng.utah.edu/ps10_monroy");
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
}
