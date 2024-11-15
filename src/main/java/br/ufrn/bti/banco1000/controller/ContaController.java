package br.ufrn.bti.banco1000.controller;

import br.ufrn.bti.banco1000.custom.exceptions.CustomException;
import br.ufrn.bti.banco1000.custom.exceptions.SegurancaException;
import br.ufrn.bti.banco1000.model.Conta;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class ContaController extends GenericController<Conta> {

	@Override
	public void processarByOpcao(int opcao, Conta obj) throws CustomException, SegurancaException {
		clearMensagens();
	}

}
