package br.ufrn.bti.banco1000.model;

import java.text.ParseException;
import java.util.Date;

import br.ufrn.bti.banco1000.dataset.UsuarioMapper;
import br.ufrn.bti.banco1000.utils.Utils;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class Pessoa extends GenericModel {
	private String nome;
	private String email;
	private String numeroCelular;
	private String cpf;
	private Date dataNascimento;
	
	public Pessoa() {
		super();
	}
	
	public Pessoa(int id) {
		super(id);
	}

	public Pessoa(String nome, String email, String numeroCelular, String cpf, Date dataNascimento) {
		super();
		this.nome = nome;
		this.email = email;
		this.numeroCelular = numeroCelular;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public void validate() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void build(String[] args) throws ParseException {
		this.setId(Integer.valueOf(args[UsuarioMapper.ID]));
		this.setNome(args[UsuarioMapper.NOME]);
		this.setEmail(args[UsuarioMapper.EMAIL]);
        this.setNumeroCelular(args[UsuarioMapper.NUMERO_CELULAR]);
        this.setCpf(args[UsuarioMapper.CPF]);
        this.setDataNascimento(Utils.getDataFormatada(args[UsuarioMapper.DATA_NASCIMENTO]));
        this.setDataCadastro(Utils.getDataFormatada(args[UsuarioMapper.DATA_CADASTRO_PESSOA]));
	}
}
