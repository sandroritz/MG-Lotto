package ch.mgeggishorn;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;


public class Main extends Application {

	@FXML
	private Label lblLoginError;
	@FXML
	private TextField txtBenutzer;
	@FXML
	private TextField txtPasswort;

	
	@FXML
	public void login() throws IOException {
		System.out.println(txtBenutzer.getText() + " " + txtPasswort.getText());
		
	if((txtBenutzer.getText().equals("demo")) && (txtPasswort.getText().equals("demo"))){
			lblLoginError.setText("Login erfolgreich!");
			Stage stage = new Stage();
			stage.getIcons().add(new Image("/mg-logo.jpg"));
			Parent root = FXMLLoader.load(getClass().getResource(
					"view/RootLayoutView.fxml"));
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("MG - Lotto");
			stage.show();
			
			Stage stageToClose = (Stage) txtBenutzer.getScene().getWindow();
			stageToClose.close();
		}
		else{
			lblLoginError.setText("Fehler beim Einloggen!");
		}

	}
	

	@Override
	public void start(Stage primaryStage) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource(
					"view/loginView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
			primaryStage.getIcons().add(new Image("/mg-logo.jpg"));
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
