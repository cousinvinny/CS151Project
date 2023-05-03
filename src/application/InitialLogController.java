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
import model.InitialLoginModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InitialLogController implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToSceneresetPW(ActionEvent event) throws IOException {		
		root = (BorderPane)FXMLLoader.load(getClass().getResource("ConfirmOldPW.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * FIRST TIME LOGING IN
	 */
	@FXML 
	PasswordField defaultPWPF;
	@FXML 
	Button initialLoginBTN;
	@FXML 
	Label loginStatus;
	public String firstLoginPW;
	InitialLoginModel initialLoginModel = new InitialLoginModel();
	
	public void firstTimeLogin(ActionEvent event) throws IOException {
		String flag = "FALSE";
		firstLoginPW = defaultPWPF.getText();
		try {
			if(initialLoginModel.isFirstTimeLogin(firstLoginPW,flag)) {
				System.out.println("Initial Login Success");
				switchToSceneresetPW(event);
			}
			else {
				System.out.println("Login Failed");
				loginStatus.setVisible(true);
				loginStatus.setText("Incorrect default password entered!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * END FIRST TIME LOGING IN
	 */
	public void initialize(URL location, ResourceBundle resource) {
		loginStatus.setVisible(false);
		loginStatus.setWrapText(true);
	}
}
