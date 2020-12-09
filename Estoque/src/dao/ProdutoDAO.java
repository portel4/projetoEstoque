package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import util.CSV;

// DAO - Data Access Object
public class ProdutoDAO {
	
	private final String arquivo = "C:/Users/Deliziane/Documents/workspace/GitHub/projeto_estoque/Estoque/db/Produto.CSV";
	
	public void exportaCSV(List<Produto> lista) {
		try {
			BufferedWriter dados = new BufferedWriter(new FileWriter(arquivo));
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
			BufferedReader dados = new BufferedReader(new FileReader(arquivo));
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
