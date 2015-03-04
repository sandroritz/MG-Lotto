package ch.mgeggishorn.view;

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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
import javafx.util.Callback;
import ch.mgeggishorn.controller.DBManager;
import ch.mgeggishorn.controller.Zahlenreihe;
import ch.mgeggishorn.logger.Log;
import ch.mgeggishorn.logger.LogView;
import ch.mgeggishorn.logger.Logger;
import ch.mgeggishorn.model.CurrentSpielerModel;
import ch.mgeggishorn.model.RundenModel;
import ch.mgeggishorn.model.SerieModel;
import ch.mgeggishorn.model.SpielerModel;

public class RemoveSpielerController implements Initializable {

	@FXML
	private Label lblLog;
	@FXML
	private TextField txtCurrentSpielerSearch;
	@FXML
	private ListView<CurrentSpielerModel> listSearchedCurrentSpieler;

	List<CurrentSpielerModel> searchedCurrentSpieler;

	public void initialize(URL url, ResourceBundle rb) {
		txtCurrentSpielerSearch.setText("");
		lblLog.setText("");
	}

	@FXML
	private void sucheCurrentSpieler() {
		DBManager dbm = new DBManager();
		lblLog.setText("");
		searchedCurrentSpieler = new ArrayList<CurrentSpielerModel>();
		try {
			searchedCurrentSpieler = dbm
					.getSearchedCurrentSpieler(txtCurrentSpielerSearch
							.getText());

		} catch (Exception e) {
			e.printStackTrace();
		}

		listSearchedCurrentSpieler
				.setCellFactory(new Callback<ListView<CurrentSpielerModel>, ListCell<CurrentSpielerModel>>() {

					@Override
					public ListCell<CurrentSpielerModel> call(
							ListView<CurrentSpielerModel> p) {

						ListCell<CurrentSpielerModel> cell = new ListCell<CurrentSpielerModel>() {

							@Override
							protected void updateItem(CurrentSpielerModel t,
									boolean bln) {
								super.updateItem(t, bln);
								if (t != null) {
									setText(t.getName() + " " + t.getVorname()
											+ ", " + t.getStrasse() + ", "
											+ t.getPlz() + " " + t.getOrt());
								}
							}

						};

						return cell;
					}
				});

		ObservableList<CurrentSpielerModel> items = FXCollections
				.observableArrayList(searchedCurrentSpieler);
		listSearchedCurrentSpieler.setItems(items);

	}

	@FXML
	private void removeCurrentSpieler() {
		DBManager dbm = new DBManager();
		int fkSpieler = listSearchedCurrentSpieler.getSelectionModel()
				.getSelectedItem().getFkSpieler();

		try {
			dbm.removeSearchedCurrentSpieler(fkSpieler);
			lblLog.setText("Spieler erfolgreich ausgeschlossen!");
			txtCurrentSpielerSearch.setText("");

		} catch (Exception e) {
			e.printStackTrace();
			lblLog.setText("Ein Fehler ist aufgetaucht!");
		}
		listSearchedCurrentSpieler.getItems().clear();
	}
}