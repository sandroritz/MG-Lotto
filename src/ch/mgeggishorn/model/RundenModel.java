package ch.mgeggishorn.model;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class RundenModel {
	private SimpleIntegerProperty rundenNr;
	private List<SerieModel> serien;
	private SimpleStringProperty rundenTyp;
	
	public RundenModel(int rundenNr, List<SerieModel> serien, String rundenTyp) {
		this.rundenNr = new SimpleIntegerProperty(rundenNr);
		this.serien= serien;
		this.rundenTyp = new SimpleStringProperty(rundenTyp);
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
	


	
	