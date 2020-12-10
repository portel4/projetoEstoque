package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connection.ConnectionFactory;
import model.Entrada;
import model.Fornecedor;
import model.Produto;

public class EntradaDAO implements DAO{

	@Override
	public List select() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Entrada> selectByProduto(int id) {
		List<Entrada> lista = new ArrayList<>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Entrada " +
					 "WHERE Produto = ?";
		con = ConnectionFactory.getConnection();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1,id);
			rs = pst.executeQuery();
			while (rs.next()) {
				int codigo = rs.getInt("Codigo");
				int codProduto = rs.getInt("Produto");
				Produto produto = new ProdutoDAO().select(codProduto);
				int codFornecedor = rs.getInt("Fornecedor");
				Fornecedor fornecedor = new FornecedorDAO().select(codFornecedor);
				Date data = new Date(System.currentTimeMillis());//rs.getDate("Data");
				String doc = rs.getString("Doc");
				int qtde = rs.getInt("Qtde");
				double valor = rs.getDouble("Valor");
				lista.add(new Entrada(codigo,
									  produto,
									  fornecedor,
									  data, 
									  doc, 
									  qtde,
									  valor));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con,pst,rs);
		}		
		return lista;
	}
	
	@Override
	public int insert(Object r) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(Object r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
