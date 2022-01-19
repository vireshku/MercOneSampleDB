package com.heroku.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	
	
	public static void main(String[] args) throws SQLException {
		
		Connection connection = getConnection();

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
		stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
		stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
		ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
		while (rs.next()) {
			System.out.println("Read from DB: " + rs.getTimestamp("tick"));
		}
		
	}

	
	private static Connection getConnection() throws SQLException {
		
		String dbURL = System.getenv("JDBC_DATABASE_URL");
		return DriverManager.getConnection(dbURL);
	}
	
	
}
