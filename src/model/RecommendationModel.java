package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Recommendation;
import application.SqliteConnection;
import application.User;

public class RecommendationModel {
	Connection connection;
	User user = User.getUser();
	
	public RecommendationModel() {
		connection = SqliteConnection.Connector();
		if (connection == null) {
			System.out.println("Connection not successful");
			System.exit(1);
		}
	}
	
	
	
	public boolean insertRecommendationDataToDB(Recommendation recommendation) {
		String insert = "INSERT INTO recommendation_data (firstname, lastname, gender, target_school,"
				+ " target_program, current_date, first_semester, first_year, courses_taken, course_grades,"
				+ " personal_characteristics, academic_characteristics) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		
		try (PreparedStatement pstmt = connection.prepareStatement(insert)) {
	        pstmt.setString(1, recommendation.getFirstname());
	        pstmt.setString(2, recommendation.getLastname());
	        pstmt.setString(3, recommendation.getGender());
	        pstmt.setString(4, recommendation.getTargetSchool());
	        pstmt.setString(5, recommendation.getTargetProgram());
	        pstmt.setString(6, recommendation.getCurrentDate());
	        pstmt.setString(7, recommendation.getFirstSemesterTaken());
	        pstmt.setString(8, recommendation.getFirstYearTaken());
	        pstmt.setString(9, String.join(",",recommendation.getCoursesTaken()));
	        pstmt.setString(10, String.join(",",recommendation.getGrades()));
	        pstmt.setString(11, String.join(",",recommendation.getPersonalCharacteristics()));
	        pstmt.setString(12, String.join(",",recommendation.getAcademicCharacteristics()));


	        pstmt.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
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
}
