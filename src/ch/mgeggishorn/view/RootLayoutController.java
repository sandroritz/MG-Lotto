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
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ch.mgeggishorn.controller.DBManager;


import ch.mgeggishorn.model.CurrentSpielerModel;
import ch.mgeggishorn.model.SpielerModel;

public class RootLayoutController implements Initializable {

	//Tab
	@FXML
	private TabPane menutab;
	
	
	//Suche
	@FXML
	private TextField txtSuche;
	
	@FXML
	private TextField txtSucheSelect;
	
	
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
	
	
	//Splitpane
	@FXML
	private SplitPane mainSplitPane;
	// -> Anchorpane 2
	@FXML
	private AnchorPane detailAnchorPane;
	
	// TableView

	ObservableList<SpielerModel> data;
	ObservableList<CurrentSpielerModel> selectedData;
	List<SpielerModel> spieler;
	List<CurrentSpielerModel> selectedSpieler;

	@FXML
	private TableView<SpielerModel> tblOverview;
	
	@FXML
	private TableView<SpielerModel> tblAllSpieler;
	
	@FXML
	private TableView<CurrentSpielerModel> tblSelectedSpieler;
	
	

	
	@FXML
	private TableColumn<SpielerModel, String> colName;
	@FXML
	private TableColumn<SpielerModel, String> colVorname;
	@FXML
	private TableColumn<SpielerModel, String> colOrt;
	
	//TableAllSpieler
	@FXML
	private TableColumn<SpielerModel, String> colName2;
	@FXML
	private TableColumn<SpielerModel, String> colVorname2;
	@FXML
	private TableColumn<SpielerModel, String> colOrt2;
	
	//tblSelectedSpieler
	@FXML
	private TableColumn<CurrentSpielerModel, String> colName3;
	@FXML
	private TableColumn<CurrentSpielerModel, String> colVorname3;
	@FXML
	private TableColumn<CurrentSpielerModel, String> colOrt3;
	@FXML
	private TableColumn<CurrentSpielerModel, String> colKarte;
		
		


	@FXML
	private ToggleGroup radioKarten;
	@FXML
	private RadioButton radiokarte1;
	@FXML
	private RadioButton radiokarte3;
	@FXML
	private RadioButton radiokarte8;
	@FXML
	private RadioButton radiokarte16;
	
	
    public void initialize(URL url, ResourceBundle rb) {
    	
    	spieler = null;
    	selectedSpieler = null;
   
    	
    	DBManager dbm = new DBManager();
    	try{
    		spieler = dbm.getAllSpielerOverView();
    		selectedSpieler = dbm.getAllCurrentSpieler();
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    	data = FXCollections.observableArrayList(spieler);
    	selectedData = FXCollections.observableArrayList(selectedSpieler);
    	
    	menutab.getSelectionModel().selectedItemProperty().addListener(
    		    new ChangeListener<Tab>() {
    		        @Override
    		        public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
    		            System.out.println("Tab Selection changed");
    		            refreshAllSpielerTableView();
    		            refreshCurrentSpielerTableView();
    		        }
    		    }
    			);
    	
    	colName2.setCellValueFactory(new PropertyValueFactory<SpielerModel, String>(
				"name"));
		colVorname2.setCellValueFactory(new PropertyValueFactory<SpielerModel, String>(
						"vorname"));
		colOrt2.setCellValueFactory(new PropertyValueFactory<SpielerModel, String>(
				"ort"));
		
		tblAllSpieler.setItems(data);
		
		
		
		colName3.setCellValueFactory(new PropertyValueFactory<CurrentSpielerModel, String>(
				"name"));
		colVorname3.setCellValueFactory(new PropertyValueFactory<CurrentSpielerModel, String>(
						"vorname"));
		colOrt3.setCellValueFactory(new PropertyValueFactory<CurrentSpielerModel, String>(
				"ort"));
		colKarte.setCellValueFactory(new PropertyValueFactory<CurrentSpielerModel, String>(
				"karten"));
		
		
		tblSelectedSpieler.setItems(selectedData);
		
		

    	
    	
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
	            	clearDetail();
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
		
		txtSucheSelect.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		       System.out.println(newValue);
		       
		       ObservableList<SpielerModel> searchData = null;
		    
		       List<SpielerModel> searchedSpieler = null;
		       
		       
		       searchedSpieler= dbm.getSpielerByName(newValue);
		       searchData = FXCollections.observableArrayList(searchedSpieler);
		       tblAllSpieler.getItems().clear();
		       if(searchedSpieler != null ){
		    	   tblAllSpieler.setItems(searchData);
		       }
		       else{
		    	   System.out.println("Kein Treffer...");
		       }
		      
              
		    
		    }
		});
    }
	
	@FXML
	private void neuSpieler() throws IOException {
	    AnchorPane neuerSpielerView = (AnchorPane)FXMLLoader.load(getClass().getResource("NeuerSpielerView.fxml"));
	    mainSplitPane.getItems().set(1, neuerSpielerView);
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
	
	
	
	@FXML
	private void addCurrentSpieler() {
	   int selectedSpielerId = tblAllSpieler.getSelectionModel().getSelectedItem().getId();
	   DBManager dbm = new DBManager();
	   SpielerModel detailSpieler = (SpielerModel) dbm.getDetailOfSpieler(selectedSpielerId).get(0);
	   int anzKarten =  getSelectedKartenRadio();
	   System.out.println(anzKarten);
	  
		CurrentSpielerModel currentSpieler = new CurrentSpielerModel(detailSpieler.getId(), detailSpieler.getName(), detailSpieler.getVorname(),
				detailSpieler.getStrasse(), detailSpieler.getPlz(), detailSpieler.getOrt(), anzKarten);

			    dbm.insertCurrentSpieler(currentSpieler);
			    refreshCurrentSpielerTableView();
	}
	
	@FXML
	private void deleteCurrentSpieler(){
		int id = 0;
		id=tblSelectedSpieler.getSelectionModel().getSelectedItem().getId();
		DBManager dbm = new DBManager();
    	try{
    		dbm.deleteCurrentSpieler(id);
    		id--;
    		refreshCurrentSpielerTableView();
    		if(!tblSelectedSpieler.getSelectionModel().isEmpty()){
    			tblSelectedSpieler.getSelectionModel().selectFirst();
    		}
    	
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
  
    private void refreshAllSpielerTableView(){
	    spieler = null;
    	DBManager dbm = new DBManager();
    	
    	try{
    		spieler = dbm.getAllSpielerOverView();
    	} catch(Exception e){
    		e.printStackTrace();
    	}

    	data = FXCollections.observableArrayList(spieler);
    	tblAllSpieler.setItems(data);
    }
    
    
    private void refreshCurrentSpielerTableView(){
	    selectedSpieler = null;
    	DBManager dbm = new DBManager();
    	
    	try{
    		selectedSpieler = dbm.getAllCurrentSpieler();
    	} catch(Exception e){
    		e.printStackTrace();
    	}

    	selectedData = FXCollections.observableArrayList(selectedSpieler);
    	tblSelectedSpieler.setItems(selectedData);
    }
    
    private void clearDetail(){
    	txtId.setText("");
        txtName.setText("");
        txtVorname.setText("");
        txtStrasse.setText("");
        txtPlz.setText("");
        txtOrt.setText("");
    }
   
    
    private int getSelectedKartenRadio(){
    	int selectedKarte = 0;
    	RadioButton selectedRadio = (RadioButton) radioKarten.getSelectedToggle();
    	selectedKarte = Integer.parseInt(selectedRadio.getId());
    	return selectedKarte;
    }

}