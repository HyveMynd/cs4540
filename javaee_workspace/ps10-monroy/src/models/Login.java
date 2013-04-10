package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import db.PS10Database;

public class Login {
	
	private PS10Database con;
	
	public Login(){
		con = PS10Database.getConnection();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject validateLogin (String login, String password){
		JSONObject result = new JSONObject();
		String sql = "select * from Users where Login=" + login + " and Password=" + password;
		
		try {
			con.stmt.executeQuery(sql);
			ResultSet results = con.stmt.getResultSet();
			
			while (results.next()){
				result.put("id", results.getInt("Id"));
				result.put("message", "Welcome " + results.getString("Name"));
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
			result.put("id", -1);
			result.put("message", "Login invalid. Please try again");
		}
		
		con.dispose();
		return result;
	}
	
}
