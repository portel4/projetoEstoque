package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connection.ConnectionFactory;
import model.Cliente;
import model.Fornecedor;

public class ClienteDAO implements DAO<Cliente>{

	@Override
	public List<Cliente> select() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Cliente select(int id) {
		Cliente reg = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT Codigo,Nome,CPF,Celular,Email " +
					 "FROM Cliente WHERE Codigo = ?";
		con = ConnectionFactory.getConnection();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1,id);
			rs = pst.executeQuery();
			if (rs.next()) {
				int codigo = rs.getInt("Codigo");
				String nome = rs.getString("Nome");
				String cpf = rs.getString("CPF");
				String celular = rs.getString("Celular");
				String email = rs.getString("Email");
				reg = new Cliente(codigo, nome, cpf, celular, email);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con,pst,rs);
		}		
		return reg;
	}	

	@Override
	public int insert(Cliente r) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(Cliente r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}