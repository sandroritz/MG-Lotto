package ch.mgeggishorn.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class SerieModel {
	
	private SimpleIntegerProperty serieNr;
	private SimpleIntegerProperty rundeNr;
	private SimpleIntegerProperty gewinnerId;
	private SimpleStringProperty preis;
	private SimpleStringProperty rundenTyp;


	
	public SerieModel(int serieNr, int rundeNr, String preis, String rundenTyp) {
		this.serieNr = new SimpleIntegerProperty(serieNr);
		this.rundeNr = new SimpleIntegerProperty(rundeNr);
		this.preis = new SimpleStringProperty(preis);
		this.rundenTyp = new SimpleStringProperty(rundenTyp);
	}
	
	

	public int getSerieNr() {
		return serieNr.get();
	}


	public void setSerieNr(int serieNr) {
		this.serieNr.set(serieNr);
	}
	
	public int getRundeNr() {
		return rundeNr.get();
	}


	public void setRundeNr(int rundeNr) {
		this.rundeNr.set(rundeNr);
	}


	public int getGewinnerId() {
		return gewinnerId.get();
	}


	public void setGewinnerId(int gewinnerId) {
		this.gewinnerId.set(gewinnerId);
	}


	public String getPreis() {
		return preis.get();
	}


	public void setPreis(String preis) {
		this.preis.set(preis);
	}
	
	public String getRundentyp() {
		return rundenTyp.get();
	}


	public void setRundentyp(String rundenTyp) {
		this.rundenTyp.set(rundenTyp);
	}


}
