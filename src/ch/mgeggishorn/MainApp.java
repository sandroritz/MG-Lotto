package ch.mgeggishorn;

import java.io.IOException;



import ch.mgeggishorn.controller.RootLayoutController;
import ch.mgeggishorn.controller.SpielerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	

	public MainApp() {
		

	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("MG Eggishorn Lotto");

		// Set the application icon.
		// this.primaryStage.getIcons().add(new
		// Image("file:resources/images/address_book_32.png"));

		initRootLayout();
		SpielerController controller = new SpielerController();
		controller.getAllSpieler();
	}

	/**
	 * Initializes the root layout and tries to load the last opened person
	 * file.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
