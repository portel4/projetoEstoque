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

public class FornecedorDAO {
	
	private final String arquivo = "C:/Users/Deliziane/Documents/workspace/GitHub/projeto_estoque/Estoque/bin/db/Fornecedor.csv";
	
	
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
		}
		return lista;
	}
	
	public void exportaCSV(List<Fornecedor> lista) {
		try {
			BufferedWriter dados = new BufferedWriter(new FileWriter(arquivo));
			for (Fornecedor p: lista) {
				CSV.write(dados, p.getCodigo());
				CSV.write(dados, p.getNome());
				CSV.write(dados, p.getCnpj());
				CSV.writeln(dados, p.getTelefone());
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
				cnpj = cnpj.replaceAll("\"", "");
				String telefone = s[3];
				telefone = telefone.replaceAll("\"", "");
				
				lista.add(new Fornecedor(codigo,nome,cnpj,telefone));
			}
			dados.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}	
}
