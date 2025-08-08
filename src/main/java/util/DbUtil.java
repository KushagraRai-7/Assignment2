package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	
	private static final String URL ="jdbc:mysql://localhost:3306/department_db";
	private static final String USER = "root";
	private static final String PASSWORD = "2025SQLl";
	
	public static Connection getconnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}