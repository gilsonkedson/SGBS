package br.ufrn.bti.banco1000.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.bti.banco1000.dataset.UsuarioDataset;
import br.ufrn.bti.banco1000.dataset.UsuarioMapper;
import br.ufrn.bti.banco1000.exceptions.NegocioException;
import br.ufrn.bti.banco1000.model.conta.Conta;
import br.ufrn.bti.banco1000.utils.Utils;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class Usuario extends GenericModel {
	private String login;
	private String senha;
	private Pessoa pessoa;
	private char tipoUsuario;
	private List<Conta> contas = new ArrayList<>();
	
	public Usuario() {
		super();
	}
	
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public char getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(char tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	@Override
	public void validate() throws NegocioException {
		boolean hasErros = false;
		
		if(login == null || login.isEmpty()) {
			hasErros = true;
			System.out.println("Login: não informado.");
		}
		
		if(senha == null || senha.isEmpty()) {
			System.out.println("Senha: não informado.");
		}

		if(pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
			hasErros = true;
			System.out.println("Nome: não informado.");
		}
		
		if(pessoa.getEmail() == null || pessoa.getEmail().isEmpty()) {
			hasErros = true;
			System.out.println("Email: não informado.");
		}
		
		if(pessoa.getNumeroCelular() == null || pessoa.getNumeroCelular().isEmpty()) {
			hasErros = true;
			System.out.println("Nº celular: não informado.");
		}
		
		if(hasErros) {
			throw new NegocioException("Formulário preenchido incorretamente.");
		}
	}

	@Override
	public void build(String[] args) throws ParseException {
        this.setId(Integer.parseInt(args[UsuarioMapper.ID]));
        this.setLogin(args[UsuarioMapper.LOGIN]);
        this.setSenha(args[UsuarioMapper.SENHA]);
        this.setPessoa(UsuarioDataset.pessoasMap.get(Integer.parseInt(args[UsuarioMapper.ID_PESSOA])));
        this.setDataCadastro(Utils.getDataFormatada(args[UsuarioMapper.DATA_CADASTRO]));
	}
	
}
