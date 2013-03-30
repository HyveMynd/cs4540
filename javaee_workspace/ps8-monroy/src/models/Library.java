package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import db.LibraryConnector;

public class Library {
	LibraryConnector con;

	public Library(){
		con = LibraryConnector.getConnection();
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
		
		con.dispose();
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
		
		con.dispose();
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
			con.stmt.executeQuery(sql);
			result = con.stmt.getResultSet();
		} catch (SQLException e) {e.printStackTrace();}
		return result;
	}

}
