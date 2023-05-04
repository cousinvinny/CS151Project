package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import model.ConfirmOldPWModel;

public class ConfirmOldPWController implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;

	public void switchToSceneResetPW(ActionEvent event) throws IOException {
		root = (BorderPane) FXMLLoader.load(getClass().getResource("resetPW.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * CONFIRM OLD PW
	 */
	@FXML
	PasswordField oldPWPF;
	@FXML
	Button confirmOldPWBTN;
	@FXML
	Label errorMessage;
	private String oldPassword;
	User user = User.getUser();
	ConfirmOldPWModel confirmOldPWModel = new ConfirmOldPWModel();
	public void confirmPW(ActionEvent event) throws IOException {
		try {
			oldPassword = oldPWPF.getText();
			if (confirmOldPWModel.isLogin(oldPassword)) {
				System.out.println("Correct Old Password Entered");
				switchToSceneResetPW(event);
			} else {
				errorMessage.setVisible(true);
				errorMessage.setText("Incorrect Old Password Entered!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errorMessage.setText("Incorrect password entered!");
			e.printStackTrace();
		}
	}
	/**
	 * END CONFIRM OLD PW
	 */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		errorMessage.setVisible(false);
	}

}
