package models;

import java.sql.SQLException;

import org.json.simple.JSONObject;

import db.PatronsConnector;

public class Checkin {
	private PatronsConnector con;
	
	public Checkin (){
		con = PatronsConnector.getConnection();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject checkInBook(String patronId, String bookId) {
		JSONObject result = new JSONObject();
		
		String sql = "update Checkout set CheckedOut=0 where PatronId=" + patronId + " and SerialNumber=" + bookId;
		
		try {
			con.stmt.execute(sql);
			result.put("status", true);
		} catch (SQLException e) {e.printStackTrace();}
		
		con.dispose();
		return result;
	}
}
