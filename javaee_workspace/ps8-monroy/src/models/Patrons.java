package models;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import db.PatronsConnector;

public class Patrons {
	private PatronsConnector con;
	
	public Patrons(){
		con = PatronsConnector.getConnection();
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
		
		con.dispose();
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
		
		con.dispose();
		return patron;
	}
	
	private ResultSet execute(String sql){
		ResultSet result = null;
		try {
			con.stmt.executeQuery(sql);
			result = con.stmt.getResultSet();
		} catch (SQLException e) {e.printStackTrace();}
		
		return result;
	}
	
	public JSONObject registerPatron(String login) {
		String sql = "insert into Patrons (LibraryId) values ("+login+")";
		try{
			con.stmt.execute(sql);
			
		} catch (SQLException e){}
		
		con.dispose();
		return loginPatron(login);
	}

	@SuppressWarnings("unchecked")
	public JSONObject getBooksForPatron(int patronId) {
		String sql = "select SerialNumber from Checkout where PatronId =" + patronId + " and Checkedout=1";
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

		con.dispose();
		return books;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAllPatrons() {
		String sql = "select * from Patrons order by Name asc";
		JSONArray patrons = new JSONArray();
		JSONObject ret = new JSONObject();
		ResultSet results = execute(sql);
		
		try {
			while (results.next()){
				JSONObject patron = new JSONObject();
				patron.put("patronName", results.getString("Name"));
				patron.put("id", results.getInt("LibraryId"));
				patrons.add(patron);
			}
		}catch(Exception e){e.printStackTrace();}
		ret.put("patrons", patrons);
		
		con.dispose();
		return ret;
	}

	
	

}
