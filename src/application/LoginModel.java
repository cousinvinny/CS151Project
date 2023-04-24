package application;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginModel {
Connection connection;
	
	public LoginModel() {
		connection = SqliteConnection.Connector();
		if(connection == null)
			System.exit(1);
	}
	
	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
