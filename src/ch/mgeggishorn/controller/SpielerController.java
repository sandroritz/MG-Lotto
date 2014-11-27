package ch.mgeggishorn.controller;

import ch.mgeggishorn.model.SpielerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class SpielerController {
	private DBManager dbm;
	
	 @FXML
	    private TableView<SpielerModel> personenTabelle;
	    @FXML
	    private TableColumn<SpielerModel, String> nameColonne;
	    @FXML
	    private TableColumn<SpielerModel, String> vornameColone;
	    @FXML
	    private TableColumn<SpielerModel, String> strasseColone;

	    @FXML
	    private TableColumn<SpielerModel, Integer> plzColone;

	    @FXML
	    private TableColumn<SpielerModel, String> ortColone;

	
	    
	 /*public List<Person> getAllPersons() {
	        return dbm.getAllPersons();
	    }
	    */
}
