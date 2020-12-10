package controller;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import connection.ConnectionFactory;
import dao.FornecedorDAO;
import model.Entrada;
import model.Fornecedor;
import model.Produto;
import view.TelaKardex;

public class Principal {
	
	public static void main(String[] args) {
		//geraProdutos();
		//listaProdutos();
		//gravaProdutos();
		//leProdutos();
		//listaProdutos();
		//testeFornecedor();	
		//testeConexao();
		//testaEntrada();
		//new TelaPrincipal().setVisible(true);
		//new TelaFornecedor().setVisible(true);
		//new TelaProduto().setVisible(true);
		new TelaKardex().setVisible(true);
	}
	
	private static void testaEntrada() {
		Produto p = new Produto(1,"HD 2TB Samsung ",10,500);
		String nome = "Samsung Eletronics";
		String cnpj = "35.177.496/0001-58";
		String telefone = "(11) 7777-6748";
		Fornecedor f = new Fornecedor(nome,cnpj,telefone);
		Date data = new Date(System.currentTimeMillis());
		Entrada e = new Entrada(1,p,f,data,"123",10,100);
		System.out.println(e);
	}
	
	private static void testeConexao() {
		Connection con = ConnectionFactory.getConnection();
		if (con == null) System.out.println("Erro na conexão!");
		else System.out.println("Conexão bem sucedida!");
		FornecedorDAO dao = new FornecedorDAO();
		List<Fornecedor> lista = dao.select();
		for (Fornecedor f: lista) {
			System.out.println(f);
		}
		ConnectionFactory.closeConnection(con);
	}
	
	private static void testeFornecedor() {
		int codigo = 1;
		String nome = "Fornecedor Teste";
		String cnpj = "06.274.707/0001-72";
		String telefone = "(11) 4569-6748";
		Fornecedor f = new Fornecedor(codigo,nome,cnpj,telefone);
		System.out.println(f);
	}
	
	private static void leProdutos() {
		Produto.carregarCSV();
	}
	private static void geraProdutos() {
		new Produto(1,"Teclado Microsoft Natural",15,180);
		new Produto(2,"Monitor HP IPS Full HD 23.5",5,1000);
		new Produto(3,"SSD Sandisk 480GB M2",10,500);
		new Produto(4,"Mouse Logitech Gamer",15,100);
		new Produto(5,"Memória DDR4 2666Mhz 8GB",20,300);
		new Produto(6,"Placa NVidia RTX3080 Nova",5,3000);
	}
	
	private static void listaProdutos() {
		Produto.getLista().forEach(e -> System.out.println(e));
	}
	
	private static void gravaProdutos() {
		Produto.gravarCSV();
	}
	
}