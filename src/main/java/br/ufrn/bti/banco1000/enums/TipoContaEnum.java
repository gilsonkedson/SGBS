package br.ufrn.bti.banco1000.enums;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public enum TipoContaEnum {
	SALARIO('S'),
	CORRENTE('C'),
	POUPANCA('P');
	
	public char tipoConta;
	
	TipoContaEnum(char tipoConta) {
		this.tipoConta = tipoConta;
	}
}
