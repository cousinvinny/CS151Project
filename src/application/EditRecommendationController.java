package application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.RecommendationModel;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditRecommendationController implements Initializable {
	/**
	 * USED TO SWAP SCENES
	 */
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

	/**
	 * END OF SWAPPING SCENES
	 */

	/**
	 * START OF RECOMMENDATION
	 */
	private User user = User.getUser();
	private Recommendation rec = new Recommendation();
	private String firstname, lastname, gender, targetSchool, targetProgram, currentDate, firstSemesterTaken,
			firstYearTaken, firstCourse, firstGrade, otherGrade;
	private String genders[] = { "Male", "Female" };
	private ArrayList<String> courses = user.getCoursesTaught();
	private ArrayList<String> semestersTaught = user.getSemestersTaught();
	private ArrayList<String> programs = user.getPrograms();
	private ArrayList<String> personalChar = user.getPersonalChar();
	private ArrayList<String> academicChar = user.getAcademicChar();

	@FXML
	TextField fnTextField, lnTextField, tsTextField, semYearTextField, firstCourseTF, firstGradeTF;
	@FXML
	Button compileButton, addDataButton, compileButton2;
	@FXML
	ChoiceBox<String> genCB, tpCB, semTakeCB;
	@FXML
	Label testLB;
	@FXML
	DatePicker datePick;
	@FXML
	ListView<String> otherCoursesLV, pcLV, acLV;
	@FXML
	TextArea otherGradesTA;

	RecommendationModel recommendationModel = new RecommendationModel();
	
	public void compileRec(ActionEvent event) { // rename to save data
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

			firstCourse = firstCourseTF.getText();
			rec.getCoursesTaken().add(firstCourse);

			firstGrade = firstGradeTF.getText();
			rec.getGrades().add(firstGrade);

			// additional data (multiple selection)

			// OTHER COURSES
			ObservableList<String> selectedCourses = otherCoursesLV.getSelectionModel().getSelectedItems();
			for (String otherCourse : selectedCourses) {
				rec.getCoursesTaken().add(otherCourse);
			}

			// OTHER GRADES
			otherGrade = otherGradesTA.getText();
			String[] otherGradeArr = otherGrade.split(",");
			for (String oGrade : otherGradeArr) {
				rec.getGrades().add(oGrade);
			}

			// personal characteristics
			ObservableList<String> selectedPC = pcLV.getSelectionModel().getSelectedItems();
			for (String stuPC : selectedPC) {
				rec.getPersonalCharacteristics().add(stuPC);
			}

			// academic characteristics
			ObservableList<String> selectedAC = acLV.getSelectionModel().getSelectedItems();
			for (String stuAC : selectedAC) {
				rec.getAcademicCharacteristics().add(stuAC);
			}

			user.getCompletedRecs().add(rec); // add new completed rec to arraylist
			recommendationModel.editRecommendationDataInDB(rec);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@FXML
	TextArea compiledTA;

	public void printRec(ActionEvent event) throws IOException {
		String pronoun;
		if (gender.equals("Male")) {
			pronoun = "He";
		} else {
			pronoun = "She";
		}

		compiledTA.appendText("For: " + rec.getFirstname() + " " + rec.getLastname() + "\n\n");
		String fixDate = String.format("%150s", "Date: " + rec.getCurrentDate());
		compiledTA.appendText(fixDate);
		compiledTA.appendText("\n\nTo: Graduate Admissions Committee\n\n");
		compiledTA.appendText("I am writing this letter to recommend my former student " + rec.getFirstname() + " "
				+ rec.getLastname() + " who is applying for the " + rec.getTargetProgram() + " in your school.");
		compiledTA.appendText(
				" I met " + rec.getFirstname() + " in " + rec.getFirstSemesterTaken() + " " + rec.getFirstYearTaken()
						+ " when he enrolled in my \"" + rec.getCoursesTaken().get(0) + "\" course.\n\n");
		compiledTA.appendText(firstname + " earned \"" + rec.getGrades().get(0)
				+ "\" from this tough course, and this shows how knowledgeable and hard worker " + pronoun
				+ " is.\n\n");

		compiledTA.appendText(pronoun + " also earned ");
		String ocString = "";
		for (int ocCount = 0; ocCount < rec.getCoursesTaken().size(); ocCount++) {
			if (rec.getCoursesTaken().size() > 1 && ocCount == rec.getCoursesTaken().size() - 1) {
				ocString = ocString + "\"" + rec.getGrades().get(ocCount) + "\" from my \""
						+ rec.getCoursesTaken().get(ocCount) + " courses.";
			} else if (rec.getCoursesTaken().size() == 1) {
				ocString = "\"" + rec.getGrades().get(ocCount) + "\" from my \"" + rec.getCoursesTaken().get(ocCount)
						+ "\"" + " course.";
			} else {
				ocString = ocString + "\"" + rec.getGrades().get(ocCount) + "\" from my \""
						+ rec.getCoursesTaken().get(ocCount) + "\", ";
			}
		}
		compiledTA.appendText(ocString);

		compiledTA.appendText("\n\n" + rec.getFirstname() + " ");
		String acString = "";
		for (int acCount = 0; acCount < rec.getAcademicCharacteristics().size(); acCount++) {
			if (rec.getAcademicCharacteristics().size() > 1 && acCount == rec.getAcademicCharacteristics().size() - 1) {
				acString = acString + "and " + rec.getAcademicCharacteristics().get(acCount) + ".\n\n";
			} else if (rec.getAcademicCharacteristics().size() == 1) {
				acString = "" + rec.getAcademicCharacteristics().get(acCount) + ".\n\n";
			} else {
				acString = acString + rec.getAcademicCharacteristics().get(acCount) + ", ";
			}
		}
		compiledTA.appendText(acString);

		compiledTA.appendText(pronoun + " was always ");
		String pcString = "";
		for (int pcCount = 0; pcCount < rec.getPersonalCharacteristics().size(); pcCount++) {
			if (rec.getPersonalCharacteristics().size() > 1 && pcCount == rec.getPersonalCharacteristics().size() - 1) {
				pcString = pcString + "and " + rec.getPersonalCharacteristics().get(pcCount) + ".\n\n";
			} else if (rec.getPersonalCharacteristics().size() == 1) {
				pcString = "" + rec.getPersonalCharacteristics().get(pcCount) + ".\n\n";
			} else {
				pcString = pcString + rec.getPersonalCharacteristics().get(pcCount) + ", ";
			}
		}
		compiledTA.appendText(pcString);

		compiledTA.appendText("Furthermore, I noticced from the term project, " + pronoun
				+ ", developed leadership, time managment, and problem-solving skills." + " " + pronoun
				+ " worked effectively with the team members and delegated tasks approriately. "
				+ "They were able to deliver a successful project in a timely fashion.\n\n");
		compiledTA.appendText("I believe that " + rec.getFirstname() + " " + rec.getLastname()
				+ " has the capacity to excel at higher education program and this is my pleasure to highly recommend him.\n\n");
		compiledTA.appendText(
				"Please do not hesitate to contact me with further questions.\n\n\n\nVery Respectfully,\n\n");

		compiledTA.appendText(user.getFullname() + "\n");
		compiledTA.appendText(user.getTitle() + "\n");
		compiledTA.appendText(user.getSchoolName() + ", " + user.getDepartment() + "\n");
		compiledTA.appendText(user.getEmail() + "\n");
		compiledTA.appendText(user.getPhoneNumber());

		System.out.println("finished writing to text area.");
	}

	/**
	 * Used to initialize the drop down menus with items
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		genCB.getItems().addAll(genders);
		tpCB.getItems().addAll(programs);
		semTakeCB.getItems().addAll(semestersTaught);
		otherCoursesLV.getItems().addAll(courses);
		pcLV.getItems().addAll(personalChar);
		acLV.getItems().addAll(academicChar);

		otherCoursesLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		pcLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		acLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	@FXML
	Button exitRecBTN;

	public void logOut(ActionEvent event) throws IOException {
		System.out.println("Goodbye!");
		switchToSceneMainPage(event);
	}

}
