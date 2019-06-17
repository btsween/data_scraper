package bts_a.proj;

import java.sql.*;

public class Connect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String db_url = "jdbc:mysql://localhost/mydatabase";
		try {
			// Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(db_url, "root", "password");
			conn.createStatement().execute("INSERT INTO customers (stock, description) VALUES (8, 'hey')");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
