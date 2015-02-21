package ch.mgeggishorn.model;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class RundenModel {
	private SimpleIntegerProperty id;
	private SimpleIntegerProperty rundenNr;
	private List<SerieModel> serien;
	private SimpleStringProperty rundenTyp;
	
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);
	}
	public int getRundenNr() {
		return rundenNr.get();
	}
	public void setRundenNr(int rundenNr) {
		this.rundenNr.set(rundenNr);
	}
	public List<SerieModel> getSerien() {
		return serien;
	}
	public void setSerien(List<SerieModel> serien) {
		this.serien = serien;
	}
	public String getRundenTyp() {
		return rundenTyp.get();
	}
	public void setRundenTyp(String rundenTyp) {
		this.rundenTyp.set(rundenTyp);
	}



}
	


	
	