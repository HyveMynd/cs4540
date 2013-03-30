package models;

import java.sql.SQLException;

import org.json.simple.JSONObject;

import db.PatronsConnector;

public class Checkout {
	private PatronsConnector con;
	
	public Checkout (){
		con = PatronsConnector.getConnection();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject checkoutBook(String id, String bookId) {
		String sql = "INSERT INTO Checkout (SerialNumber, CheckedOut, PatronId) VALUES ("+bookId+",1,"+id+") ON DUPLICATE KEY UPDATE CheckedOut=1";
		JSONObject result = new JSONObject();
		try {
			con.stmt.execute(sql);
			result.put("status", true);
			result.put("bookId", bookId);
		} catch (SQLException e) {e.printStackTrace();}
		
		con.dispose();
		return result;
	}
	
}
