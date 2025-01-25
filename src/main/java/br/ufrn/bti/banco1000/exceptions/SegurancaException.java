package br.ufrn.bti.banco1000.exceptions;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class SegurancaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SegurancaException() {
		super("É preciso estar autenticado para acessar esta funcionalidade.");
	}
	
	public SegurancaException(String mensagem) {
		super(mensagem);
	}
}
