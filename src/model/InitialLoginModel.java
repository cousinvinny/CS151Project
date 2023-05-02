package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.SqliteConnection;

public class InitialLoginModel {
	Connection connection;

	public InitialLoginModel() {
		connection = SqliteConnection.Connector();
		if (connection == null) {
			System.out.println("Connection not successful");
			System.exit(1);
		}
	}

	public boolean chechDBForInitialLogin() throws SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM default_password WHERE initial_login_flag = 'TRUE'";
		try {
		    preparedStatement = connection.prepareStatement(query);
		    resultSet = preparedStatement.executeQuery();
		    if (resultSet.next()) {
		        // At least one row exists with initial_login_flag set to true
		    	System.out.println("This is the initial login!");
		        return true;
		    }
		    else {
		        // No row exists with initial_login_flag set to true
		    	System.out.println("This is not an initial login!");
		        return false;
		    }
		} catch (Exception e) {
		    return false;
		} finally {
		    preparedStatement.close();
		    resultSet.close();
		}
	}
	
	public boolean isFirstTimeLogin(String pass, String flag) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from default_password where password = ?";
		String insert = "INSERT INTO current_password (password) VALUES (?)";
		String updateInitialLoginFlag = "UPDATE default_password SET password = ?, initial_login_flag = ?";
		try {
			preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setString(1, pass);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(updateInitialLoginFlag);
			preparedStatement.setString(1, pass);
			preparedStatement.setString(2, flag);
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
