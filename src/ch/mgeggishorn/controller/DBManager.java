package ch.mgeggishorn.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;
import javax.swing.text.html.HTMLDocument.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.mgeggishorn.model.SpielerModel;

public class DBManager {
	private static Connection con;
	private static Statement stat;

	private ObservableList<SpielerModel> spielerData;

	public ObservableList<SpielerModel> getAllSpieler() {
		try {
			con = DBConnector.getConnected();
			stat = con.createStatement();
			spielerData = FXCollections.observableArrayList();
			ResultSet rs = con.createStatement().executeQuery(
					"select * from spieler");
			while (rs.next()) {
				spielerData.add(new SpielerModel(rs.getString("name"), rs
						.getString("vorname"), rs.getString("strasse"), rs
						.getInt("plz"), rs.getString("ort")));
			}
			
			
			for (int i = 0; i < spielerData.size(); i++) {
				System.out.println(spielerData.get(i).getName()+spielerData.get(i).getVorname()+spielerData.get(i).getPlz()+spielerData.get(i).getStrasse()+spielerData.get(i).getOrt());
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}

		return null;

	};
}
