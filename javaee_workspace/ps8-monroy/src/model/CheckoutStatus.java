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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stmt = con.stmt;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray getCheckoutStatus(String...ids){
		String sql = getSqlString(ids);
		JSONArray status = new JSONArray();
		try {
			stmt.executeQuery(sql);
			ResultSet result = stmt.getResultSet();
			
			while (result.next()){
				JSONObject book = new JSONObject();
				book.put("id", result.getInt("SerialNumber"));
				book.put("status", result.getBoolean("Status"));
				status.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return status;
	}
	
	private String getSqlString(String...ids){
		String sql = "select Status from Checkout where SerialNumber in ";
		String serialNumbers = "(";
		for (String id : ids)
			serialNumbers += id + ",";
		
		serialNumbers.substring(0, serialNumbers.length());
		
		return sql + serialNumbers + ")";
	}
}
