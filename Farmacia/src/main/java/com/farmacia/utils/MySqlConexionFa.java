package com.farmacia.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConexionFa {
	public static Connection getConexion() {
		Connection cn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url,user,pass;
			user="root";
			pass="mysql";
			url="jdbc:mysql://localhost:3306/BD_Farma_ABCD?serverTimezone=UTC";
			cn = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}
}
