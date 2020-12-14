package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.EntradaDAO;
import dao.SaidaDAO;
import util.Conversao;

public abstract class Kardex {

	private int codigo;
	private Date data;
	private String doc;
	private int qtde;
	private double valor;
	private Produto produto;

	public Kardex(int c, Produto p, Date dt, String d, int q, double v) {
		setCodigo(c);
		setProduto(p);
		setData(dt);
		setDoc(d);
		setQtde(q);
		setValor(v);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public int getQtde() {
		return qtde;
	}

	public void setQtde(int qtde) {
		this.qtde = qtde;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public static List<Kardex> getListaKardex(int produto) {
		List<Kardex> lista = new ArrayList<>();
		for (Entrada e : new EntradaDAO().selectByProduto(produto)) {
			lista.add(e);
		}

		for (Saida s : new SaidaDAO().selectByProduto(produto)) {
			lista.add(s);
		}
		Collections.sort(lista, (a, b) -> a.getData().compareTo(b.getData()));
		//Collections.sort(lista, (a, b) -> a.getDoc().compareTo(b.getDoc()));
		return lista;
	}

	public static DefaultTableModel getTableModel(int produto) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Tipo");
		modelo.addColumn("Código");
		modelo.addColumn("Data");
		modelo.addColumn("Nome");
		modelo.addColumn("Docto");
		modelo.addColumn("Qtde");
		modelo.addColumn("Valor");
		for (Kardex k : getListaKardex(produto)) {
			if (k instanceof Entrada) {
				Entrada e = (Entrada) k; // downcasting
				String[] str = { "E", String.valueOf(e.getCodigo()), Conversao.date2dmy.format(e.getData()),
						e.getFornecedor().getNome(), e.getDoc(), String.valueOf(e.getQtde()),
						String.valueOf(e.getValor()) };
				modelo.addRow(str);
			}
			if (k instanceof Saida) {
				Saida s = (Saida) k; // downcasting
				String[] str = { "S", String.valueOf(s.getCodigo()), Conversao.date2dmy.format(s.getData()),
						s.getCliente().getNome(), s.getDoc(), String.valueOf(s.getQtde()),
						String.valueOf(s.getValor()) };

				modelo.addRow(str);
			}

		}
		return modelo;
	}

	@Override
	public String toString() {
		return (getProduto().getNome() + " - " +
				Conversao.date2dmy.format(getData()) + " - " +
				getDoc() + " - " +
				getQtde() + " - " +
				getValor()
				);
	}

}