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
	
	
	
	
	
	
	public List<SpielerModel> getDetailOfSpieler(SpielerModel spieler) {
		
		try {
			spielerDataOf.clear();
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs= s.executeQuery("select * from spieler where id='" + spieler.getId() + "'");
			
			if (rs != null) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String vorname = rs.getString("vorname");
					String strasse = rs.getString("strasse");
					int plz = rs.getInt("plz");
					String ort = rs.getString("ort");
					spielerDataOf.add(new SpielerModel(id,name, vorname,strasse, plz, ort));
				}
			
			}
			/*for (SpielerModel spielerModel : spielerDataOf) {
				System.out.println(spielerModel.getId());
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return spielerDataOf;
	}
	public List<SpielerModel> getAllSpielerOverView() {
		try {
			con = DBConnector.getConnected();
			Statement s = con.createStatement();
			ResultSet rs;
			rs= s.executeQuery("select id, name, vorname, ort from spieler");
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
