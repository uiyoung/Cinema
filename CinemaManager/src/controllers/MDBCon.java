package controllers;

import java.sql.*;

public class MDBCon {
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cinemadb";
	// String url = "jdbc:mysql://localhost:3308/management"; //
	String id = "root";
	String pw = "1234";
	Connection con = null;

	public MDBCon() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public Connection getConnection() {
		// TODO Auto-generated method stub
		return con;
	}

}
