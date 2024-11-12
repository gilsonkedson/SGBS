package br.ufrn.bti.banco1000.enums;

public enum TipoUsuarioEnum {
	Cliente('C'),
	Funcionario('F');

	public char tipo;
	
	TipoUsuarioEnum(char tipo) {
		this.tipo = tipo;
	}
}
