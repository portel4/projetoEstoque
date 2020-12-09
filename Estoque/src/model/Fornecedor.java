package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.FornecedorDAO;
import util.Valida;

public class Fornecedor {

	private int codigo;
	private String nome;
	private String cnpj;
	private String telefone;
	private static List<Fornecedor> lista = new ArrayList<Fornecedor>();

	public static List<Fornecedor> getLista() {
		return new FornecedorDAO().select();
	}

	public static void gravarCSV() {
		new FornecedorDAO().exportaCSV(lista);
	}

	public static void carregarCSV() {
		lista = new FornecedorDAO().importaCSV();
	}
	
	public static boolean excluir(int id) {
		boolean ok = new FornecedorDAO().delete(id);
		return ok;
	}
	
	public int gravar() {
		return(new FornecedorDAO().insert(this));
	}

// Não pode existir na versão final do sistema
//	public Fornecedor() { 
//		this(0, "", "", "");
//	}

	public Fornecedor(String nome, String cnpj, String telefone) {
		this(0, nome, cnpj, telefone);
	}

	public Fornecedor(int codigo, String nome, String cnpj, String telefone) {
		setCodigo(codigo);
		setNome(nome);
		setCnpj(cnpj);
		setTelefone(telefone);
		//lista.add(this);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		if (codigo >= 0) {
			this.codigo = codigo;
		} else {
			throw new IllegalArgumentException("Código inválido");
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.length() > 2) {
			this.nome = nome;
		} else {
			throw new IllegalArgumentException("Nome inválido. Deve ter mais que 2 caracteres!");
		}
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		if (Valida.CNPJ(cnpj)) {
			this.cnpj = cnpj;
		} else {
			throw new IllegalArgumentException("CNPJ Inválido!");
		}
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public static DefaultTableModel getTableModel() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Nome");
		modelo.addColumn("CNPJ");
		modelo.addColumn("Telefone");
		for (Fornecedor e: new FornecedorDAO().select()) {
			String[] s = { String.valueOf(e.getCodigo()), 
										  e.getNome(), 
										  e.getCnpj(), 
										  e.getTelefone() };
			modelo.addRow(s);
		}
		return modelo;
	}

	@Override
	public String toString() {
		return ("Fornecedor: [" + "CNPJ: " + getCnpj() + "\tNome: " + getNome() + "]");
	}

}