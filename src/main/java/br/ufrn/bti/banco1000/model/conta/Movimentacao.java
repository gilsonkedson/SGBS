package br.ufrn.bti.banco1000.model.conta;

import br.ufrn.bti.banco1000.enums.TipoMovimentacaoEnum;
import br.ufrn.bti.banco1000.exceptions.NegocioException;
import br.ufrn.bti.banco1000.model.GenericModel;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class Movimentacao extends GenericModel {
	private Conta conta;
	private Conta contaDestino;
	private char tipoMovimentacao;
	private double saldoPreMovimentacao;
	private double saldoPosMovimentacao;
	
	public void sacar(double valorSaque) throws NegocioException {
		if(valorSaque >= conta.getSaldo()) {
			throw new NegocioException("Saldo insuficiente!");
		}
		
		if(valorSaque <= 0) {
			throw new NegocioException("Valor de saque inválido!");
		}
		
		this.tipoMovimentacao = TipoMovimentacaoEnum.SAQUE.tipoMovimentacao;
		
		this.saldoPreMovimentacao = conta.getSaldo();
		this.saldoPosMovimentacao = conta.getSaldo() - valorSaque;
		
		conta.setSaldo(this.saldoPosMovimentacao);
	}
	
	public void depositar(double valorDeposito) throws NegocioException {
		if(valorDeposito <= 0) {
			throw new NegocioException("Valor de depósito inválido!");
		}
		
		this.tipoMovimentacao = TipoMovimentacaoEnum.DEPOSITO.tipoMovimentacao;
		
		this.saldoPreMovimentacao = conta.getSaldo();
		this.saldoPosMovimentacao = conta.getSaldo() + valorDeposito;
		
		conta.setSaldo(this.saldoPosMovimentacao);
	}
	
	public void transferir(double valorTransferencia) throws NegocioException {
		if(valorTransferencia >= conta.getSaldo()) {
			throw new NegocioException("Saldo insuficiente!");
		}
		
		if(conta.getId() == contaDestino.getId()) {
			throw new NegocioException("Transação inválida, não é possível transferir valores para si mesmo.");
		}
		
		this.tipoMovimentacao = TipoMovimentacaoEnum.TRANSFERENCIA.tipoMovimentacao;
		
		this.saldoPreMovimentacao = conta.getSaldo();
		this.saldoPosMovimentacao = conta.getSaldo() - valorTransferencia;
		
		conta.setSaldo(this.saldoPosMovimentacao);
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	public char getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(char tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public double getSaldoPreMovimentacao() {
		return saldoPreMovimentacao;
	}

	public void setSaldoPreMovimentacao(double saldoPreMovimentacao) {
		this.saldoPreMovimentacao = saldoPreMovimentacao;
	}

	public double getSaldoPosMovimentacao() {
		return saldoPosMovimentacao;
	}

	public void setSaldoPosMovimentacao(double saldoPosMovimentacao) {
		this.saldoPosMovimentacao = saldoPosMovimentacao;
	}

	@Override
	public void validate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void build(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
