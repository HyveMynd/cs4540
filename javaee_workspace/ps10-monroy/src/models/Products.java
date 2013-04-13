package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import db.PS10Database;

public class Products {

	private PS10Database con;
	
	public Products(){
		con = PS10Database.getConnection();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getAllProducts() {
		JSONArray products = new JSONArray();
		JSONObject result = new JSONObject();
		String sql = "select Id, Brand, Name, Quantity from Products";
		
		try {
			con.stmt.executeQuery(sql);
			ResultSet results = con.stmt.getResultSet();
			
			while (results.next()){
				JSONObject product = new JSONObject();
				product.put("id", results.getInt("Id"));
				product.put("brand", results.getString("Brand"));
				product.put("name", results.getString("Name"));
				product.put("quantity", results.getInt("Quantity"));
				products.add(product);
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		result.put("products", products);
		
		con.dispose();
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getSpecs(String id) {
		JSONObject result = new JSONObject();
		String sql = "select Specifications from Products where Id=" + id;
		
		try {
			con.stmt.executeQuery(sql);
			ResultSet results = con.stmt.getResultSet();
			
			while (results.next()){
				result.put("specs", results.getString("Specifications"));
			}
		} catch (SQLException e) {e.printStackTrace();}
				
		con.dispose();
		return result;
	}

	public JSONObject addProduct(String brand, String name, String quantity) {
		JSONObject result = new JSONObject();
		String sql = "insert into Products (Brand, Name, Quantity) values ('"+brand+"','"+name+"',"+quantity+")";
		
		try {
			con.stmt.execute(sql);
			
		} catch (SQLException e) {e.printStackTrace();}
				
		con.dispose();
		return result;	
	}

	@SuppressWarnings("unchecked")
	public JSONObject getCartProducts(String[] cart) {
		JSONArray products = new JSONArray();
		JSONObject result = new JSONObject();
		String sql = "select Id, Brand, Name from Products where Id IN ("+Arrays.toString(cart).replace('[', ' ').replace(']', ' ')+")";
		
		try {
			con.stmt.executeQuery(sql);
			ResultSet results = con.stmt.getResultSet();
			
			while (results.next()){
				JSONObject product = new JSONObject();
				product.put("id", results.getInt("Id"));
				product.put("brand", results.getString("Brand"));
				product.put("name", results.getString("Name"));
				products.add(product);
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		result.put("products", products);
		
		con.dispose();
		return result;
	}

	public JSONObject checkoutProducts(String[] cart) {
		JSONObject result = new JSONObject();
		String sql = "update Products set Quantity= Quantity - 1 where Id IN ("+Arrays.toString(cart).replace('[', ' ').replace(']', ' ')+")";
		
		try {
			con.stmt.execute(sql);
			
		} catch (SQLException e) {e.printStackTrace();}
				
		con.dispose();
		return result;
	}

}
