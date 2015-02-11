package ch.mgeggishorn.model;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class CurrentSpielerModel {
	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private SimpleStringProperty vorname;
	private SimpleStringProperty strasse;
	private SimpleIntegerProperty plz;
	private SimpleStringProperty ort;
	private SimpleIntegerProperty karten;
	private List<Integer> lottozahlen;
	
	public CurrentSpielerModel(int id, String name, String vorname,
			String strasse, int plz, String ort, int karten) {
		this.id= new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.vorname = new SimpleStringProperty(vorname);
		this.strasse = new SimpleStringProperty(strasse);
		this.plz = new SimpleIntegerProperty(plz);
		this.ort = new SimpleStringProperty(ort);
		this.karten= new SimpleIntegerProperty(karten);
	}
	public CurrentSpielerModel(int id, String name, String vorname,String ort, int karten) {
		this.id= new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.vorname = new SimpleStringProperty(vorname);
		this.ort = new SimpleStringProperty(ort);
		this.karten= new SimpleIntegerProperty(karten);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getVorname() {
		return vorname.get();
	}

	public void setVorname(String vorname) {
		this.vorname.set(vorname);
	}

	public String getStrasse() {
		return strasse.get();
	}

	public void setStrasse(String strasse) {
		this.strasse.set(strasse);
	}

	public int getPlz() {
		return plz.get();
	}

	public void setPlz(int plz) {
		this.plz.set(plz);
	}

	public String getOrt() {
		return ort.get();
	}

	public void setOrt(String ort) {
		this.ort.set(ort);
	}
	
	public int getKarten() {
		return karten.get();
	}

	public void setKarten(int karten) {
		this.karten.set(karten);
	}

	public List<Integer> getLottozahlen() {
		return lottozahlen;
	}
	public void setLottozahlen(List<Integer> lottozahlen) {
		this.lottozahlen = lottozahlen;
	}
	

	
}
