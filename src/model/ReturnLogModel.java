package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.SqliteConnection;

public class ReturnLogModel {
	Connection connection;

	public ReturnLogModel() {
		connection = SqliteConnection.Connector();
		if (connection == null) {
			System.out.println("Connection not successful");
			System.exit(1);
		}
	}
	
	public boolean isLogin(String pass) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from current_password where password = ?";
		try {
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
