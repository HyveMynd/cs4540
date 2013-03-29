package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import db.Connector;

public class Library {
	private Connector libraryCon;
	private Statement stmt;
	
	public Library(){
		try {
			libraryCon = new Connector("library", "library", 
					"jdbc:mysql://atr.eng.utah.edu/collection");
		} catch (Exception e) {
			e.printStackTrace();
		}
		stmt = libraryCon.stmt;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getBooks(JSONArray ids){
		JSONArray books = new JSONArray();
		JSONObject ret = new JSONObject();
		String sql = "select * from books where SerialNumber in (";
		
		for (int i = 0; i < ids.size(); i++){
			sql += ids.get(i) + ",";
		}
		
		sql = sql.substring(0, sql.length()-1) + ")";
		ResultSet results = execute(sql);
		try {
			books = fillWithBooks(results);
		} catch (SQLException e) {e.printStackTrace();}
		ret.put("books", books);
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	private JSONArray fillWithBooks(ResultSet result) throws SQLException{
		JSONArray books = new JSONArray();
		while (result.next()){
			JSONObject book = new JSONObject();
			book.put("title", result.getString("Title"));
			book.put("author", result.getString("Author"));
			book.put("id", result.getString("SerialNumber"));
			books.add(book);
		}
		return books;
	}
	
	public JSONArray getBooks(int offset, String filter, String order){
		String sql = getSqlString(order, filter, offset);
		JSONArray books = new JSONArray();
		try {
			ResultSet result = execute(sql);	
			books = fillWithBooks(result);
			
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
	
	private ResultSet execute(String sql){
		ResultSet result = null;
		try {
			stmt.executeQuery(sql);
			result = stmt.getResultSet();
		} catch (SQLException e) {e.printStackTrace();}
		return result;
	}
	
	public void dispose(){
		try {
			stmt.close();
			libraryCon.close();
		} catch (Exception e) {e.printStackTrace();}
	}
}
