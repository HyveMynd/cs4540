package db;

import java.sql.*;
import javax.servlet.http.*;
import javax.naming.*;
import javax.sql.*;

public class DbUtil {

	// This opens a connection by obtaining a DataSource that is configured
	// in WEB-INF/web.xml and META-INF/context.html.  It takes care of pooling
	// DB connections.
	
	public static Connection openConnection (HttpServlet servlet) throws SQLException {
		try {
			Context initContext = new InitialContext();
			Context envContext = 
					(Context) initContext.lookup("java:comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/LibraryDB");
			return ds.getConnection();
		}
		catch (NamingException e) {
			return null;
		}
	}
	
	public static void close (Connection db) {
		try {
			db.close();
		}
		catch (Exception e) {
		}
	}

	public static void commitAndClose (Connection db) {
		try {
			db.commit();
			db.close();
		}
		catch (Exception e) {
		}
	}

	public static void rollbackAndClose (Connection db) {
		try {
			db.rollback();
			db.close();
		}
		catch (Exception e) {
		}
	}
}