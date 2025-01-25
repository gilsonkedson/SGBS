package br.ufrn.bti.banco1000.enums;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public enum TipoMovimentacaoEnum {
	DEPOSITO('D'),
	SAQUE('S'),
	TRANSFERENCIA('T');
	
	public char tipoMovimentacao;
	
	TipoMovimentacaoEnum(char tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
}
