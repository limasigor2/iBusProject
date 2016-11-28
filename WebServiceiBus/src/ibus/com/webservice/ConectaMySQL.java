package ibus.com.webservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaMySQL {
	
	private static final String URL = "jdbc:mysql://localhost/ibus";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public static Connection obterConexao() throws SQLException{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(URL, USER, PASSWORD);
		
	}
	
}
