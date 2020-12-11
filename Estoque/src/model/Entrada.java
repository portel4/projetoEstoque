package model;

import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.EntradaDAO;
import dao.FornecedorDAO;
import dao.ProdutoDAO;
import util.Conversao;

public class Entrada extends Kardex {

	private Fornecedor fornecedor;

	public Entrada(Produto p, Fornecedor f, Date dt, String d, int q, double v) {
		super(0,p,dt,d,q,v);
		setFornecedor(f);
	}
	public Entrada(int c, Produto p, Fornecedor f, Date dt, String d, int q, double v) {
		super(c,p,dt,d,q,v);
		setFornecedor(f);
	}
	
	public int gravar() {
		int codigo = 0;
		if (this.getCodigo() == 0) { // incluindo novo registro
			codigo = new EntradaDAO().insert(this);
		} else { // alterando registro existente
			new EntradaDAO().update(this);
			codigo = this.getCodigo();
		}
		return codigo;
	}	
	
	public static List<Entrada> getLista(int produto) {
		return (new EntradaDAO().selectByProduto(produto));
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public static DefaultTableModel getTableModel(int produto) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Fornecedor");
		modelo.addColumn("Data");
		modelo.addColumn("Docto");
		modelo.addColumn("Qtde");
		modelo.addColumn("Valor");
		for (Entrada e: new EntradaDAO().selectByProduto(produto)) {
			String[] s = { String.valueOf(e.getCodigo()), 
										  e.getFornecedor().getNome(), 
						   Conversao.date2dmy.format(e.getData()), 
										  e.getDoc(), 
						   String.valueOf(e.getQtde()), 
						   String.valueOf(e.getValor()) };
			modelo.addRow(s);
		}
		return modelo;
	}
	
	@Override
	public String toString() {
		return ("----------------------------------------------------\n" +
				getCodigo() + ": " + 
				getProduto().getNome() + " - " +
				getFornecedor().getNome() + "\n" +
				getData() + " - " + getDoc() + " - " + 
				getQtde() + " - " + getValor());
	}

}