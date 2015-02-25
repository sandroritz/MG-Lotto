package ch.mgeggishorn.model;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class RundenModel {
	private SimpleIntegerProperty rundenNr;
	private SimpleStringProperty rundenTyp;
	
	public RundenModel(int rundenNr, String rundenTyp) {
		this.rundenNr = new SimpleIntegerProperty(rundenNr);
		this.rundenTyp = new SimpleStringProperty(rundenTyp);
	}
	

	public int getRundenNr() {
		return rundenNr.get();
	}
	public void setRundenNr(int rundenNr) {
		this.rundenNr.set(rundenNr);
	}
	
	public String getRundenTyp() {
		return rundenTyp.get();
	}
	public void setRundenTyp(String rundenTyp) {
		this.rundenTyp.set(rundenTyp);
	}



}
	


	
	