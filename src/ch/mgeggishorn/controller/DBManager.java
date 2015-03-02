package ch.mgeggishorn.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.mgeggishorn.model.CurrentSpielerModel;
import ch.mgeggishorn.model.RundenModel;
import ch.mgeggishorn.model.SerieModel;
import ch.mgeggishorn.model.SpielerModel;

public class DBManager {
	private static Connection con;
	
	List<SpielerModel> spielerData = new ArrayList<>();
	List<SpielerModel> spielerDataOf = new ArrayList<>();
	List<CurrentSpielerModel> currentSpielerData = new ArrayList<>();
	List<String> rundenNr = new ArrayList<>();
	List<String> preise = new ArrayList<>();
	List<String> serien = new ArrayList<>();
	List<String> runden = new ArrayList<>();
	List<String> rundentypen = new ArrayList<>();
	List<Integer> fkpreis = new ArrayList<>();	
	List<Integer> rundenTyp = new ArrayList<>();
	List<Integer> neuerRundenTypen = new ArrayList<>();
	List<Integer> maxRundenNr = new ArrayList<>();
	List<Integer> countRundenNr = new ArrayList<>();
	
	List<Integer> usedSerieNr = new ArrayList<>();
	List<String> NoUsedSerieNr = new ArrayList<>();
	List<String> usedSerieNr2 = new ArrayList<>();
	
	//SpielenView
	int serienNr;
	String preis;
	
	
	
	
/**
 * Gibt die Daten von einem gewissen SpielerId zurueck
 * @param spieler
 * @return
 */
	public List<SpielerModel> getDetailOfSpieler(int spielerId) {

		try {
			spielerDataOf.clear();
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select * from spieler where id='"
					+ spielerId + "'");

			if (rs != null) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String vorname = rs.getString("vorname");
					String strasse = rs.getString("strasse");
					int plz = rs.getInt("plz");
					String ort = rs.getString("ort");
					spielerDataOf.add(new SpielerModel(id, name, vorname,
							strasse, plz, ort));
				}

			}
			/*
			 * for (SpielerModel spielerModel : spielerDataOf) {
			 * System.out.println(spielerModel.getId()); }
			 */
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return spielerDataOf;
	}

	/**
	 * Gibt Daten für TableView zuruck
	 * @return
	 */
	public List<SpielerModel> getAllSpielerOverView() {
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select id, name, vorname, strasse, plz, ort from spieler");
			if (rs != null) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String vorname = rs.getString("vorname");
					String strasse = rs.getString("strasse");
					int plz = rs.getInt("plz");
					String ort = rs.getString("ort");
					spielerData.add(new SpielerModel(id, name, vorname,strasse, plz, ort));
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return spielerData;
	}

	/**
	 * Spieler bearbeiten
	 * @param spieler
	 */
	public void updateSpieler(SpielerModel spieler) {
		// TODO Auto-generated method stub
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			String query = "update spieler set name='" + spieler.getName()
					+ "', vorname='" + spieler.getVorname() + "', strasse='"
					+ spieler.getStrasse() + "',plz=" + spieler.getPlz()
					+ ", ort='" + spieler.getOrt() + "' where id="
					+ spieler.getId();
			System.out.println(query);
			s.execute(query);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error updating Data");
		}
	}

	/**
	 * Neuen Spieler hinzufügen
	 * @param spieler
	 */
	public void insertSpieler(SpielerModel spieler) {
		// TODO Auto-generated method stub
		try {
			System.out.println("insertSpieler");
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			String query = "insert into spieler ('id','name', 'vorname', 'strasse', 'plz', 'ort') values ("
					+ spieler.getId()
					+ ", '"
					+ spieler.getName()
					+ "', '"
					+ spieler.getVorname()
					+ "', '"
					+ spieler.getStrasse()
					+ "', "
					+ spieler.getPlz()
					+ ", '" + spieler.getOrt() + "')";
			System.out.println(query);
			s.execute(query);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error inserting Data");
		}
	}

	public void deleteSpieler(int id) {
		// TODO Auto-generated method stub
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			String query = "delete from spieler where id="+id;
			System.out.println(query);
			s.execute(query);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error deleting Data");
		}
	}

	public List<SpielerModel> getSpielerByName(String searchQuery) {
		try {
			
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			System.out.println("select id, name, vorname, strasse, plz, ort from spieler where name like'%" + searchQuery + "%' or vorname like'%" + searchQuery + "%' or strasse like'%" + searchQuery + "%' or plz like'%" + searchQuery + "%' or ort like'%" + searchQuery + "%'");
			rs = s.executeQuery("select id, name, vorname, strasse, plz, ort from spieler where name like '%" + searchQuery + "%' or vorname like '%" + searchQuery + "%' or strasse like '%" + searchQuery + "%' or plz like '%" + searchQuery + "%' or ort like '%" + searchQuery + "%'");
			spielerData.clear();
			if (rs != null) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String vorname = rs.getString("vorname");
					String strasse = rs.getString("strasse");
					int plz = rs.getInt("plz");
					String ort = rs.getString("ort");
					spielerData.add(new SpielerModel(id, name, vorname, strasse, plz, ort));
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return spielerData;
	}
	
	public int getLastId(){
		int id = 0;
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select max(id) as id from spieler");
			
			if (rs != null) {
				
				while (rs.next()) {
					id= rs.getInt("id");
					System.out.println(rs.getInt("id"));
				}

			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return id;
	
	}


	
	/**
	 * Überprüfe ob Spieler schon vorhanden
	 * 
	 * 
	 * @param currentSpieler
	 */
	public void insertCurrentSpieler(CurrentSpielerModel currentSpieler) {
		
		
		// TODO Auto-generated method stub
				try {
					System.out.println("insertSpieler");
					con = DBConnector.getConnected();
					Statement s = con.createStatement();
					int id=0;
					ResultSet rs;
					rs = s.executeQuery("select fkSpieler from currentSpieler where fkSpieler=" + currentSpieler.getFkSpieler());
					if (rs != null) {
						while (rs.next()) {
							id = rs.getInt("fkSpieler");
						}
					}
					
					
					if(id == 0){
						String query = "insert into currentSpieler ('fkSpieler','name', 'vorname', 'strasse', 'plz', 'ort', 'karten') values ("
								+ currentSpieler.getFkSpieler()
								+ ", '"
								+ currentSpieler.getName()
								+ "', '"
								+ currentSpieler.getVorname()
								+ "', '"
								+ currentSpieler.getStrasse()
								+ "', "
								+ currentSpieler.getPlz()
								+ ", '" + 
								currentSpieler.getOrt()
								+ "', " + 
								currentSpieler.getKarten() + ")";
						
						System.out.println(query);
						s.execute(query);
						
					}
					else{
						System.out.println("Spieler ist bereits hinzugefügt.");
					}
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error inserting Data");
				}
	}
	
	
	/**
	 * Gibt Daten für TableView zuruck
	 * @return
	 */
	public List<CurrentSpielerModel> getAllCurrentSpieler() {
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select id, fkSpieler, name, vorname, strasse, plz, ort, karten from currentSpieler");
			if (rs != null) {
				while (rs.next()) {
					int id = rs.getInt("id");
					int fkSpieler = rs.getInt("fkSpieler");
					String name = rs.getString("name");
					String vorname = rs.getString("vorname");
					String strasse = rs.getString("strasse");
					int plz = rs.getInt("plz");
					String ort = rs.getString("ort");
					int karten = rs.getInt("karten");
					
					currentSpielerData.add(new CurrentSpielerModel(id, fkSpieler, name, vorname, strasse, plz, ort, karten));
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return currentSpielerData;
	}
	
	public void deleteCurrentSpieler(int fkSpieler) {
		// TODO Auto-generated method stub
				try {
					System.out.println("delete Current Spieler");
					con = DBConnector.getConnected();
					Statement s = con.createStatement();
					String query = "delete from currentSpieler where fkSpieler="+fkSpieler;
					System.out.println(query);
					s.execute(query);
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error inserting Data");
				}
	}

	//Serienverwaltung
	
	public List<SerieModel> getAllSerien() {
		// TODO Auto-generated method stub
		List<SerieModel> serien = new ArrayList<SerieModel>();
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select serienNr, fkRundenNr, preis.name as preis from serie, preis, runde where serie.fkRundenNr = runde.rundenNr and serie.fkPreis=preis.id order by fkRundenNr, serienNr;");
			if (rs != null) {
				while (rs.next()) {
					int serienNr = rs.getInt("serienNr");
					int rundenNr = rs.getInt("fkRundenNr");
					String preis = rs.getString("preis");
					
					
					serien.add(new SerieModel(serienNr, rundenNr, preis));
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return serien;
	}

	//Serienverwaltung
	
	public void addPreis(String text) {
		// TODO Auto-generated method stub
		try {
			System.out.println("insertPreis");
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			String query = "insert into preis ('name') values ('"+text+"')";
			
			System.out.println(query);
			s.execute(query);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error inserting Data");
		}
	}

	public List<String> getRunden() {
		// TODO Auto-generated method stub
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select rundenNr from runde");
			if (rs != null) {
				while (rs.next()) {
					String runde = rs.getString("rundenNr");
					rundenNr.add(runde);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return rundenNr;
	}

	public List<String> getPreise() {
		// TODO Auto-generated method stub
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select name from preis");
			if (rs != null) {
				while (rs.next()) {
					String name = rs.getString("name");
					preise.add(name);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return preise;
	}

	public List<String> getSerien() {
		// TODO Auto-generated method stub
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select serienNr from serie");
			if (rs != null) {
				while (rs.next()) {
					String serienNr = rs.getString("serienNr");
					serien.add(serienNr);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return serien;
	}

	public List<String> getRundentyp() {
		// TODO Auto-generated method stub
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select name from rundenTyp");
			if (rs != null) {
				while (rs.next()) {
					String rundentyp = rs.getString("name");
					rundentypen.add(rundentyp);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return rundentypen;
	}

	public void deleteSerie(String serienNr, String fkRundenNr) {
		// TODO Auto-generated method stub
		try {
			System.out.println("delete Serie " + serienNr +" from Runde " +fkRundenNr);
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			String query = "delete from serie where serienNr="+serienNr+" and fkRundenNr="+fkRundenNr;
			
			System.out.println(query);
			s.execute(query);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error deleting Data");
		}
	}

	public List<String> getNoUsedSerieNr(String selected) {
		// TODO Auto-generated method stub
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			usedSerieNr = new ArrayList<>();
			rs = s.executeQuery("select serienNr from serie where fkRundenNr="+selected);
			if (rs != null) {
				while (rs.next()) {
					int serienNr = rs.getInt("serienNr");
					usedSerieNr.add(serienNr);
				}
			}
			
			if (!usedSerieNr.contains(1) && !usedSerieNr.contains(2) && !usedSerieNr.contains(3)){
				System.out.println("into");
				NoUsedSerieNr.add(String.valueOf(1));
				NoUsedSerieNr.add(String.valueOf(2));
				NoUsedSerieNr.add(String.valueOf(3));
			}
			else{
				for (int i = 1; i <= 3; i++) {
					System.out.println("i:"+ i);
					if(!usedSerieNr.contains(i)){
						NoUsedSerieNr.add(String.valueOf(i));
					}
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return NoUsedSerieNr;
	}

	public List<String> getUsedSerieNr(String selected) {
		// TODO Auto-generated method stub
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select serienNr from serie where fkRundenNr="+selected);
			if (rs != null) {
				while (rs.next()) {
					String serienNr = rs.getString("serienNr");
					usedSerieNr2.add(serienNr);
				}
			}
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return usedSerieNr2;
	}

	public void addSerie(String rundeNr,String serienNr, String preis) {
		// TODO Auto-generated method stub
		try {
			System.out.println("insertSerie");
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select id from preis where name='"+preis+"'");
			if (rs != null) {
				while (rs.next()) {
					int fkPreis = rs.getInt("id");
					fkpreis.add(fkPreis);
				}
			}
			
			String query = "insert into serie ('serienNr', 'fkRundenNr', 'fkPreis') values ("+serienNr+","+rundeNr+","+fkpreis.get(0)+")";
			
			System.out.println(query);
			s.execute(query);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error inserting Data");
		}
	}

	public void addRunde(String rundenTypName) {
		// TODO Auto-generated method stub
		try {
			System.out.println("insertRunde");
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select id from rundenTyp where name='"+rundenTypName+"'");
			if (rs != null) {
				while (rs.next()) {
					int rundenTypNr = rs.getInt("id");
					rundenTyp.add(rundenTypNr);
				}
			}
			int maxRundenNr= getMaxRundenNr();
			maxRundenNr++;
			
			String query = "insert into runde ('rundenNr', 'fkRundentyp') values ("+maxRundenNr+","+rundenTyp.get(0)+")";
			
			System.out.println(query);
			s.execute(query);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error inserting Data");
		}
	}

	public int getMaxRundenNr() {
		// TODO Auto-generated method stub
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select max(rundenNr) as maxRundenNr from runde"); //Last/Max RundenNr
			if (rs != null) {
				while (rs.next()) {
					int rundenNr = rs.getInt("maxRundenNr");
					maxRundenNr.add(rundenNr);
				}
			}
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error selecting Data");
		}
		return maxRundenNr.get(0);
	}
	

	public void deleteRunde(String rundenNr) { //müssen noch Fremdschlüssel beziehungen implementiert werden fürs löschen
		// TODO Auto-generated method stub
		try {
			System.out.println("delete Runde " + rundenNr);
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			String query = "delete from runde where rundenNr="+rundenNr;
			
			System.out.println(query);
			s.execute(query);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error deleting Data");
		}
	}

	public void changeRundenTyp(String rundenNr, String neuerRundenTyp) {
		// TODO Auto-generated method stub
		try {
			System.out.println("change Rundentyp");
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select id from rundenTyp where name='"+neuerRundenTyp+"'");
			if (rs != null) {
				while (rs.next()) {
					int rundenTyp = rs.getInt("id");
					neuerRundenTypen.add(rundenTyp);
				}
			}
			

			String query = "update runde set fkRundentyp="+neuerRundenTypen.get(0)+ " where rundenNr=" + rundenNr;
			
			System.out.println(query);
			s.execute(query);
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error updating Data");
		}
		
	}
	
	public String getRundenTypById(int id){
		String rundenTypName = "";
		try{
		con = DBConnector.getConnected();
		Statement s = con.createStatement();
		ResultSet rs;
		
		rs = s.executeQuery("select name from runde, rundenTyp where runde.fkRundentyp = rundenTyp.id and runde.rundenNr="+id);
		if (rs != null) {
			while (rs.next()) {
				rundenTypName = rs.getString("name");
			}
		}
		con.close();
		
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error selecting Data");
		}
		
		return rundenTypName;
	
	}

	public void deletePreis(String preis) {
		// TODO Auto-generated method stub
		try {
			System.out.println("delete Preis " + preis);
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			String query = "delete from preis where name='"+preis+"'";
			
			System.out.println(query);
			s.execute(query);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error deleting Data");
		}
	}

	public void editPreis(String oldPreis, String newPreis) {
		// TODO Auto-generated method stub
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			String query = "update preis set name='" + newPreis	+ "' where name='" + oldPreis + "'";
			System.out.println(query);
			s.execute(query);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error updating Data");
		}
	}

	
	public List<RundenModel> getAllRunden() {
		// TODO Auto-generated method stub
		List<RundenModel> runden = new ArrayList<RundenModel>();
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select runde.rundenNr as rundenNr, rundenTyp.name as name from runde, rundenTyp where runde.fkRundentyp = rundenTyp.id");
			if (rs != null) {
				while (rs.next()) {
					int rundenNr = rs.getInt("rundenNr");
					String typName = rs.getString("name");
					runden.add(new RundenModel(rundenNr, typName));
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return runden;
	}

	
	//SpielenView
	

	public int getFirstSerie(int rundenNr) {
		// TODO Auto-generated method stub
		
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select serienNr from serie, runde where serie.fkRundenNr = runde.rundenNr and serie.fkRundenNr ="+rundenNr+" limit 1");
			if (rs != null) {
				while (rs.next()) {
					serienNr = rs.getInt("serienNr");
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return serienNr;
	}

	public String getPreis(int rundenNr, int serienNr) {
		// TODO Auto-generated method stub
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs = s.executeQuery("select preis.name as preisname from preis,serie,runde where serie.fkRundenNr = runde.rundenNr and serie.fkPreis = preis.id and runde.rundenNr ="+ rundenNr+ " and serie.serienNr ="+ serienNr);
			if (rs != null) {
				while (rs.next()) {
					preis = rs.getString("preisname");
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return preis;
	}

	public String searchGewinner(int zahl, int rundenNr, int serienNr) {
		// TODO Auto-generated method stub
		String txtGewinner = "Kein Gewinner gefunden";
		int spielerId = 0;
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
		
			rs = s.executeQuery("select cur.fkSpieler as fkSpieler,s.name as name, s.vorname as vorname, s.strasse as strasse, s.plz as plz, s.ort as ort from currentSpieler as cur,spieler as s where cur.fkSpieler = s.id and cur.lottozahl ="+zahl);
			if (rs != null) {
				while (rs.next()) {
					spielerId = rs.getInt("fkSpieler");
					String name = rs.getString("name");
					String vorname = rs.getString("vorname");
					String strasse = rs.getString("strasse");
					int plz = rs.getInt("plz");
					String ort = rs.getString("ort");
					txtGewinner = name + ", " + vorname +", " + strasse + ", " + plz + " " + ort;
				}
			}
			if(spielerId != 0){
				updateGewinner(spielerId, rundenNr, serienNr);
			}
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return txtGewinner;
	}
	
	public void updateGewinner(int spielerId, int rundenNr, int serienNr) {
		
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			String query = "update serie set sieger="+ spielerId +" where fkRundenNr="+rundenNr+" and serienNr ="+serienNr;
			s.execute(query);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error updating Data");
		}
		
	}
	
}
