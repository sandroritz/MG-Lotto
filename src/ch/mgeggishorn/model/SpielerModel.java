package ch.mgeggishorn.model;


public class SpielerModel {
	private String name;
	private String vorname;
	private String strasse;
	private int plz;
	private String ort;
	
	public SpielerModel(String name, String vorname,
			String strasse, int plz, String ort) {

		this.name = name;
		this.vorname = vorname;
		this.strasse = strasse;
		this.plz = plz;
		this.ort = ort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	
}
