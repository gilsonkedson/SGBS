package br.ufrn.bti.banco1000.model;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class Usuario extends GenericModel {
	private String login;
	private String senha;
	private Cliente cliente;
	private char tipoUsuario;
	
	public Usuario() {}
	
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario(String nome, String cpf, String email, String telefone, String senha) {
		this.cliente = new Cliente(nome, cpf, email, telefone);
		this.senha = senha;
	}
	
	public Usuario(Cliente cliente, String senha, char tipoUsuario) {
		this.cliente = cliente;
		this.login = cliente.getCpf();
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
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
	
	@Override
	public String toString() {
		return "Cliente: %s - %s".formatted(cliente.getNome(), cliente.getCpf());
	}
	
}
