package controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://127.0.0.1:3306/cinemaDB";
	private final String ID = "root";
	private final String PW = "1234";
	Connection conn = null;

	public DBConnection() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, ID, PW);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}
}
