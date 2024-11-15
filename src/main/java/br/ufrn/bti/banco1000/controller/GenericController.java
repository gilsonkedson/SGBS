package br.ufrn.bti.banco1000.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.bti.banco1000.constantes.Constantes;
import br.ufrn.bti.banco1000.custom.exceptions.CustomException;
import br.ufrn.bti.banco1000.custom.exceptions.SegurancaException;
import br.ufrn.bti.banco1000.model.Usuario;
import br.ufrn.bti.banco1000.view.BancoAppView;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public abstract class GenericController<T> {
	
	public abstract void processarByOpcao(int opcao, T obj) throws CustomException, SegurancaException;
	
	protected void redirectViewByOpcao(int opcao) throws CustomException, SegurancaException{
		new BancoAppController().redirectViewByOpcao(opcao);
	}
	
	private Usuario usuarioAutenticado;
	protected List<String> erros = new ArrayList<>();
	protected List<String> sucessos = new ArrayList<>();
	
	public boolean hasUsuarioAutenticado() {
		return usuarioAutenticado != null && usuarioAutenticado.getId() > 0;
	}
	
	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}
	
	protected void setUsuarioAutenticado(Usuario usuario) {
		this.usuarioAutenticado = usuario;
	}
	
	public boolean hasErro() {
		return erros.size() > 0;
	}
	
	public void apresentarErros() {
		for(String erro : erros) {
			System.out.println(erro);
		}
	}
	
	public boolean hasSucesso() {
		return sucessos.size() > 0;
	}
	
	public void apresentarMensagens() {
		for(String sucesso : sucessos) {
			System.out.println(sucesso);
		}
		
		for(String erro : erros) {
			System.out.println(erro);
		}
	}
	
	public void clearMensagens() {
		sucessos.clear();
		erros.clear();
	}
}
