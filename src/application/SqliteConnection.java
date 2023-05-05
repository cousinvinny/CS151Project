package application;
import java.sql.*;

/*
 * Database connection class
 */
public class SqliteConnection {
	public static Connection Connector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:InitialDataDB.db");
			return conn;
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
