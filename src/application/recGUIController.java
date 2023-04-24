package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class recGUIController{
	private static String password;
	
	/**
	 * USED TO SWAP SCENES
	 */
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToSceneRecGUI(ActionEvent event) throws IOException {
		root = (BorderPane)FXMLLoader.load(getClass().getResource("recGUI.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void switchToSceneresetPW(ActionEvent event) throws IOException {
		root = (BorderPane)FXMLLoader.load(getClass().getResource("resetPW.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToSceneReturnLogin(ActionEvent event) throws IOException {
		root = (BorderPane)FXMLLoader.load(getClass().getResource("returnLog.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * END OF SWAPPING SCENES
	 */

	
	/**
	 * FIRST TIME LOGING IN
	 */
	@FXML PasswordField defaultPWPF;
	@FXML Button initialLoginBTN;
	
	private final String defaultPW = "p";
	private String firstLoginPW;
	
	public void firstTimeLogin(ActionEvent event) throws IOException {
		firstLoginPW = defaultPWPF.getText();
		if(firstLoginPW.equals(defaultPW)) {
			// swap to change pw page
			System.out.println("Login Success");
			switchToSceneresetPW(event);
		} else {
			System.out.println("Login Failed");
		}
	}
	/**
	 * END FIRST TIME LOGING IN
	 */
	
	/**
	 * RESET PW
	 */
	@FXML PasswordField newPWPF, confirmNewPWPF;
	@FXML Button confirmNewPWBTN;
	private String newPassword, confNewPassword;
	public void confirmPW(ActionEvent event) throws IOException {
		newPassword = newPWPF.getText();
		confNewPassword = confirmNewPWPF.getText();
		if(newPassword.equals(confNewPassword)) {
			System.out.println("New PW Set");
			password = newPassword; // store in user object
			switchToSceneReturnLogin(event);
		} else {
			System.out.println("New PW Failed");
		}
	}
	/**
	 * END RESET PW
	 */
	
	/**
	 * LOG BACK IN
	 */
	@FXML PasswordField logBackInPF;
	@FXML Button logBackInBTN;
	private String logBackInPW;
	
	public void logBackIn(ActionEvent event) throws IOException {
		logBackInPW = logBackInPF.getText();
		System.out.println("Password is:" + password);
		System.out.println("You entered: " + logBackInPW);
		if(logBackInPW.equals(password)) {
			System.out.println("Welcome Back!");
			switchToSceneRecGUI(event);
		} else {
			System.out.println("Incorrect Password");
		}
	}
	
	@FXML Button logoutRECBTN;
	public void logOut(ActionEvent event) throws IOException {
		System.out.println("Goodbye!");
		switchToSceneReturnLogin(event);
	}
	
}
