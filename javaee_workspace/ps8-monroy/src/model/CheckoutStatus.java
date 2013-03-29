package model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import db.Connector;

public class CheckoutStatus {
	private Connector con;
	private Statement stmt;
	
	public CheckoutStatus(){
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
			stmt.executeQuery(sql);
			ResultSet result = stmt.getResultSet();
			
			while (result.next()){
				status.add(result.getBoolean("CheckedOut"));
			}
			
		} catch (SQLException e) { e.printStackTrace();}
		
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
}
