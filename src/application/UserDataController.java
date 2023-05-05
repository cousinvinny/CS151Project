package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class UserDataController {
	private Stage stage;
	private Scene scene;
	private Parent root;

	public void switchToSceneMainPage(ActionEvent event) throws IOException {
		root = (BorderPane) FXMLLoader.load(getClass().getResource("mainPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToSceneConfirmOldPW(ActionEvent event) throws IOException {
		root = (BorderPane) FXMLLoader.load(getClass().getResource("ConfirmOldPW.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	private boolean inSet(ArrayList<String> list, String word) {
		if (list.indexOf(word) == -1) { // if word is not in list
			return false;
		} else {
			return true; // if word is in list
		}
	}

	private User user = User.getUser();
	private String fullname, title, schoolName, departmentName, email, phoneNum, program, semester, personal, academic;

	@FXML
	TextField fNameTextField, titleTextField, schoolTextField, departmentTextField, phoneNumTextField, emailTextField;
	@FXML
	TextArea semTaughtTA, programNameTA, pcTA, acTA;

	/*
	 * Gets the user inputs from textfields, dropdowns, and multiselects and saves to User object
	 */
	public void inputUserData(ActionEvent event) {
		try {
			fullname = fNameTextField.getText();
			user.setFullname(fullname);

			title = titleTextField.getText();
			user.setTitle(title);

			schoolName = schoolTextField.getText();
			user.setSchoolName(schoolName);

			departmentName = departmentTextField.getText();
			user.setDepartment(departmentName);

			email = emailTextField.getText();
			user.setEmail(email);

			phoneNum = phoneNumTextField.getText();
			user.setPhoneNumber(phoneNum);

			semester = semTaughtTA.getText();
			String[] semArr = semester.split(",");
			for (String sem : semArr) {
				if (!inSet(user.getSemestersTaught(), sem)) {
					user.getSemestersTaught().add(sem);
				}
			}

			program = programNameTA.getText();
			String[] programArr = program.split(",");
			for (String prog : programArr) {
				if (!inSet(user.getPrograms(), prog)) {
					user.getPrograms().add(prog);
				}
			}

			personal = pcTA.getText();
			String[] pcArr = personal.split(",");
			for (String pc : pcArr) {
				if (!inSet(user.getPersonalChar(), pc)) {
					user.getPersonalChar().add(pc);
				}
			}

			academic = acTA.getText();
			String[] acArr = academic.split(",");
			for (String acc : acArr) {
				if (!inSet(user.getAcademicChar(), acc)) {
					user.getAcademicChar().add(acc);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
