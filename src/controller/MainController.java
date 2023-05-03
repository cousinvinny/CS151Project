package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	// Create New Recommendation
	public void switchToSceneRecGUI(ActionEvent event) throws IOException {
		root = (BorderPane) FXMLLoader.load(getClass().getResource("recGUI.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// Search Recommendations
	public void switchToSearch(ActionEvent event) throws IOException {
		root = (BorderPane) FXMLLoader.load(getClass().getResource("SearchForRecommendation.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	// User Data
	public void switchToSceneUserData(ActionEvent event) throws IOException {
		root = (BorderPane) FXMLLoader.load(getClass().getResource("UserData.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// Exit
	public void switchToSceneReturnLogin(ActionEvent event) throws IOException {
		root = (BorderPane) FXMLLoader.load(getClass().getResource("returnLog.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
