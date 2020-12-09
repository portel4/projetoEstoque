package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String URL = "jdbc:sqlite:C:/Users/Deliziane/Documents/workspace/GitHub/projeto_estoque/Estoque/db/Estoque.DB";

	
	public static Connection getConnection() {
		Connection con= null;
		
		try {
			con = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return con; 
		
	}
	
	public static void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
