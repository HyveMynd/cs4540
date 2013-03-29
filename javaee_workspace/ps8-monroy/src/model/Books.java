package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	
	@SuppressWarnings("unchecked")
	public JSONArray getBooks(int offset, String filter, String order){
		String sql = getSqlString(order, filter, offset);
		JSONArray books = new JSONArray();
		try {
			stmt.executeQuery(sql);
			ResultSet result = stmt.getResultSet();
			
			while (result.next()){
				JSONObject book = new JSONObject();
				book.put("title", result.getString("Title"));
				book.put("author", result.getString("Author"));
				book.put("id", result.getString("SerialNumber"));
				books.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return books;
	}
	
	private String getSqlString(String order, String filter, int offset){
		String sql;
		if (order.compareToIgnoreCase("title") == 0)
			sql = "select * from books where Title like '%"+ filter +"%' order by Title, Author limit 10 offset "+ offset*10;
		else
			sql = "select * from books where Author like '%"+ filter +"%' order by Author, Title limit 10 offset "+ offset*10;
		return sql;
	}
}
