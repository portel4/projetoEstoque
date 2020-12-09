package model;

import java.util.Date;

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
	
	@Override
	public String toString() {
		return "Kardex [data=" + data + ", doc=" + doc + ", qtde=" + qtde + ", valor=" + valor + "]";
	}
	
}