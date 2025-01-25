package br.ufrn.bti.banco1000.model.conta;

import java.text.ParseException;
import java.util.List;

import br.ufrn.bti.banco1000.dataset.ContaMapper;
import br.ufrn.bti.banco1000.dataset.UsuarioMapper;
import br.ufrn.bti.banco1000.exceptions.NegocioException;
import br.ufrn.bti.banco1000.model.GenericModel;
import br.ufrn.bti.banco1000.model.Pessoa;
import br.ufrn.bti.banco1000.utils.Utils;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class Conta extends GenericModel {
	private Integer numeroConta;
	private double saldo;
	private Pessoa cliente;
	private AgenciaConta agencia;
	private char tipoConta;
	private List<Movimentacao> movimentacoes;
	
	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public AgenciaConta getAgencia() {
		return agencia;
	}

	public void setAgencia(AgenciaConta agencia) {
		this.agencia = agencia;
	}

	public char getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(char tipo) {
		this.tipoConta = tipo;
	}
	
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	@Override
	public void validate() throws NegocioException {
		boolean hasErros = false;
		
		if(agencia == null || agencia.getId() <= 0) {
			hasErros = true;
			System.out.println("Agência: não informada ou inválida.");
		}
		
		if(hasErros) {
			throw new NegocioException("Formulário preenchido incorretamente.\n");
		}
	}

	@Override
	public void build(String[] args) throws ParseException {
		this.setId(Integer.valueOf(args[UsuarioMapper.ID]));
		this.setNumeroConta(Integer.valueOf(args[ContaMapper.NUMERO_CONTA]));
		this.setSaldo(Double.valueOf(args[ContaMapper.SALDO]));
        this.setCliente(new Pessoa(Integer.valueOf(args[ContaMapper.ID_PESSOA])));
        this.setAgencia(new AgenciaConta(Integer.valueOf(args[ContaMapper.ID_AGENCIA]), 0));
        this.setTipoConta(args[ContaMapper.TIPO].charAt(0));
        this.setDataCadastro(Utils.getDataFormatada(args[ContaMapper.DATA_CADASTRO]));
	}
	
	@Override
	public String toString() {
		return "Nº Conta: %d - Agência: %s - Tipo: %s".formatted(numeroConta, agencia, tipoConta);
	}
}
