package db;

import java.sql.*;

public class Connector {
	public Connection con;
	public Statement stmt;
	public Connector(String userName, String password, String url) throws Exception {
		try{
//			 	String userName = "cs5530u64";
//		   		String password = "u3q38idq";
//	        	String url = "jdbc:mysql://georgia.eng.utah.edu/cs5530db64";
		        Class.forName ("com.mysql.jdbc.Driver").newInstance ();
        		con = DriverManager.getConnection (url, userName, password);

			stmt = con.createStatement();
        } catch(Exception e) {
			System.err.println("Unable to open mysql jdbc connection. The error is as follows,\n");
            		System.err.println(e.getMessage());
			throw(e);
		}
	}
	
	public void finalize() throws Exception{
		con.close();
	}
}
