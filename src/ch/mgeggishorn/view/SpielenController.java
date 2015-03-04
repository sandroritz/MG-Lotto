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
import javafx.stage.Modality;
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
	private Label lblAnzVersuche;
	@FXML
	private Label lblGewinner;
	@FXML
	private TextField txtGetrillteeZahl;
	@FXML
	private TextField txtManuellGewinner;
	@FXML
	private CheckBox cboxManuell;
	@FXML
	private Button btnZahlBestaetigen;

	int rundenNr = 0;
	int serienNr = 0;
	String preis = "";

	int maxSerien = 0;
	int countCurrentSerie = 0;
	int anzVersuche = 0;

	List<SerieModel> serienliste;

	boolean siegerIsSet = false;

	public void initialize(URL url, ResourceBundle rb) {
		serienliste = new ArrayList<SerieModel>();
		holeSerienListe();
		getMaxSerien();
		if (countCurrentSerie <= maxSerien) {
			rundenNr = serienliste.get(countCurrentSerie).getRundeNr();
			serienNr = serienliste.get(countCurrentSerie).getSerieNr();
			preis = serienliste.get(countCurrentSerie).getPreis();

			refreshStatusLabels(rundenNr, serienNr, preis);
		}
		cboxManuell.selectedProperty().addListener(
				new ChangeListener<Boolean>() {
					public void changed(ObservableValue<? extends Boolean> ov,
							Boolean old_val, Boolean new_val) {
						if (old_val == false && new_val == true) {
							txtManuellGewinner.setDisable(false);
						} else if (old_val == true && new_val == false) {
							txtManuellGewinner.setDisable(true);
						}
					}
				});

	}

	@FXML
	private void zahlBestaetigen() {
		DBManager dbm = new DBManager();
		int zahl = Integer.parseInt(txtGetrillteeZahl.getText());
		try {
			if (txtGetrillteeZahl.getText() != "") {
				String txtGewinner = dbm.searchGewinner(zahl, rundenNr,
						serienNr);
				lblGewinner.setText(txtGewinner);
				if (txtGewinner != "Kein Gewinner gefunden") {
					siegerIsSet = true;
					btnZahlBestaetigen.setDisable(true);
					txtManuellGewinner.setDisable(true);
					cboxManuell.setDisable(true);
				}
				anzVersuche++;
				lblAnzVersuche.setText("Anz. Versuche: " + anzVersuche);
				txtGetrillteeZahl.setText("");
				txtManuellGewinner.setText("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void serieAbschliessen() {
		if (siegerIsSet) {
			countCurrentSerie++;

			if (countCurrentSerie < maxSerien) {
				rundenNr = serienliste.get(countCurrentSerie).getRundeNr();
				serienNr = serienliste.get(countCurrentSerie).getSerieNr();
				preis = serienliste.get(countCurrentSerie).getPreis();
				refreshStatusLabels(rundenNr, serienNr, preis);
				// Labels clear
				txtGetrillteeZahl.setText("");
				lblGewinner.setText("Gewinner:");
				txtManuellGewinner.setText("");
				anzVersuche = 0;
				lblAnzVersuche.setText("Anz. Versuche: 0");
				btnZahlBestaetigen.setDisable(false);
				txtManuellGewinner.setDisable(false);
				cboxManuell.setDisable(false);
				cboxManuell.selectedProperty().set(false);
			} else {
				System.out.println("Alle Serien gespielt");
			}
		} else if (!siegerIsSet && txtManuellGewinner.getLength() > 6) {
			DBManager dbm = new DBManager();
			dbm.setManuellerGewinner(txtManuellGewinner.getText(), rundenNr,
					serienNr);

			countCurrentSerie++;

			if (countCurrentSerie < maxSerien) {
				rundenNr = serienliste.get(countCurrentSerie).getRundeNr();
				serienNr = serienliste.get(countCurrentSerie).getSerieNr();
				preis = serienliste.get(countCurrentSerie).getPreis();
				refreshStatusLabels(rundenNr, serienNr, preis);
				// Labels clear
				txtGetrillteeZahl.setText("");
				lblGewinner.setText("Gewinner:");
				txtManuellGewinner.setText("");
				anzVersuche = 0;
				lblAnzVersuche.setText("Anz. Versuche: 0");
				btnZahlBestaetigen.setDisable(false);
				txtManuellGewinner.setDisable(false);
				cboxManuell.setDisable(false);
				cboxManuell.selectedProperty().set(false);
			} else {
				System.out.println("Alle Serien gespielt");
			}
		} else {
			System.out.println("Noch kein Sieger festgelegt");
		}
		siegerIsSet = false;
	}

	// Menuitems
	@FXML
	private void closeSpiel() throws IOException {
		Stage stageToClose = (Stage) btnZahlBestaetigen.getScene().getWindow();
		stageToClose.close();

		Stage stage = new Stage();
		stage.getIcons().add(new Image("/mg-logo.jpg"));
		Parent root = FXMLLoader.load(getClass().getResource(
				"RootLayoutView.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("MG - Lotto");
		stage.show();
	}

	@FXML
	private void zahlenZuweisen() {
		// Zufallszahlen
		Zahlenreihe reihe = new Zahlenreihe();

		DBManager dbm = new DBManager();
		try {
			dbm.deleteLottozahlen();
			dbm.setLottozahlen(reihe.createPCNumberList());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void spielerAusschliessen() throws IOException {
		Stage stage = new Stage();
		stage.getIcons().add(new Image("/mg-logo.jpg"));
		Parent root = FXMLLoader.load(getClass().getResource(
				"RemoveSpieler.fxml"));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnZahlBestaetigen.getScene().getWindow());

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("MG - Lotto");
		stage.show();
	}

	private void holeSerienListe() {
		// TODO Auto-generated method stub
		DBManager dbm = new DBManager();
		try {
			serienliste = dbm.holeSerienListe();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getMaxSerien() {
		DBManager dbm = new DBManager();
		try {
			if (dbm.getMaxSerien() != 0) {
				maxSerien = dbm.getMaxSerien();
			} else {
				System.out.println("Fehler bei Max Serien Abfrage");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void refreshStatusLabels(int rundenNr, int serienNr, String preis) {
		// TODO Auto-generated method stub
		lblRundenNr.setText("Runden Nr: " + rundenNr);
		lblSerieNr.setText("Serien Nr: " + serienNr);
		lblPreis.setText("Preis: " + preis);
	}
}