package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.SqliteConnection;

public class ResetPWModel {
	Connection connection;

	public ResetPWModel() {
		connection = SqliteConnection.Connector();
		if (connection == null) {
			System.out.println("Connection not successful");
			System.exit(1);
		}
	}
	
	/*
	 * When user changes their password, will delete the current password and replace with the new password
	 */
	public boolean resetPW(String pass) throws SQLException {
		PreparedStatement preparedStatement = null;
		String delete = "DELETE FROM current_password";
		String insert = "INSERT INTO current_password (password) VALUES (?)";
		try {
			preparedStatement = connection.prepareStatement(delete);
	        preparedStatement.executeUpdate();
	        preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setString(1, pass);
			int rowsAffected = preparedStatement.executeUpdate();

			if(rowsAffected > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
		}
	}
}
