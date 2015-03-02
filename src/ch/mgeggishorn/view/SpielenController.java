package ch.mgeggishorn.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ch.mgeggishorn.controller.DBManager;
import ch.mgeggishorn.controller.Zahlenreihe;
import ch.mgeggishorn.logger.Log;
import ch.mgeggishorn.logger.LogView;
import ch.mgeggishorn.logger.Logger;
import ch.mgeggishorn.model.CurrentSpielerModel;
import ch.mgeggishorn.model.RundenModel;
import ch.mgeggishorn.model.SerieModel;
import ch.mgeggishorn.model.SpielerModel;

public class SpielenController implements Initializable {

	// Tab
	@FXML
	private Label lblRundenNr;
	@FXML
	private Label lblSerieNr;
	@FXML
	private Label lblPreis;
	@FXML
	private Label lblGewinner;
	@FXML
	private TextField txtGetrillteeZahl;
	@FXML
	private TextField txtManuellGewinner;
	@FXML
	private CheckBox cboxManuell;

	int rundenNr = 1;
	int serienNr = 0;
	String preis = "";
	
	public void initialize(URL url, ResourceBundle rb) {
		
		
		
		DBManager dbm = new DBManager();
		try {
			serienNr = dbm.getFirstSerie(rundenNr);
			preis = dbm.getPreis(rundenNr,serienNr);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		lblRundenNr.setText("Runden Nr: 1");
		lblSerieNr.setText("Serien Nr: "  + serienNr);
		lblPreis.setText("Preis: " +preis);
	}

	@FXML
	private void zahlEnter() {
		DBManager dbm = new DBManager();
		int zahl = Integer.parseInt(txtGetrillteeZahl.getText());
		try {
			String txtGewinner = dbm.searchGewinner(zahl, rundenNr, serienNr);
			lblGewinner.setText(txtGewinner);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void neuerVersuch() {

	}

	@FXML
	private void serieAbschliessen() {
		//Suche naechste Serie/runde
	}

	// Menuitems
	@FXML
	private void closeSpiel() {

	}

	@FXML
	private void zahlenZuweisen() {

	}

	@FXML
	private void spielerAusschliessen() {

	}

}