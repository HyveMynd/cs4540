package model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import db.Connector;

public class PatronCheckout {
	private Connector con;
	private Statement stmt;
	
	public PatronCheckout(){
		try {
			con = new Connector("monroy", "00733037", "jdbc:mysql://atr.eng.utah.edu/ps8_monroy");
		} catch (Exception e) {e.printStackTrace();}
		stmt = con.stmt;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray getCheckoutStatus(JSONArray books){
		String sql = getSqlString(books);
		JSONArray status = new JSONArray();
		try {
			ResultSet result = execute(sql);
			
			while (result.next()){
				status.add(result.getBoolean("CheckedOut"));
			}
			
		} catch (SQLException e) { e.printStackTrace();}
		finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return addCheckoutStatusToBooks(books, status);
	}
	
	private String getSqlString(JSONArray books){
		String sql = "select CheckedOut from Checkout where SerialNumber in ";
		String serialNumbers = "(";
		for (Object book : books){
			JSONObject b = (JSONObject)book;
			String id = (String)b.get("id");
			serialNumbers += id + ",";
		}
		serialNumbers = serialNumbers.substring(0, serialNumbers.length()-1);
		
		return sql + serialNumbers + ")";
	}
	
	@SuppressWarnings("unchecked")
	private JSONArray addCheckoutStatusToBooks(JSONArray books, JSONArray status){	
		if (status.size() < 1)
			return books;
		
		for (int i = 0; i < status.size(); i++){
			JSONObject book = (JSONObject)books.get(i);
			book.put("checkedOut", status.get(i));
		}
		
		return books;
	}

	@SuppressWarnings("unchecked")
	public JSONObject loginPatron(String login) {
		String sql = "select LibraryId from Patrons where LibraryId=" + login;
		JSONObject patron = new JSONObject();
		try{
			ResultSet result = execute(sql);
			
			while (result.next()){
				patron.put("id", result.getString("LibraryId"));
			}
			
		} catch (SQLException e){}
		
		if (patron.size() < 1){
			patron.put("message", "Failed to login. Check library id.");
			patron.put("status", false);
		}
		else{
			patron.put("message", "Welcome! Library Id: " + patron.get("id"));
			patron.put("status", true);
		}
		
		return patron;
	}
	
	private ResultSet execute(String sql){
		ResultSet result = null;
		try {
			stmt.executeQuery(sql);
			result = stmt.getResultSet();
		} catch (SQLException e) {e.printStackTrace();}
		
		return result;
	}
	
	public JSONObject registerPatron(String login) {
		String sql = "insert into Patrons (LibraryId) values ("+login+")";
		try{
			stmt.execute(sql);
			
		} catch (SQLException e){}
		
		return loginPatron(login);
	}
	
	public void dispose(){
		try {
			stmt.close();
			con.close();
		} catch (Exception e) {e.printStackTrace();}
	}

	@SuppressWarnings("unchecked")
	public JSONObject checkoutBook(String id, String bookId) {
		String sql = "INSERT INTO Checkout (SerialNumber, CheckedOut, PatronId) VALUES ("+bookId+",1,"+id+") ON DUPLICATE KEY UPDATE CheckedOut=1";
		JSONObject result = new JSONObject();
		try {
			stmt.execute(sql);
			result.put("status", true);
			result.put("bookId", bookId);
		} catch (SQLException e) {e.printStackTrace();}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getBooksForPatron(int patronId) {
		String sql = "select SerialNumber from Checkout where PatronId =" + patronId;
		JSONArray ids = new JSONArray();
		ResultSet results = execute(sql);
		
		try {
			while (results.next()){
				ids.add(results.getInt("SerialNumber"));
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		Library lib = new Library();
		JSONObject books = new JSONObject();
		if (ids.size() > 0)
			books = lib.getBooks(ids);
		lib.dispose();
		return books;
	}

	@SuppressWarnings("unchecked")
	public JSONObject checkInBook(String patronId, String bookId) {
		JSONObject result = new JSONObject();
		
		String sql = "update Checkout set CheckedOut=0 where PatronId=" + patronId + " and SerialNumber=" + bookId;
		
		try {
			stmt.execute(sql);
			result.put("status", true);
		} catch (SQLException e) {e.printStackTrace();}
		
		return result;
	}
	

}
