package models;

import java.sql.SQLException;

import org.json.simple.JSONObject;

import db.PS10Database;

public class Register {

	private PS10Database con;
	
	public Register(){
		con = PS10Database.getConnection();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject registerUser(String login, String password, String name) {
 		String sql = "insert into Users (Login, Password, Name, Role) values ('" + login + "','" + password + "','" + name + "','user')";
		JSONObject results = new JSONObject();
		
		try {
			con.stmt.execute(sql);
		}catch (SQLException e) 
		{
			e.printStackTrace();
			results.put("message", "Registration failed. Please try again.");
			results.put("id", -1);
		}
		
		con.dispose();
		// Return if an error existed
		if (results.size() > 0)
			return results;
		else
			return new Login().validateLogin(login, password);
	}

}
