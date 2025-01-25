package br.ufrn.bti.banco1000.exceptions;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class CustomException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomException(String mensagem) {
		super(mensagem);
	}
}
