package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connection.ConnectionFactory;
import model.Entrada;
import model.Fornecedor;
import model.Produto;
import util.Conversao;

public class EntradaDAO implements DAO<Entrada>{
	
	@Override
	public List<Entrada> select() {
		// TODO Auto-generated method stub
		return null;
	}
	public Entrada select(int id) {
		Entrada reg = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Entrada " +
					 "WHERE Codigo = ?";
		con = ConnectionFactory.getConnection();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1,id);
			rs = pst.executeQuery();
			if (rs.next()) {
				int codigo = rs.getInt("Codigo");
				int codProduto = rs.getInt("Produto");
				Produto produto = new ProdutoDAO().select(codProduto);
				int codFornecedor = rs.getInt("Fornecedor");
				Fornecedor fornecedor = new FornecedorDAO().select(codFornecedor);
				String dtString = rs.getString("Data");
				Date data = Conversao.str2dmy(dtString);
				String doc = rs.getString("Doc");
				int qtde = rs.getInt("Qtde");
				double valor = rs.getDouble("Valor");
				reg = new Entrada(codigo,
						  		  produto,
								  fornecedor,
								  data, 
								  doc, 
								  qtde,
								  valor);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con,pst,rs);
		}		
		return reg;		
	}

	public List<Entrada> selectByProduto(int id) {
		List<Entrada> lista = new ArrayList<>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Entrada " +
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
				int codFornecedor = rs.getInt("Fornecedor");
				Fornecedor fornecedor = new FornecedorDAO().select(codFornecedor);
				String dtString = rs.getString("Data");
				Date data = Conversao.str2dmy(dtString);
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
	public int insert(Entrada r) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "INSERT INTO Entrada " +
					 "(Produto,Fornecedor,Data,Doc,Qtde,Valor) " +
					 "VALUES (?,?,?,?,?,?)";
		int codigo = 0;
		con = ConnectionFactory.getConnection();		
		try {
			pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setInt(1,r.getProduto().getCodigo());
			pst.setInt(2,r.getFornecedor().getCodigo());
			pst.setString(3,Conversao.date2dmy.format(r.getData()));
			pst.setString(4, r.getDoc());
			pst.setInt(5, r.getQtde());
			pst.setDouble(6, r.getValor());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				codigo = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con,pst,rs);
		}
		return codigo;
	}

	@Override
	public boolean update(Entrada r) {
		boolean ok = false;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "UPDATE Entrada SET " +
					 "Produto = ?, " +
					 "Fornecedor = ?, " +
					 "Data = ?, " +
					 "Doc = ?, " +
					 "Qtde = ?, " +
					 "Valor = ? " +
					 "WHERE Codigo = ?";
		con = ConnectionFactory.getConnection();		
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1,r.getProduto().getCodigo());
			pst.setInt(2,r.getFornecedor().getCodigo());
			pst.setString(3,Conversao.date2dmy.format(r.getData()));
			pst.setString(4, r.getDoc());
			pst.setInt(5, r.getQtde());
			pst.setDouble(6, r.getValor());
			pst.setInt(7,  r.getCodigo());
			pst.executeUpdate();
			ok = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con,pst,rs);
		}
		return ok;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}