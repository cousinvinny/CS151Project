package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.ArrayList;

public class recGUIChoiceBoxController implements Initializable {
	
	/**
	 * USED TO SWAP SCENES
	 */

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	/**
	 * START OF RECOMMENDATION
	 */
	private Recommendation rec = new Recommendation(); 
	private String firstname, lastname, gender, targetSchool, targetProgram, currentDate,
    			firstSemesterTaken, firstYearTaken, stuPC, stuAC;
	private String genders[] = {"Male", "Female"};
	private String courses[] = {"CS151: Object-Oriented Design",
			"CS166: Information Security",
			"CS154: Theory of Computation",
			"CS160: Software Engineering",
			"CS256: Cryptography",
			"CS146: Data Structures and Algorithms", "CS152: Programming Languages Paradigm"};
	private String semestersTaught[] = {"Spring", "Fall", "Summer"};
	private String programNames[] = {"Master of Science (MS)", "Master of Business Administration (MBA)"
			, "Doctor of Philosophy (PhD)"};
	private String personalCharacteristics[] = {"very passionate", "very enthusiastic", "punctual",
			"attentitive", "polite"};
	private String academicCharacteristics[] = {"submitted well-written assignments", "participated in all of my class activities",
			"worked hard", "was very well prepared for evert exam and assignment", "picked up new skills quickly",
			"was able to excel academically at the top of my class"};
	
	/**
	 * FXML OBJECTS
	 */
	@FXML TextField fnTextField, lnTextField, tsTextField, semYearTextField;
	@FXML Button compileButton, addDataButton, compileButton2;
	@FXML ChoiceBox<String> genCB, tpCB, semTakeCB, pcCB, acCB;
	@FXML Label testLB;
	@FXML DatePicker datePick;
	/**
	 * FXML OBJECTS END
	 */
	
	@FXML Button logoutRECBTN;
	public void logOut(ActionEvent event) throws IOException {
		System.out.println("Goodbye!");
		switchToSceneReturnLogin(event);
	}
	
	public void switchToSceneReturnLogin(ActionEvent event) throws IOException {
		root = (BorderPane)FXMLLoader.load(getClass().getResource("returnLog.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void compileRec(ActionEvent event) {
		ArrayList<String> stuPCS = new ArrayList<>();
		ArrayList<String> stuACS = new ArrayList<>();

		
		try {
			firstname = fnTextField.getText();
			rec.setFirstname(firstname);
			
			lastname = lnTextField.getText();
			rec.setLastname(lastname);
			
			gender = genCB.getValue();
			rec.setGender(gender);
			
			targetSchool = tsTextField.getText();
			rec.setTargetSchool(targetSchool);
			
			targetProgram = tpCB.getValue();
			rec.setTargetProgram(targetProgram);
			
			LocalDate date = datePick.getValue();
			currentDate = date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
			rec.setCurrentDate(currentDate);
			
			firstSemesterTaken = semTakeCB.getValue();
			rec.setFirstSemesterTaken(firstSemesterTaken);
			
			firstYearTaken = semYearTextField.getText();
			rec.setFirstYearTaken(firstYearTaken);
			
			// additional data
			stuPC = pcCB.getValue();
			stuPCS.add(stuPC);
			rec.setPersonalCharacteristics(stuPCS);
			
			stuAC = acCB.getValue();
			stuACS.add(stuAC);
			rec.setAcademicCharacteristics(stuACS);
			
			System.out.println("Name: " + firstname + " " + lastname + "\n"
					+ "Gender: " + gender + "\n"
					+ "Target School: " + targetSchool + "\n"
					+ "Target Program: " + targetProgram + "\n"
					+ "Current Date: " + currentDate + "\n"
					+ "Semester Taken: " + firstSemesterTaken + "\n"
					+ "Year Taken: " + firstYearTaken + "\n");
			System.out.print("Personal Characteristics: ");
			for(int i = 0; i < stuPCS.size(); i++)
			{
				//System.out.print(rec.getPersonalCharacteristics().at(i) + " ");
				System.out.print(stuPCS.get(i));
			}
			System.out.println();
			System.out.print("Academic Characteristics: ");
			for(int j = 0; j < stuACS.size(); j++)
			{
				System.out.print(stuACS.get(j));
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Used to initialize the drop down menus with items
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		genCB.getItems().addAll(genders);
		tpCB.getItems().addAll(programNames);
		semTakeCB.getItems().addAll(semestersTaught);
		pcCB.getItems().addAll(personalCharacteristics);
		acCB.getItems().addAll(academicCharacteristics);
	}
	
}
