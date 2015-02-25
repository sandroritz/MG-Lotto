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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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


import ch.mgeggishorn.logger.Log;
import ch.mgeggishorn.logger.LogView;
import ch.mgeggishorn.logger.Logger;
import ch.mgeggishorn.model.CurrentSpielerModel;
import ch.mgeggishorn.model.RundenModel;
import ch.mgeggishorn.model.SerieModel;
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
	private TableColumn<SpielerModel, String> colStrasse;
	@FXML
	private TableColumn<SpielerModel, String> colPlz;
	@FXML
	private TableColumn<SpielerModel, String> colOrt;
	
	//TableAllSpieler
	@FXML
	private TableColumn<SpielerModel, String> colName2;
	@FXML
	private TableColumn<SpielerModel, String> colVorname2;
	@FXML
	private TableColumn<SpielerModel, String> colStrasse2;
	@FXML
	private TableColumn<SpielerModel, String> colPlz2;
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
	
	
	
	//Serienverwaltung
	@FXML
	private TreeView treeviewRunden;
	private List<SerieModel> serien;
	private List<RundenModel> allRunden;
	
	@FXML
	private ComboBox<String> rundeWaehlenCombo;
	@FXML
	private ComboBox<String> preisWaehlenCombo;
	@FXML
	private ComboBox<String> serieWaehlenCombo;
	@FXML
	private ComboBox<String> serieWaehlen2Combo;
	@FXML
	private ComboBox<String> rundentypWaehlenCombo;
	@FXML
	private ComboBox<String> rundeWaehlen2Combo;
	@FXML
	private ComboBox<String> rundeWaehlen3Combo;
	@FXML
	private ComboBox<String> rundeWaehlen4Combo;
	@FXML
	private ComboBox<String> neuerRundentypWaehlenCombo;
	@FXML
	private TextField txtPreisErstellen;
	@FXML
	private ComboBox<String> preisWaehlen2Combo;
	@FXML
	private ComboBox<String> preisWaehlen3Combo;
	@FXML
	private TextField txtPreisBearbeiten;
	
	//Logging
	Log log = new Log();
	Logger logger = new Logger(log, "MG-Eggishorn: ");
	enum Level { DEBUG, INFO, WARN, ERROR };
	
	
    public void initialize(URL url, ResourceBundle rb) {
    	
logger.info("Test");
    	
    	spieler = null;
    	selectedSpieler = null;
   
    	createTree();
    	initializeCombos();
    	
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
		colStrasse2.setCellValueFactory(new PropertyValueFactory<SpielerModel, String>(
				"strasse"));
		colPlz2.setCellValueFactory(new PropertyValueFactory<SpielerModel, String>(
				"plz"));
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
		colStrasse.setCellValueFactory(new PropertyValueFactory<SpielerModel, String>(
				"strasse"));
		colPlz.setCellValueFactory(new PropertyValueFactory<SpielerModel, String>(
				"plz"));
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
		
		rundeWaehlenCombo.valueProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue ov, String t, String selected) {
	              System.out.println(selected);
	              serieWaehlen2Combo.getItems().clear();
	              DBManager dbm = new DBManager();
		          	try{
		          		List<String> noUsedSerieNr = dbm.getNoUsedSerieNr(selected);
		          		serieWaehlen2Combo.getItems().addAll(noUsedSerieNr);
		  
		          	
		          	
		          	} catch(Exception e){
		          		e.printStackTrace();
		          	}
	          }    
	      });
		 rundeWaehlen4Combo.valueProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue ov, String t, String selected) {
	              System.out.println(selected);
	              serieWaehlenCombo.getItems().clear();
	              DBManager dbm = new DBManager();
		          	try{
		          		List<String> usedSerieNr = dbm.getUsedSerieNr(selected);
		          		serieWaehlenCombo.getItems().addAll(usedSerieNr);
		  
		          	
		          	
		          	} catch(Exception e){
		          		e.printStackTrace();
		          	}
	          }    
	      });
		 preisWaehlen2Combo.valueProperty().addListener(new ChangeListener<String>() {
		        @Override public void changed(ObservableValue ov, String t, String selectedPreis) {
		              System.out.println(selectedPreis);
		              txtPreisBearbeiten.setText(selectedPreis);     	
		        }
		      });
		 
		 startLogger();
		
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
	
	//Spiel starten
	
	@FXML
	private void starteSpiel(){
	
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
    
    
    //Serienverwaltung Funktionen
    
	public void createTree() {
		List<TreeItem<String>> serienForCurrentRunde = new ArrayList<TreeItem<String>>();
		
		DBManager dbm = new DBManager();
    	try{
    		serien = dbm.getAllSerien();
    		
    		
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	int maxRunden =dbm.getMaxRundenNr();
    	System.out.println(maxRunden);
    	int rundeNr = 1;
    	
    	//List[] runden = new List[maxRunden];
    	List<ArrayList> runden = new ArrayList<ArrayList>();
    	for(int i =0; i <=maxRunden;i++)
    	{
    		runden.add(new ArrayList<>());
    	}
    	
   
    	
  
    	
    	for(int i = rundeNr; i <= maxRunden;i++){
    		serienForCurrentRunde = null;
			serienForCurrentRunde = new ArrayList<TreeItem<String>>();
			int counter = 0; //counter zeigt auf SerienNr
    		for (SerieModel serie : serien) {
    			
				//System.out.println("Runde Nr: " + i);
					if(serie.getRundeNr() == i){
						serienForCurrentRunde.add(new TreeItem<String>("SerienNr: " +serie.getSerieNr()));
						serienForCurrentRunde.get(counter).getChildren().add((new TreeItem<String>("Preis: " +serie.getPreis()))); //Preise von Serien hinzufügen
						counter++; //nächste Serie durchlaufen
					}
					else{
						//System.out.println("Falsche Runde: " + serie.getRundeNr());
					}
					
					
			}
    		 

			runden.set(i, (ArrayList) serienForCurrentRunde);
		}
    	
    	
    	List<TreeItem<String>> rundenList = new ArrayList<TreeItem<String>>();
    	TreeItem<String> root = new TreeItem<>("Runden");
	    
	    
	    for(int j = 1; j <= maxRunden; j++){
	    	String rundenTypName = dbm.getRundenTypById(j);

	    	rundenList.add(new TreeItem<String>("RundenNr: " + j + " ("+rundenTypName+")")); //Runden Menuepunkte werden erstellt
	    	rundenList.get(j-1).getChildren().addAll(runden.get(j)); //Serien werden den Runden hinzugefügt
	    }
	    
	    root.setExpanded(true);;
		root.getChildren().addAll(rundenList);
		
		
		
	    treeviewRunden.setRoot(root);
	}
	
	public void initializeCombos(){
		DBManager dbm = new DBManager();
    	try{
    		List<String> rundenNr = dbm.getRunden();
    		rundeWaehlenCombo.getItems().addAll(rundenNr);
    		rundeWaehlen2Combo.getItems().addAll(rundenNr);
    		rundeWaehlen3Combo.getItems().addAll(rundenNr);
    		rundeWaehlen4Combo.getItems().addAll(rundenNr);
    		
    		List<String> preise = dbm.getPreise();
    		preisWaehlenCombo.getItems().addAll(preise);
    		preisWaehlen2Combo.getItems().addAll(preise);
    		preisWaehlen3Combo.getItems().addAll(preise);

    		List<String> rundenTyp = dbm.getRundentyp();
    		rundentypWaehlenCombo.getItems().addAll(rundenTyp);
    		neuerRundentypWaehlenCombo.getItems().addAll(rundenTyp);
    		
    		
    	} catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	@FXML
	private void addSerie(){
		DBManager dbm = new DBManager();
		if(rundeWaehlenCombo.getSelectionModel().getSelectedItem() != "Runde wählen" || preisWaehlenCombo.getSelectionModel().getSelectedItem() != "Preis wählen" || serieWaehlen2Combo.getSelectionModel().getSelectedItem() !="Serie wählen"){
    	try{    		
    		dbm.addSerie(rundeWaehlenCombo.getSelectionModel().getSelectedItem(), serieWaehlen2Combo.getSelectionModel().getSelectedItem(), preisWaehlenCombo.getSelectionModel().getSelectedItem());
    		clearCombos();
    		initializeCombos();
    		createTree();
    	} catch(Exception e){
    		e.printStackTrace();
    	}}
		else{
			System.out.println("Runde, Preis und Serie wählen!");
		}
	}
	

	@FXML
	private void deleteSerie(){
		DBManager dbm = new DBManager();
    	try{    	
    		dbm.deleteSerie(serieWaehlenCombo.getSelectionModel().getSelectedItem(), rundeWaehlen4Combo.getSelectionModel().getSelectedItem());
    		clearCombos();
    		initializeCombos();
    		createTree();
    	} catch(Exception e){
    		e.printStackTrace();
    	}
	}
	@FXML
	private void addRunde(){
		DBManager dbm = new DBManager();
    	try{    	
    		dbm.addRunde(rundentypWaehlenCombo.getSelectionModel().getSelectedItem());
    		clearCombos();
    		initializeCombos();
    		createTree();
    	} catch(Exception e){
    		e.printStackTrace();
    	}
	}
	@FXML
	private void deleteRunde(){
//		DBManager dbm = new DBManager();
//
//    	try{    	
//    		int maxRunde = dbm.getMaxRundenNr();
//
//    		if((rundeWaehlen2Combo.getSelectionModel().getSelectedItem()) == (String.valueOf(maxRunde))){
//	    		dbm.deleteRunde(rundeWaehlen2Combo.getSelectionModel().getSelectedItem());
//	    		clearCombos();
//	    		initializeCombos();
//	    		createTree();
//    		}
//    	} catch(Exception e){
//    		e.printStackTrace();
//    	}
		
	}
	@FXML
	private void changeTyp(){
		DBManager dbm = new DBManager();
    	try{    	
    		dbm.changeRundenTyp(rundeWaehlen3Combo.getSelectionModel().getSelectedItem(), neuerRundentypWaehlenCombo.getSelectionModel().getSelectedItem());
    		clearCombos();
    		initializeCombos();
    		createTree();
    	} catch(Exception e){
    		e.printStackTrace();
    	}
	}
	@FXML
	private void addPreis(){
		DBManager dbm = new DBManager();
    	try{
    		dbm.addPreis(txtPreisErstellen.getText());
    		clearCombos();
    		initializeCombos();
    		createTree();
    	} catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	@FXML
	private void editPreis(){
		DBManager dbm = new DBManager();
    	try{    	
    		dbm.editPreis(preisWaehlen2Combo.getSelectionModel().getSelectedItem(),txtPreisBearbeiten.getText());
    		clearCombos();
    		initializeCombos();
    		createTree();
    	} catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	@FXML
	private void deletePreis(){
		DBManager dbm = new DBManager();
    	try{    	
    		dbm.deletePreis(preisWaehlen3Combo.getSelectionModel().getSelectedItem());
    		clearCombos();
    		initializeCombos();
    		createTree();
    	} catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	
	private void clearCombos() {
		// TODO Auto-generated method stub
		rundeWaehlenCombo.getItems().clear();
		preisWaehlenCombo.getItems().clear();
		preisWaehlen2Combo.getItems().clear();
		preisWaehlen3Combo.getItems().clear();
		serieWaehlenCombo.getItems().clear();
		serieWaehlen2Combo.getItems().clear();
		rundentypWaehlenCombo.getItems().clear();
		rundeWaehlen2Combo.getItems().clear();
		rundeWaehlen3Combo.getItems().clear();
		rundeWaehlen4Combo.getItems().clear();
		neuerRundentypWaehlenCombo.getItems().clear();
		txtPreisErstellen.clear();
		txtPreisBearbeiten.clear();
		rundeWaehlenCombo.setValue(null);
		preisWaehlenCombo.setValue(null);
		preisWaehlen2Combo.setValue(null);
		preisWaehlen3Combo.setValue(null);
		serieWaehlenCombo.setValue(null);
		serieWaehlen2Combo.setValue(null);
		rundentypWaehlenCombo.setValue(null);
		rundeWaehlen2Combo.setValue(null);
		rundeWaehlen3Combo.setValue(null);
		rundeWaehlen4Combo.setValue(null);
		neuerRundentypWaehlenCombo.setValue(null);
		
	}
	
	//Logger
	private void startLogger() {
		// TODO Auto-generated method stub
		
		Stage logStage = new Stage();
		
		

		logger.info("Hallo - Hier werden alle Aktionen geloggt.");

		

		LogView logView = new LogView(logger);
		logView.setPrefWidth(1000);
		logView.setPrefHeight(150);

		ChoiceBox<Level> filterLevel = new ChoiceBox<>(
				FXCollections.observableArrayList(Level.values()));
		filterLevel.getSelectionModel().select(Level.DEBUG);
		/*logView.filterLevelProperty().bind(
				filterLevel.getSelectionModel().selectedItemProperty());
*/
		ToggleButton showTimestamp = new ToggleButton("Zeige Zeitstempel");
		logView.showTimeStampProperty().bind(showTimestamp.selectedProperty());

		HBox controls = new HBox(10, filterLevel, showTimestamp);
		controls.setMinHeight(HBox.USE_PREF_SIZE);

		VBox layout = new VBox(10, controls, logView);
		VBox.setVgrow(logView, Priority.ALWAYS);

		Scene scene = new Scene(layout);
		scene.getStylesheets().add(
				this.getClass().getResource("/log-view.css").toExternalForm());
		logStage.setScene(scene);
		logStage.getIcons().add(new Image("/mg-logo.jpg"));
		logStage.setTitle("Lotto - Log");
		logStage.show();
	}
	
	

}