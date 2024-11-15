package br.ufrn.bti.banco1000.enums;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public enum TipoUsuarioEnum {
	Cliente('C'),
	Funcionario('F');

	public char tipo;
	
	TipoUsuarioEnum(char tipo) {
		this.tipo = tipo;
	}
}
