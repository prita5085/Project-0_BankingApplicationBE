package com.bank.util;

import java.sql.*;

public class ConnectionDemo {

	private static Connection con = null;

	public static Connection getMyConnection() {
		// To establish connection we need 3 credentials: url (endpoint), un, pwd

		if (con == null) {

			String endpoint = "paritapostgres.caxbj1exud2l.us-east-2.rds.amazonaws.com";
			// URL Format (postgres JDBC)
			String url = "jdbc:postgresql://" + endpoint + "/postgres";
			String username = "postgres";
			String password = "rootroot";

			try {
				con = DriverManager.getConnection(url,username,password);
				
				if (con != null) {
					System.out.println("Connection Succeeded");
				} else {
					System.out.println("Connection Failed");
				}

			} catch (SQLException se) {
				System.out.println(se);
			}
		}
		return con;
	}
}
