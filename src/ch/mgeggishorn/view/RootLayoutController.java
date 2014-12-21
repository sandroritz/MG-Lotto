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

	
	//Suche
	@FXML
	private TextField txtSuche;
	
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
	
	private boolean disable = true;
	
	// TableView

	ObservableList<SpielerModel> data;
	List<SpielerModel> spieler;

	@FXML
	private TableView<SpielerModel> tblOverview;

	@FXML
	private TableColumn<SpielerModel, String> colName;
	@FXML
	private TableColumn<SpielerModel, String> colVorname;
	@FXML
	private TableColumn<SpielerModel, String> colOrt;


	
    public void initialize(URL url, ResourceBundle rb) {
      
    	spieler = null;
   
    	
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
	                  selectedSpieler= dbm.getDetailOfSpieler(newVal.getId());
	                  
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
		
		
		txtSuche.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		       System.out.println(newValue);
		       
		       ObservableList<SpielerModel> searchData = null;
		    
		       List<SpielerModel> searchedSpieler = null;
		       
		       
		       searchedSpieler= dbm.getSpielerByName(newValue);
		       searchData = FXCollections.observableArrayList(searchedSpieler);
		       tblOverview.getItems().clear();
		       if(searchedSpieler != null ){
		    	   tblOverview.setItems(searchData);
		       }
		       else{
		    	   System.out.println("Kein Treffer...");
		       }
		      
              
		    
		    }
		});
    }
	
	@FXML
	private void neuSpieler() {
		
	}
	@FXML
	private void ladeDaten() {
		refreshTableView();
		txtSuche.setText("");
	}

	@FXML
	private void speichern() {
		if(disable ==false){
			DBManager dbm = new DBManager();
			SpielerModel spieler = new SpielerModel(Integer.parseInt(txtId.getText()), txtName.getText(), txtVorname.getText(), txtStrasse.getText(), Integer.parseInt(txtPlz.getText()), txtOrt.getText());
	    	try{
	    		dbm.updateSpieler(spieler);
	    		disable = true;
	    		refreshTableView();
	    		
	    		//Detailfelder füllen
                txtId.setText(String.valueOf(spieler.getId()));
                txtName.setText(spieler.getName());
                txtVorname.setText(spieler.getVorname());
                txtStrasse.setText(spieler.getStrasse());
                txtPlz.setText(String.valueOf(spieler.getPlz()));
                txtOrt.setText(spieler.getOrt());
	    		
                setDisableTrue();
	    	} catch(Exception e){
	    		e.printStackTrace();
	    	}
		}
		
	}



	@FXML
	private void bearbeiten() {
		setDisableFalse();
	}	



	@FXML
	private void loeschen() {
			DBManager dbm = new DBManager();
	    	try{
	    		dbm.deleteSpieler(Integer.parseInt(txtId.getText()));
                clearDetail();
                refreshTableView();  
                setDisableFalse();
	    	} catch(Exception e){
	    		e.printStackTrace();
	    	}
	}
	
	
	
	
	
	
	
	
	
	//Helper Methoden
	private void setDisableTrue() {
		disable =true;
		txtName.setDisable(true);
		txtVorname.setDisable(true);
        txtStrasse.setDisable(true);
        txtPlz.setDisable(true);
        txtOrt.setDisable(true);
		
	}

	private void setDisableFalse() {
		disable =false;
        txtName.setDisable(false);
		txtVorname.setDisable(false);
        txtStrasse.setDisable(false);
        txtPlz.setDisable(false);
        txtOrt.setDisable(false);
	}
	
    private void refreshTableView(){
    	spieler = null;
    	
    	DBManager dbm = new DBManager();
    	
    	try{
    		spieler = dbm.getAllSpielerOverView();
    	} catch(Exception e){
    		e.printStackTrace();
    	}

    	data = FXCollections.observableArrayList(spieler);
    	tblOverview.setItems(data);
    }
    
    private void clearDetail(){
    	txtId.setText("");
        txtName.setText("");
        txtVorname.setText("");
        txtStrasse.setText("");
        txtPlz.setText("");
        txtOrt.setText("");
    }

}