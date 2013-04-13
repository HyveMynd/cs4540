package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import db.PS10Database;

public class Reviews {

	private PS10Database con;
	
	public Reviews(){
		con = PS10Database.getConnection();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getReview(String prodId) {
		JSONObject result = new JSONObject();
		JSONArray reviews = new JSONArray();
		String sql = "Select Rating, Id from Reviews where Prod_Id=" + prodId;
		
		try {
			con.stmt.executeQuery(sql);
			ResultSet results = con.stmt.getResultSet();
			
			while (results.next()){
				JSONObject review = new JSONObject();
				review.put("rating", results.getInt("Rating"));
				review.put("id", results.getInt("Id"));
				reviews.add(review);
			}
		} catch (SQLException e) {e.printStackTrace();}

		result.put("reviews", reviews);
		con.dispose();
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getReviewDesc(String revId) {
		JSONObject result = new JSONObject();
		String sql = "Select Review from Reviews where Id=" + revId;
		
		try {
			con.stmt.executeQuery(sql);
			ResultSet results = con.stmt.getResultSet();
			
			while (results.next()){
				result.put("review", results.getString("Review"));
			}
		} catch (SQLException e) {e.printStackTrace();}

		con.dispose();
		return result;
	}

	public JSONObject addReview(String prodId, String userId, String rating, String review) {
		JSONObject result = new JSONObject();
		String sql = "Insert into Reviews (User_Id, Prod_Id, Rating, Review) values ("+userId+","+prodId+","+rating+",'"+review+"')";
		
		try {
			con.stmt.execute(sql);

		} catch (SQLException e) {e.printStackTrace();}

		con.dispose();
		return result;
	}

}
