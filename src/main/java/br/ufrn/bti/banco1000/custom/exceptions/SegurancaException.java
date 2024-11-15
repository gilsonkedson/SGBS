package br.ufrn.bti.banco1000.custom.exceptions;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class SegurancaException extends Exception {
	public SegurancaException() {
		super("É preciso estar autenticado para acessar esta funcionalidade.");
	}
	
	public SegurancaException(String mensagem) {
		super(mensagem);
	}
}
