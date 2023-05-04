package application;

import java.io.File;
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
import model.RecommendationModel;

public class SearchForRecController {
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

	public void switchToSceneRecGUI(ActionEvent event) throws IOException {
		root = (BorderPane) FXMLLoader.load(getClass().getResource("recGUI.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	User user = User.getUser();
	private String recName;

	@FXML
	TextField searchTF;
	@FXML
	TextArea displayRecTA;
	
	public static String stuRecToEdit = "";
	RecommendationModel recModel = new RecommendationModel();
	Recommendation rec  =  new Recommendation();
	
	public void searchRec(ActionEvent event) throws IOException {
		try {
			displayRecTA.clear();
			recName = searchTF.getText();
			setStudentRecommendationToEdit(searchTF.getText()); //last name is now usuable by RecommendationModel to make a query to database
			if(recModel.recommendationExists(recName)) {
				rec = recModel.loadRecommendationDataFromDB(recName);
				displayRecTA.appendText("Recommendation: " + rec.getFirstname() + "_"
						+ rec.getLastname() + "_Recommendation was found!\n");
			} else {
				displayRecTA.appendText("No recommendation with last name '" + recName + "' was found.");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteRec(ActionEvent event) throws IOException {
	    try {
	        String recFileName = rec.getFirstname() + "_" + rec.getLastname() + "_" + "Recommendation.txt";
	        recName = searchTF.getText();
	        recModel.deleteRecommendationFromDB(recName);
	        File fileToDelete = new File(recFileName);
	        if (fileToDelete.delete()) {
	            displayRecTA.clear();
	            displayRecTA.setText("Recommendation data in database and textfile " + recFileName + " were successfully removed.");
	            System.out.println("Success: Recommendation data in database and textfile " + recFileName + " were successfully removed.");
	        } else {
	            displayRecTA.clear();
	            displayRecTA.setText("Error: Could not delete file, please search for a recommendation again.");
	            System.out.println("Error: Could not delete file, please search for a recommendation again.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error: Could not delete recommendation from database");
	    }
	}

	public static String getStuRecLastNameToEdit() {
		return stuRecToEdit;
	}

	public void setStudentRecommendationToEdit(String studentRecommendationToEdit) {
		SearchForRecController.stuRecToEdit = studentRecommendationToEdit;
	}
	
}
