package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SearchForRecommendationController implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToSceneRecGUI(ActionEvent event) throws IOException {
		root = (BorderPane)FXMLLoader.load(getClass().getResource("mainPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToSceneEditRecommendation(ActionEvent event) throws IOException {
		root = (BorderPane)FXMLLoader.load(getClass().getResource("EditRecommendation.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML 
	private Button backButton;
	@FXML
	private Button searchButton;
	@FXML
	private TextField lastNameTextField;
	@FXML
	private Label searchStatusLabel;
	@FXML
	private ListView<String> listView;
	@FXML
	private Button editRecommendationButton;
	@FXML
	private Button deleteRecommendationButton;
	
	/*
	 * This method should searches for a text file named with the last name of student,
	 * but should really look up a row with the correct last name in the database
	 */
	public void searchButtonAction(ActionEvent event) {
		 String fileName = lastNameTextField.getText();
		    File textFile = new File(fileName + ".txt");
		    if(textFile.exists() && textFile.isFile()) {
		        listView.getItems().add(textFile.getName());
		    } else {
		        System.out.println("File is not valid or does not exist.");
		    }
	}
	
	public void editRecommendation(ActionEvent event) throws IOException {
		switchToSceneEditRecommendation(event);
	}
	
	/*
	 * This method will delete a recommendation text file, and the entry in the database
	 */
	public void deleteRecommendation(ActionEvent event) {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		searchStatusLabel.setVisible(false);
	}
}
