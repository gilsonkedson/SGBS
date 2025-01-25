package br.ufrn.bti.banco1000.controller;

import br.ufrn.bti.banco1000.constantes.Comandos;
import br.ufrn.bti.banco1000.dataset.AgenciaDataset;
import br.ufrn.bti.banco1000.dataset.ContaDataset;
import br.ufrn.bti.banco1000.dataset.UsuarioDataset;
import br.ufrn.bti.banco1000.exceptions.CustomException;
import br.ufrn.bti.banco1000.exceptions.NegocioException;
import br.ufrn.bti.banco1000.exceptions.SegurancaException;
import br.ufrn.bti.banco1000.model.conta.Conta;

public class ContaController extends GenericController<Conta> {

	ContaDataset contaDS = new ContaDataset();
	
	@Override
	public void processarByOpcao(int opcao, Conta obj) throws CustomException, SegurancaException, NegocioException {
		switch(opcao) {
			case(Comandos.CADASTRAR_NOVA_CONTA):
				cadastrar(obj);
				break;
		}
	}
	
	private void cadastrar(Conta obj) throws CustomException, NegocioException {
		obj.setCliente(UsuarioDataset.recuperarPessoa(obj.getCliente().getId()));
		obj.setAgencia(AgenciaDataset.recuperarPorNumeroIdentificador(obj.getAgencia().getNumeroIdentificador()));
		obj.validate();
		contaDS.criar(obj);
	}
	
}
