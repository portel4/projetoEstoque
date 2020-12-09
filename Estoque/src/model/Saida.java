package model;

import java.util.Date;

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

	
}