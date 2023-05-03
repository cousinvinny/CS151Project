package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.SqliteConnection;

public class ConfirmOldPWModel{
	Connection connection;

	public ConfirmOldPWModel() {
		connection = SqliteConnection.Connector();
		if (connection == null) {
			System.out.println("Connection not successful");
			System.exit(1);
		}
	}

	public boolean isLogin(String pass) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String hint;
		String query = "SELECT * FROM current_password WHERE password = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, pass);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			else {
				hint = "Hint: Old Password was '" + resultSet.getString("password")+"' (without the quotes)";;
				System.out.println(hint);
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