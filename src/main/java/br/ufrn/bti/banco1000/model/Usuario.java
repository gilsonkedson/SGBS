package br.ufrn.bti.banco1000.model;

import java.util.Date;

public class Usuario {
	private int id;
	private String login;
	private String senha;
	private Cliente cliente;
	private char tipoUsuario;
	private Date dataCadastro;
	private Date dataModificacao;
	
	public Usuario() {}
	
	public Usuario(Cliente cliente, String senha, char tipoUsuario) {
		this.cliente = cliente;
		this.login = cliente.getCpf();
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Cliente getPessoaCliente() {
		return cliente;
	}
	
	public void setPessoaCliente(Cliente pessoaCliente) {
		this.cliente = pessoaCliente;
	}
	
	public char getTipoUsuario() {
		return tipoUsuario;
	}
	
	public void setTipoUsuario(char tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Date getDataModificacao() {
		return dataModificacao;
	}
	
	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	
	public void validate() {
		
	}
	
	@Override
	public String toString() {
		return "Cliente: %s - %s".formatted(cliente.getNome(), cliente.getCpf());
	}
	
}
