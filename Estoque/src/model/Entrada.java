package model;

import java.util.Date;

public class Entrada extends Kardex {

	private Fornecedor fornecedor;
	
	public Entrada(int c, Produto p, Fornecedor f, Date dt, String d, int q, double v) {
		super(c,p,dt,d,q,v);
		setFornecedor(f);
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}