package ch.mgeggishorn.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import ch.mgeggishorn.controller.DBManager;
import ch.mgeggishorn.model.CurrentSpielerModel;


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