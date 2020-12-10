package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import model.Produto;
import util.CSV;

// DAO - Data Access Object
public class ProdutoDAO implements DAO<Produto> {
	
	private final String ARQUIVO = "C:/Users/Deliziane/Documents/Estudos/Java_curso/GitHub/projetoEstoque/Estoque/bd/Produto.CSV";
	
	@Override
	public List<Produto> select() {
		List<Produto> lista = new ArrayList<>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Produto";
		con = ConnectionFactory.getConnection();
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int codigo = rs.getInt("Codigo");
				String nome = rs.getString("Nome");
				int qtde = rs.getInt("Qtde");
				double valor = rs.getDouble("Valor");
				lista.add(new Produto(codigo,nome,qtde,valor));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con,pst,rs);
		}		
		return lista;
	}

	public Produto select(int id) {
		Produto reg = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Produto " +
					 "WHERE Codigo = ?";
		con = ConnectionFactory.getConnection();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				int codigo = rs.getInt("Codigo");
				String nome = rs.getString("Nome");
				int qtde = rs.getInt("Qtde");
				double valor = rs.getDouble("Valor");
				reg = new Produto(codigo,nome,qtde,valor);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con,pst,rs);
		}		
		return reg;
	}
	
	@Override
	public int insert(Produto r) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "INSERT INTO Produto " +
					 "(Nome,Qtde,Valor) " +
					 "VALUES (?,?,?)";
		int codigo = 0;
		con = ConnectionFactory.getConnection();		
		try {
			pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1,r.getNome());
			pst.setInt(2,r.getQtde());
			pst.setDouble(3,r.getValor());
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
	public boolean update(Produto r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		boolean ok = false;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "DELETE FROM Produto WHERE Codigo = ?";
		con = ConnectionFactory.getConnection();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1,id);
			pst.execute();
			ok = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con,pst,rs);
		}		
		return ok;
	}		
	
	public void exportaCSV(List<Produto> lista) {
		try {
			BufferedWriter dados = new BufferedWriter(new FileWriter(ARQUIVO));
			for (Produto p: lista) {
				CSV.write(dados, p.getCodigo());
				CSV.write(dados, p.getNome());
				CSV.write(dados, p.getQtde());
				CSV.writeln(dados, p.getValor());
			}
				dados.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}		
	}
	
	public List<Produto> importaCSV() {
		List<Produto> lista = new ArrayList<Produto>();
		try {
			BufferedReader dados = new BufferedReader(new FileReader(ARQUIVO));
			String linha;
			while ((linha = dados.readLine()) != null) {
				String[] s = linha.split(",");
				int codigo = Integer.parseInt(s[0]);
				String nome = s[1];
				nome = nome.replaceAll("\"", "");
				int qtde = Integer.parseInt(s[2]);
				double valor = Double.parseDouble(s[3]);
				lista.add(new Produto(codigo,nome,qtde,valor));
			}
			dados.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

}
