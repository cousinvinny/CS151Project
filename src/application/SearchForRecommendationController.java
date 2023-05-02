package application;

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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SearchForRecommendationController {
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
	
	static String studentRecommendationToEdit;
	
	public void searchRec(ActionEvent event) throws IOException {
		try {
			displayRecTA.clear();
			recName = searchTF.getText();
			setStudentRecommendationToEdit(searchTF.getText());
			int stuPos = user.findStuPos(recName);
			if(stuPos > -1) {
				displayRecTA.appendText("Currently working on: " + user.getCompletedRecs().get(stuPos).getFirstname() + "_"
						+ user.getCompletedRecs().get(stuPos).getLastname() + "_Recommendation\n");
				user.setEditRecName(recName); // store for editing
			} else {
				displayRecTA.appendText("That recommendation does not exist.");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// get name, search for position in arraylist, delete from arraylist
	public void deleteRec(ActionEvent event) throws IOException {
		try {
			recName = searchTF.getText();
			int stuPos = user.findStuPos(recName);
			//System.out.println("Student Found? " + stuPos);
			user.getCompletedRecs().remove(stuPos);
			displayRecTA.clear();
			displayRecTA.setText("Recommendation was successfully removed.");
			//System.out.println("Num of recs in list is/are: " + user.getCompletedRecs().size());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String getStuRecLastNameToEdit() {
		return studentRecommendationToEdit;
	}

	public void setStudentRecommendationToEdit(String studentRecommendationToEdit) {
		this.studentRecommendationToEdit = studentRecommendationToEdit;
	}
	
}
