package br.ufrn.bti.banco1000.controller;

import br.ufrn.bti.banco1000.constantes.Constantes;
import br.ufrn.bti.banco1000.view.BancoView;

public class BancoController {
	public void processarOpcaoEscolhidaMenuInicial(int opcao) throws Exception {
		if(!Constantes.OPCOES_TELA_INICIAL.containsKey(opcao)) {
			throw new Exception("Opção inválida. Tente novamente.\n");
		}
		
		switch(opcao) {
			case Constantes.ENTRAR_SISTEMA:
				BancoView.telaEntrarSistema();
				break;
			case Constantes.CADASTRAR_USUARIO:	
				BancoView.telaCadastroUsuario();
				break;
			default:
				BancoView.encerraExecucao();
				break;
		}
	}
}
