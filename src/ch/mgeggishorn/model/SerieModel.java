package ch.mgeggishorn.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class SerieModel {
	private SimpleIntegerProperty id;
	private SimpleIntegerProperty serieNr;
	private SimpleIntegerProperty gewinnerId;
	private SimpleStringProperty preis;


	
	public SerieModel(int id, int serieNr,
			int gewinnerId, String preis) {
	
		this.id = new SimpleIntegerProperty(id);
		this.serieNr = new SimpleIntegerProperty(serieNr);
		this.gewinnerId = new SimpleIntegerProperty(gewinnerId);
		this.preis = new SimpleStringProperty(preis);
	}
	
	
	public void setId(int id) {
		this.id.set(id);
	}
	
	public int getSerieNr() {
		return serieNr.get();
	}


	public void setSerieNr(int serieNr) {
		this.serieNr.set(serieNr);
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


}
