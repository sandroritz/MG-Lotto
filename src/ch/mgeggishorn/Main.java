package ch.mgeggishorn;

import java.io.IOException;
import java.util.Random;

import ch.mgeggishorn.controller.*;
import ch.mgeggishorn.view.RootLayoutController;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;



public class Main extends Application {

	@FXML
	private TextField txtBenutzer;
	@FXML
	private TextField txtPasswort;

	
	@FXML
	public void login() throws IOException {
		System.out.println(txtBenutzer.getText() + " " + txtPasswort.getText());
		
		if((txtBenutzer.getText().equals("demo")) && (txtPasswort.getText().equals("demo"))){
			
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
