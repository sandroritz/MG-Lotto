package ch.mgeggishorn.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.mgeggishorn.controller.DBManager;


import ch.mgeggishorn.model.SpielerModel;

public class RootLayoutController implements Initializable {

	
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
	
	
	
	// TableView

	ObservableList<SpielerModel> data;

	@FXML
	private TableView<SpielerModel> tblOverview;

	@FXML
	private TableColumn<SpielerModel, String> colName;
	@FXML
	private TableColumn<SpielerModel, String> colVorname;
	@FXML
	private TableColumn<SpielerModel, String> colOrt;

	@FXML
	private void neuSpieler() {
		
	}

	
	
    public void initialize(URL url, ResourceBundle rb) {
       /* List<User> users = null;

        try {
            users = usersManager.getAllUsers();
        } catch (SQLException ex) {
            notification.setText("Problem with loading users from database");
        }

        observableUsers = FXCollections.observableArrayList(users);
        usersList.setItems(observableUsers);

        usersList.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<User>() {
                @Override
                public void changed(ObservableValue<? extends User> ov, User oldVal, User newVal) {
                    if (newVal != null) {
                        activeUserLabel = newVal;
                        userField.setText(activeUserLabel.getName());
                    } else {
                        activeUserLabel = null;
                        userField.clear();
                    }
                }
            }
        );*/
    	
    	List<SpielerModel> spieler = null;
   
    	
    	DBManager dbm = new DBManager();
    	try{
    		spieler = dbm.getAllSpielerOverView();
    	} catch(Exception e){
    		e.printStackTrace();
    	}

    	data = FXCollections.observableArrayList(spieler);
    	tblOverview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SpielerModel>() {
    		@Override
    		public void changed(ObservableValue<? extends SpielerModel> ov, SpielerModel oldVal, SpielerModel newVal) {
                if (newVal != null) {
	                  List<SpielerModel> selectedSpieler = null;
	                  selectedSpieler= dbm.getDetailOfSpieler(newVal);
	                  
	                  //Detailfelder füllen
	                  txtId.setText(String.valueOf(selectedSpieler.get(0).getId()));
	                  txtName.setText(selectedSpieler.get(0).getName());
	                  txtVorname.setText(selectedSpieler.get(0).getVorname());
	                  txtStrasse.setText(selectedSpieler.get(0).getStrasse());
	                  txtPlz.setText(String.valueOf(selectedSpieler.get(0).getPlz()));
	                  txtOrt.setText(selectedSpieler.get(0).getOrt());
                  
                    
                } else {
                	//Detailfelder leeren
	            	 txtId.clear();
	                 txtName.clear();
	                 txtVorname.clear();
	                 txtStrasse.clear();
	                 txtPlz.clear();
	                 txtOrt.clear();
                }
            }
			
		});

		colName.setCellValueFactory(new PropertyValueFactory<SpielerModel, String>(
				"name"));
		colVorname
				.setCellValueFactory(new PropertyValueFactory<SpielerModel, String>(
						"vorname"));
		colOrt.setCellValueFactory(new PropertyValueFactory<SpielerModel, String>(
				"ort"));

		tblOverview.setItems(data);
		
		
		
    }
	
	
	@FXML
	private void ladeDaten() {

	}

	@FXML
	private void speichern() {

	}

	@FXML
	private void bearbeiten() {

	}

	@FXML
	private void loeschen() {

	}

}