package model;

import java.util.Date;

import javax.swing.table.DefaultTableModel;

import dao.SaidaDAO;
import util.Conversao;

public class Saida extends Kardex {

	private Cliente cliente;
	
	public Saida(int c, Produto p, Cliente cl, Date dt, String d, int q, double v) {
		super(c,p,dt,d,q,v);
		setCliente(cl);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}	

	public static DefaultTableModel getTableModel(int produto) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Cliente");
		modelo.addColumn("Data");
		modelo.addColumn("Docto");
		modelo.addColumn("Qtde");
		modelo.addColumn("Valor");
		for (Saida e: new SaidaDAO().selectByProduto(produto)) {
			String[] s = { String.valueOf(e.getCodigo()), 
										  e.getCliente().getNome(), 
						   Conversao.date2dmy.format(e.getData()), 
										  e.getDoc(), 
						   String.valueOf(e.getQtde()), 
						   String.valueOf(e.getValor()) };
			modelo.addRow(s);
		}
		return modelo;
	}	
	
}