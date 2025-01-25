package br.ufrn.bti.banco1000.controller;

import br.ufrn.bti.banco1000.exceptions.CustomException;
import br.ufrn.bti.banco1000.exceptions.NegocioException;
import br.ufrn.bti.banco1000.exceptions.SegurancaException;
import br.ufrn.bti.banco1000.model.Usuario;

/** 
* 
* @author Gilson Kedson 
* 
*/
public abstract class GenericController<T> {
	
	public abstract void processarByOpcao(int opcao, T obj) throws CustomException, SegurancaException, NegocioException;
	
	protected void redirectViewByOpcao(int opcao) throws CustomException, SegurancaException, NegocioException{
		BancoAppController.redirectViewByOpcao(opcao);
	}
	
	protected void redirectViewLogadoByOpcao(int opcao) throws CustomException, SegurancaException, NegocioException{
		BancoAppController.redirectViewLogadoByOpcao(opcao);
	}
	
	private Usuario usuarioAutenticado;
	
	public boolean hasUsuarioAutenticado() {
		return usuarioAutenticado != null && usuarioAutenticado.getId() > 0;
	}
	
	public Usuario getUsuarioAutenticado() throws SegurancaException {
		if(hasUsuarioAutenticado()) {			
			return usuarioAutenticado;
		}
		
		throw new SegurancaException("É necessário estar autenticado.");
	}
	
	protected void setUsuarioAutenticado(Usuario usuario) {
		this.usuarioAutenticado = usuario;
	}
}
