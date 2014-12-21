package ch.mgeggishorn.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.mgeggishorn.model.SpielerModel;

public class DBManager {
	private static Connection con;
	List<SpielerModel> spielerData = new ArrayList<>();
	List<SpielerModel> spielerDataOf = new ArrayList<>();
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
			rs = s.executeQuery("select id, name, vorname, ort from spieler");
			if (rs != null) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String vorname = rs.getString("vorname");
					String ort = rs.getString("ort");
					spielerData.add(new SpielerModel(id, name, vorname, ort));
				}
			}
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
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			String query = "insert into spieler ('name', 'vorname', 'strasse', 'plz', 'ort') values ('"
					+ spieler.getName()
					+ "', '"
					+ spieler.getVorname()
					+ "', '"
					+ spieler.getStrasse()
					+ "', "
					+ spieler.getPlz()
					+ ", '" + spieler.getOrt() + "')";
			System.out.println(query);
			// s.executeQuery(query);

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
			System.out.println("select id, name, vorname, ort from spieler where name like'%" + searchQuery + "%' or vorname like'%" + searchQuery + "%' or strasse like'%" + searchQuery + "%' or plzlike'%" + searchQuery + "%' or ort like'%" + searchQuery + "%'");
			rs = s.executeQuery("select id, name, vorname, ort from spieler where name like '%" + searchQuery + "%' or vorname like '%" + searchQuery + "%' or strasse like '%" + searchQuery + "%' or plz like '%" + searchQuery + "%' or ort like '%" + searchQuery + "%'");
			spielerData.clear();
			if (rs != null) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String vorname = rs.getString("vorname");
					String ort = rs.getString("ort");
					spielerData.add(new SpielerModel(id, name, vorname, ort));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return spielerData;
	}
	
}
