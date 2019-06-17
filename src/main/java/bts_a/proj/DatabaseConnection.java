package bts_a.proj;

import java.sql.*;

public class DatabaseConnection {
	private final String DB_URL = "jdbc:mysql://localhost/mydatabase";
	private Connection sqlHandle;
	
	public DatabaseConnection() {
		try {
			sqlHandle = DriverManager.getConnection(DB_URL, "root", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertData(String stock, int price) {
		String sql = String.format("INSERT INTO data (stock, price) VALUES ('%s', %d)", stock, price);
		System.out.println(sql);
		try {
			sqlHandle.createStatement().execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String args[]) {
//		new DatabaseConnection().insertData("what the heck", 3);
//	}
}
