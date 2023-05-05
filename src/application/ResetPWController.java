package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ResetPWModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResetPWController implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToSceneReturnLogin(ActionEvent event) throws IOException {
		root = (BorderPane)FXMLLoader.load(getClass().getResource("returnLog.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * RESET PW
	 */
	@FXML PasswordField newPWPF, confirmNewPWPF;
	@FXML Button confirmNewPWBTN;
	@FXML Label resetPasswordStatus;
	private String newPassword, confNewPassword;
	
	public ResetPWModel resetPWModel = new ResetPWModel();
	
	public void confirmPW(ActionEvent event) throws IOException {
		newPassword = newPWPF.getText();
		confNewPassword = confirmNewPWPF.getText();
		try {
			if(newPassword.equals(confNewPassword)) {
				System.out.println("New PW Set");
				resetPWModel.resetPW(newPWPF.getText());
				switchToSceneReturnLogin(event);
			}
			else {
				System.out.println("New PW Failed");
				resetPasswordStatus.setVisible(true);
				resetPasswordStatus.setText("Passwords do not match!");
			}
		} catch (SQLException e) {
			resetPasswordStatus.setText("Passwords do not match!");
			e.printStackTrace();
		}
	}
	/**
	 * END RESET PW
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		resetPasswordStatus.setVisible(false);
	}
}
