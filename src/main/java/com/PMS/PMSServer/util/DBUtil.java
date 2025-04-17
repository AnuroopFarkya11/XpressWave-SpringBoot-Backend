package com.PMS.PMSServer.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static final String dbName = "PMSDB";

	private static final String demoDbName = "PMSDEMODB";

	private static Connection connection = null;

	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/// Connects the database
	public static DatabaseMetaData connectDB() throws SQLException {
		System.out.println("Connecting Database.....  ");
		connection = DriverManager.getConnection("jdbc:derby:" + dbName + ";create=true");
		DatabaseMetaData metaData = connection.getMetaData();
		System.out.println("Connected DEV Database Successfully!");
		// todo print some meta data for database like version, name , drivers
		return connection.getMetaData();
	}

	public static DatabaseMetaData connectDEMODB() throws SQLException {
		System.out.println("Connecting Database.....  ");
		connection = DriverManager.getConnection("jdbc:derby:" + demoDbName + ";create=true");
		DatabaseMetaData metaData = connection.getMetaData();
		System.out.println("Connected DEMO Database Successfully!");
		// todo print some meta data for database like version, name , drivers
		return connection.getMetaData();
	}

	public static Connection getConnection() {
		/// todo make a custom exception for connection is null
		if (connection != null) {
			return connection;
		}
		return null;
	}

	public static void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

}
