package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connection.ConnectionFactory;
import model.Cliente;
import model.Entrada;
import model.Produto;
import model.Saida;
import util.Conversao;

public class SaidaDAO implements DAO<Saida>{

	@Override
	public List<Saida> select() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Saida> selectByProduto(int id) {
		List<Saida> lista = new ArrayList<>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Saida " +
					 "WHERE Produto = ? ORDER BY Data";
		con = ConnectionFactory.getConnection();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1,id);
			rs = pst.executeQuery();
			while (rs.next()) {
				int codigo = rs.getInt("Codigo");
				int codProduto = rs.getInt("Produto");
				Produto produto = new ProdutoDAO().select(codProduto);
				int codCliente = rs.getInt("Cliente");
				Cliente cliente = new ClienteDAO().select(codCliente);
				String dtString = rs.getString("Data");
				Date data = Conversao.str2dmy(dtString);
				String doc = rs.getString("Doc");
				int qtde = rs.getInt("Qtde");
				double valor = rs.getDouble("Valor");
				lista.add(new Saida(codigo,
									produto,
									cliente,
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
	public int insert(Saida r) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(Saida r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}