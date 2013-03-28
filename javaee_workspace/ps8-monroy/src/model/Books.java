package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.Connector;

public class Books {
	private Connector libraryCon;
	private Statement stmt;
	
	public Books(){
		try {
			libraryCon = new Connector("library", "library", 
					"jdbc:mysql://atr.eng.utah.edu/collection");
		} catch (Exception e) {
			e.printStackTrace();
		}
		stmt = libraryCon.stmt;
	}
	
	public ArrayList<String> getBooks(int offset, String filter){
		String sql = "select Title from books where Title like '%"+ filter +"%' order by Title limit 10 offset "+ offset*10;
		ArrayList<String> titles = new ArrayList<>();
		try {
			stmt.executeQuery(sql);
			ResultSet result = stmt.getResultSet();
			
			while (result.next())
				titles.add(result.getString("Title"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return titles;
	}
}
