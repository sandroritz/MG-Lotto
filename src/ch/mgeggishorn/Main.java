package ch.mgeggishorn;
	

import java.io.IOException;


import ch.mgeggishorn.view.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;


public class Main extends Application {
	
	@FXML
	private TextField txtBenutzer;
	@FXML
	private TextField txtPasswort;
	
	@FXML
	public void login() throws IOException{
//		if(txtBenutzer.getText() == "demo" && txtPasswort.getText()=="demo"){
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("view/RootLayoutView.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
//		}
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/loginView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
