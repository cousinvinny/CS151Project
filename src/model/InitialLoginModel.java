package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.SqliteConnection;

public class InitialLoginModel {
	private static boolean isInitialLogin;
	Connection connection;

	public InitialLoginModel() {
		connection = SqliteConnection.Connector();
		if (connection == null) {
			System.out.println("Connection not successful");
			System.exit(1);
		}
	}

	public void setInitialLogin(boolean isInitialLogin) {
		InitialLoginModel.isInitialLogin = isInitialLogin;
	}
	
	public boolean isInitialLogin() {
		return isInitialLogin;
	}
	
	public boolean isFirstTimeLogin(String pass) throws SQLException {

		isInitialLogin = true;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from default_password where password = ?";
		String insert = "INSERT INTO current_password (password) VALUES (?)";
		try {
			preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setString(1, pass);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, pass);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
}
