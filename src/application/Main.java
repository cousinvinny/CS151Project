package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.InitialLoginModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root;
			InitialLoginModel model = new InitialLoginModel();
			if(model.isInitialLogin()==false) {

				root = (BorderPane) FXMLLoader.load(getClass().getResource("returnLog.fxml"));
			}
			else
				root = (BorderPane) FXMLLoader.load(getClass().getResource("initialLog.fxml"));
			Scene scene = new Scene(root, 1280, 720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
