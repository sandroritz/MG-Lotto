package ch.mgeggishorn.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ch.mgeggishorn.controller.DBManager;
import ch.mgeggishorn.model.SpielerModel;

public class NeuerSpielerController implements Initializable {
	
	//DetailView
		@FXML
		private TextField txtId;
		@FXML
		private TextField txtName;
		@FXML
		private TextField txtVorname;
		@FXML
		private TextField txtStrasse;
		@FXML
		private TextField txtPlz;
		@FXML
		private TextField txtOrt;	
		
		
		int lastSpielerId;
		

	public void initialize(URL url, ResourceBundle rb) {
		
		DBManager dbm = new DBManager();
    	try{
    		lastSpielerId = dbm.getLastId();
    		txtId.setText(String.valueOf(++lastSpielerId)); //Id fuer neuen Spieler
    	} catch(Exception e){
    		e.printStackTrace();
    	}
	}

	
	
	@FXML
	private void speichern() {
		DBManager dbm = new DBManager();
		SpielerModel spieler = new SpielerModel(Integer.parseInt(txtId
				.getText()), txtName.getText(), txtVorname.getText(),
				txtStrasse.getText(), Integer.parseInt(txtPlz.getText()),
				txtOrt.getText());

		try {
			dbm.insertSpieler(spieler);

			// Detailfelder füllen
			txtId.setText(String.valueOf(spieler.getId()));
			txtName.setText(spieler.getName());
			txtVorname.setText(spieler.getVorname());
			txtStrasse.setText(spieler.getStrasse());
			txtPlz.setText(String.valueOf(spieler.getPlz()));
			txtOrt.setText(spieler.getOrt());
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}