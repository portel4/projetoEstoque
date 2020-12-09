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
import model.Fornecedor;
import util.CSV;

public class FornecedorDAO implements DAO<Fornecedor> {
	
	private final String arquivo = "C:/Users/Deliziane/Documents/Estudos/Java_curso/GitHub/projetoEstoque/Estoque/bd/Fornecedor.csv";
	
	
	@Override
	public int insert(Fornecedor r) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "INSERT INTO Fornecedor " +
					 "(Nome,CNPJ,Telefone) " +
					 "VALUES (?,?,?)";
		int codigo = 0;
		con = ConnectionFactory.getConnection();		
		try {
			pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1,r.getNome());
			pst.setString(2,r.getCnpj());
			pst.setString(3,r.getTelefone());
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
	public List<Fornecedor> select() {
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT Codigo,Nome,CNPJ,Telefone FROM Fornecedor";
		con = ConnectionFactory.getConnection();
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int codigo = rs.getInt("Codigo");
				String nome = rs.getString("Nome");
				String cnpj = rs.getString("CNPJ");
				String telefone = rs.getString("Telefone");
				lista.add(new Fornecedor(codigo, nome, cnpj, telefone));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con,pst,rs);
		}		
		return lista;
	}

	public void exportaCSV(List<Fornecedor> lista) {
		try {
			BufferedWriter dados = new BufferedWriter(new FileWriter(arquivo));
			for (Fornecedor f : lista) {
				CSV.write(dados, f.getCodigo());
				CSV.write(dados, f.getNome());
				CSV.write(dados, f.getCnpj());
				CSV.writeln(dados, f.getTelefone());
			}
			dados.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Fornecedor> importaCSV() {
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		try {
			BufferedReader dados = new BufferedReader(new FileReader(arquivo));
			String linha;
			while ((linha = dados.readLine()) != null) {
				String[] s = linha.split(",");
				int codigo = Integer.parseInt(s[0]);
				String nome = s[1];
				nome = nome.replaceAll("\"", "");
				String cnpj = s[2];
				nome = cnpj.replaceAll("\"", "");
				String telefone = s[3];
				nome = telefone.replaceAll("\"", "");
				lista.add(new Fornecedor(codigo, nome, cnpj, telefone));
			}
			dados.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	@Override
	public boolean update(Fornecedor r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		boolean ok = false;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "DELETE FROM Fornecedor WHERE Codigo = ?";
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

}