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

	public boolean insertRecommendationDataToDB(Recommendation recommendation, StringBuilder sb) {
		String insert = "INSERT INTO recommendation_data (firstname, lastname, gender, target_school,"
				+ " target_program, current_date, first_semester, first_year, courses_taken, course_grades,"
				+ " personal_characteristics, academic_characteristics, recommendation_letter_text) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(insert)) {
			pstmt.setString(1, recommendation.getFirstname());
			pstmt.setString(2, recommendation.getLastname());
			pstmt.setString(3, recommendation.getGender());
			pstmt.setString(4, recommendation.getTargetSchool());
			pstmt.setString(5, recommendation.getTargetProgram());
			pstmt.setString(6, recommendation.getCurrentDate());
			pstmt.setString(7, recommendation.getFirstSemesterTaken());
			pstmt.setString(8, recommendation.getFirstYearTaken());
			pstmt.setString(9, String.join(",", recommendation.getCoursesTaken()));
			pstmt.setString(10, String.join(",", recommendation.getGrades()));
			pstmt.setString(11, String.join(",", recommendation.getPersonalCharacteristics()));
			pstmt.setString(12, String.join(",", recommendation.getAcademicCharacteristics()));
			pstmt.setString(13, sb.toString());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean recommendationExists(String lastNameInput) throws SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM recommendation_data WHERE lastname = ?";
		try {
		    preparedStatement = connection.prepareStatement(query);
		    preparedStatement.setString(1, lastNameInput);
		    resultSet = preparedStatement.executeQuery();
		    if (resultSet.next()) {
		    	System.out.println("Success: Recommendation with last name '" + lastNameInput + "' found in database!");
		        return true;
		    }
		    else {
		    	System.out.println("Error: No recommendation with that last name exists!");
		        return false;
		    }
		} catch (Exception e) {
			System.out.println("Error: No recommendation with that last name exists!" + e);
		    return false;
		} finally {
		    preparedStatement.close();
		    resultSet.close();
		}
	}
	
	public Recommendation loadRecommendationDataFromDB(String lastNameInput) throws SQLException {
	    Recommendation recommendation = new Recommendation();
	    String firstname, lastname, gender, targetSchool, targetProgram, currentDate, firstSemester, firstYear, recommendationLetterText;
	    //coursesTaken,courseGrades, personal_characteristics, academicCharacteristics, ;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    String query = "SELECT firstname, lastname, gender, target_school, target_program, current_date, first_semester,"
	            + " first_year, courses_taken, course_grades, personal_characteristics, academic_characteristics,"
	            + " recommendation_letter_text FROM recommendation_data WHERE lastname = ?";
	    try {
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, lastNameInput);
	        resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            firstname = resultSet.getString("firstname");
	            lastname = resultSet.getString("lastname");
	            gender = resultSet.getString("gender");
	            targetSchool = resultSet.getString("target_school");
	            targetProgram = resultSet.getString("target_program");
	            currentDate = resultSet.getString("current_date");
	            firstSemester = resultSet.getString("first_semester");
	            firstYear = resultSet.getString("first_year");
	            recommendationLetterText = resultSet.getString("recommendation_letter_text");
	            recommendation.setFirstname(firstname);
	            recommendation.setLastname(lastname);
	            recommendation.setGender(gender);
	            recommendation.setTargetSchool(targetSchool);
	            recommendation.setTargetProgram(targetProgram);
	            recommendation.setCurrentDate(currentDate);
	            recommendation.setFirstSemesterTaken(firstSemester);
	            recommendation.setFirstYearTaken(firstYear);
	            recommendation.setRecommendationLetterText(recommendationLetterText);
	            return recommendation;
	        }
	        return recommendation;
	    } catch (Exception e) {
	    	System.out.println("Error: could not load recommendation data from database!" + e.getMessage());
	        return recommendation;
	    } finally {
	        preparedStatement.close();
	        resultSet.close();
	    }
	}
	
	public void deleteRecommendationFromDB(String lastNameInput) throws SQLException {
		String query = "DELETE FROM recommendation_data WHERE lastname = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, lastNameInput);
	        preparedStatement.executeUpdate();
	        preparedStatement.close();
	    } catch (SQLException e) {
	        System.out.println("Error: Could not delete recommendation from database!" + e.getMessage());
	    }
	}
}
