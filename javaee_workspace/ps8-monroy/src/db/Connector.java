package db;

import java.sql.*;

public class Connector {
	public Connection con;
	public Statement stmt;
	public Connector(String userName, String password, String url) throws Exception {
		try{
		        Class.forName ("com.mysql.jdbc.Driver").newInstance ();
        		con = DriverManager.getConnection (url, userName, password);

			stmt = con.createStatement();
        } catch(Exception e) {
			System.err.println("Unable to open mysql jdbc connection. The error is as follows,\n");
            		System.err.println(e.getMessage());
			throw(e);
		}
	}
	
	public void close() throws Exception{
		con.close();
	}
}
