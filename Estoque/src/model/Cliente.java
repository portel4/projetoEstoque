package model;

public class Cliente {
	
	private int codigo;
	private String nome;
	private String cpf;
	private String celular;
	private String email;
	

	public Cliente(String nome, String cpf, String celular, String email) {
		this(0,nome,cpf,celular,email);
	}
	public Cliente(int codigo, String nome, String cpf, String celular, String email) {
		setCodigo(codigo);
		setNome(nome);
		setCpf(cpf);
		setCelular(celular);
		setEmail(email);
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return ( "Cliente: [" +
				 getCpf() + 
				 "\t - " + getNome() +
				 "\t - " + getCelular() + "]");
	}

}
