package ch.mgeggishorn.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ch.mgeggishorn.model.SerieModel;

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
	private Label lblRundentyp;
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
	String rundenTyp = "";

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
			rundenTyp = serienliste.get(countCurrentSerie).getRundentyp();
			if(rundenTyp.equals("Saalrunde")){
				System.out.println(rundenTyp);
				System.out.println("in");
				txtGetrillteeZahl.setDisable(true);
				btnZahlBestaetigen.setDisable(true);
				txtManuellGewinner.setDisable(false);
				cboxManuell.selectedProperty().set(true);
				cboxManuell.setDisable(false);
				lblGewinner.setText("Gewinner:");
				refreshStatusLabels(rundenNr, serienNr, preis, rundenTyp);
			}
			else{
				refreshStatusLabels(rundenNr, serienNr, preis, rundenTyp);
			}
			
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
				refreshView();
			} else {
				System.out.println("Alle Serien gespielt");
			}
		} else if (!siegerIsSet && txtManuellGewinner.getLength() > 6) {
			DBManager dbm = new DBManager();
			dbm.setManuellerGewinner(txtManuellGewinner.getText(), rundenNr,
					serienNr);

			countCurrentSerie++;

			if (countCurrentSerie < maxSerien) {
				refreshView();
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
				"/ch/mgeggishorn/view/RootLayoutView.fxml"));

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
				"/ch/mgeggishorn/view/RemoveSpieler.fxml"));
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

	private void refreshStatusLabels(int rundenNr, int serienNr, String preis, String rundenTyp) {
		// TODO Auto-generated method stub
		lblRundenNr.setText("Runden Nr: " + rundenNr);
		lblSerieNr.setText("Serien Nr: " + serienNr);
		lblPreis.setText("Preis: " + preis);
		lblRundentyp.setText("Rundentyp: " + rundenTyp);
	}

	private void refreshView() {
		rundenNr = serienliste.get(countCurrentSerie).getRundeNr();
		serienNr = serienliste.get(countCurrentSerie).getSerieNr();
		preis = serienliste.get(countCurrentSerie).getPreis();
		rundenTyp = serienliste.get(countCurrentSerie).getRundentyp();
		if(rundenTyp.equals("Saalrunde")){
			System.out.println("in");
			txtGetrillteeZahl.setDisable(true);
			btnZahlBestaetigen.setDisable(true);
			txtManuellGewinner.setDisable(false);
			cboxManuell.selectedProperty().set(true);
			cboxManuell.setDisable(false);
			txtManuellGewinner.setText("");
			anzVersuche = 0;
			lblGewinner.setText("Gewinner:");
			lblAnzVersuche.setText("Anz. Versuche: 0");
			refreshStatusLabels(rundenNr, serienNr, preis, rundenTyp);
		}
		else{
			refreshStatusLabels(rundenNr, serienNr, preis, rundenTyp);
			// Labels clear
			txtGetrillteeZahl.setDisable(false);
			btnZahlBestaetigen.setDisable(false);
			txtGetrillteeZahl.setText("");
			lblGewinner.setText("Gewinner:");
			txtManuellGewinner.setText("");
			anzVersuche = 0;
			lblAnzVersuche.setText("Anz. Versuche: 0");
			txtManuellGewinner.setDisable(false);
			cboxManuell.setDisable(false);
			cboxManuell.selectedProperty().set(false);
		}
		

	}

	@FXML
	private void gewinnerExportieren() {
		generatePDF();
	}

	
	@SuppressWarnings("deprecation")
	private static String FILE = "c:/temp/mglotto_gewinnerliste_"
			+ String.valueOf(new Date().getYear() + 1900) + ".pdf";
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);

	private void generatePDF() {
		// PDF Generator

		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addMetaData(document);
			addTitlePage(document);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private static void addMetaData(Document document) {

		document.addTitle("MG Eggishorn - Lotto");
		document.addSubject("Gewinnerliste "
				+ String.valueOf(new Date().getYear() + 1900));
		document.addAuthor("MG Eggishorn");
		document.addCreator("MG Eggishorn");
	}

	private static void addTitlePage(Document document)
			throws DocumentException {
		Paragraph preface = new Paragraph();
		// We add one empty line
		addEmptyLine(preface, 1);
		// Lets write a big header
		preface.add(new Paragraph("MG Eggishorn - Lotto", catFont));

		addEmptyLine(preface, 1);
		// Will create: Report generated by: _name, _date
		preface.add(new Paragraph("Report vom: " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				smallBold));
		addEmptyLine(preface, 2);
		preface.add(new Paragraph("Gewinnerliste", subFont));

		addEmptyLine(preface, 2);
		document.add(preface);
		createTable(document);

	}

	private static void createTable(Document document) throws DocumentException {

		Chunk trennlinie = new Chunk(new LineSeparator(0.5f, 100,
				BaseColor.BLACK, Element.ALIGN_CENTER, 3.5f));

		DBManager dbm = new DBManager();
		List<String> gewinnerlist = dbm.getAllGewinner();
		Paragraph pg;
		int i = 0;
		for (String gewinner : gewinnerlist) {
			pg = new Paragraph(gewinner, redFont);

			if (i % 2 == 0) {
				pg = new Paragraph(gewinner, smallBold);
				document.add(trennlinie);
			}
			i++;
			document.add(pg);

		}
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

}