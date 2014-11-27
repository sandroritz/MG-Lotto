package ch.mgeggishorn.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class SpielerModel {
	private StringProperty name;
	private StringProperty vorname;
	private StringProperty strasse;
	private IntegerProperty plz;
	private StringProperty ort;
	
	public SpielerModel(StringProperty name, StringProperty vorname,
			StringProperty strasse, IntegerProperty plz, StringProperty ort) {

		this.name = name;
		this.vorname = vorname;
		this.strasse = strasse;
		this.plz = plz;
		this.ort = ort;
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public StringProperty getVorname() {
		return vorname;
	}

	public void setVorname(StringProperty vorname) {
		this.vorname = vorname;
	}

	public StringProperty getStrasse() {
		return strasse;
	}

	public void setStrasse(StringProperty strasse) {
		this.strasse = strasse;
	}

	public IntegerProperty getPlz() {
		return plz;
	}

	public void setPlz(IntegerProperty plz) {
		this.plz = plz;
	}

	public StringProperty getOrt() {
		return ort;
	}

	public void setOrt(StringProperty ort) {
		this.ort = ort;
	}
	
	
	
}
