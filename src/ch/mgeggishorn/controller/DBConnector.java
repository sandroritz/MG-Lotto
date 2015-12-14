package ch.mgeggishorn.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnector {
	private static Connection con;

	public static Connection getConnected() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
		  {
		      Class.forName("org.sqlite.JDBC").newInstance();
		      con = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.home") + "/" + "lottoskiclub/db_skiclub" );
		      
		      return con;
		  }
}


