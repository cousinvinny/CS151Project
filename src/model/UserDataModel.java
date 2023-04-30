package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.SqliteConnection;
import application.User;

public class UserDataModel {
	Connection connection;
	User user = User.getUser();
	
	public UserDataModel() {
		connection = SqliteConnection.Connector();
		if (connection == null) {
			System.out.println("Connection not successful");
			System.exit(1);
		}
	}
	
	public boolean populateUser() throws SQLException {

		String name, title, school, department, email, phoneNum;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select full_name, title, school_name, department_name, email_address, phone_number from faculty_signature";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				name = resultSet.getString("full_name");
				title = resultSet.getString("title");
				school = resultSet.getString("school_name");
				department = resultSet.getString("department_name");
				email = resultSet.getString("email_address");
				phoneNum = resultSet.getString("phone_number");
				user.setFullname(name);
				user.setTitle(title);
				user.setSchoolName(school);
				user.setDepartment(department);
				user.setEmail(email);
				user.setPhoneNumber(phoneNum);
				return true;
			}
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	public boolean populateSemesters() throws SQLException{
		String semester;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select semester_name from semester";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				semester = resultSet.getString("semester_name");
				user.getSemestersTaught().add(semester);
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	public boolean populateCourses() throws SQLException{
		String course;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select course_name from course_taught";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				course = resultSet.getString("course_name");
				user.getCoursesTaught().add(course);
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	public boolean populatePrograms() throws SQLException{
		String program;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select program_name from program";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				program = resultSet.getString("program_name");
				user.getPrograms().add(program);
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	public boolean populatePersonalCharacteristics() throws SQLException{
		String personalCharcteristic;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select personal_adjective from personal_characteristic";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				personalCharcteristic = resultSet.getString("personal_adjective");
				user.getPersonalChar().add(personalCharcteristic);
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	public boolean populateAcademicCharacteristics() throws SQLException{
		String academicCharcteristic;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select academic_adjective from academic_characteristic";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				academicCharcteristic = resultSet.getString("academic_adjective");
				user.getAcademicChar().add(academicCharcteristic);
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	/*
	public boolean editUserData() throws SQLException{
		try {
			
		} catch(Exception e) {
			
		} finally {
			
		}
	}*/
}
