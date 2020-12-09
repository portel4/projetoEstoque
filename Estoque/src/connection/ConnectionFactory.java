package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String URL = "jdbc:sqlite:C:/Users/Deliziane/Documents/Estudos/Java_curso/GitHub/projetoEstoque/Estoque/bd/Estoque.DB";

	
	public static Connection getConnection() {
		Connection con = null;
		try {
			//con = DriverManager.getConnection(URL, USER, PASS);
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

	public static void closeConnection(Connection con, PreparedStatement pst) {
		try {
			if (pst != null) {
				pst.close();
			}
			closeConnection(con);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement pst, ResultSet rs){
		try {
			if (rs != null) {
				rs.close();
			}
			closeConnection(con,pst);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}