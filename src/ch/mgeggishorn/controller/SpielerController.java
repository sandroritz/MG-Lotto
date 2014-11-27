package ch.mgeggishorn.controller;

import ch.mgeggishorn.model.SpielerModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SpielerController {
    DBManager dbm = new DBManager();
	

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

	public ObservableList<SpielerModel> getAllSpieler() {
		return dbm.getAllSpieler();
	}

}
