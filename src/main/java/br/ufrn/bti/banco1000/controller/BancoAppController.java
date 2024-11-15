package br.ufrn.bti.banco1000.controller;

import br.ufrn.bti.banco1000.constantes.Constantes;
import br.ufrn.bti.banco1000.custom.exceptions.CustomException;
import br.ufrn.bti.banco1000.custom.exceptions.SegurancaException;
import br.ufrn.bti.banco1000.dataset.UsuarioDataset;
import br.ufrn.bti.banco1000.enums.TipoUsuarioEnum;
import br.ufrn.bti.banco1000.model.Cliente;
import br.ufrn.bti.banco1000.model.Usuario;
import br.ufrn.bti.banco1000.view.BancoAppView;
import br.ufrn.bti.banco1000.view.UsuarioView;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class BancoAppController {

	public void redirectViewByOpcao(int opcao) throws CustomException, SegurancaException {
			switch(opcao) {
				case Constantes.TELA_INICIAL:
					BancoAppView.apresentaMenuPrincipal = true;
					BancoAppView.iniciarApp();
					break;
				case Constantes.ENTRAR_SISTEMA:
					BancoAppView.apresentaMenuPrincipal = false;
					UsuarioView.telaEntrarSistema();
					break;
				case Constantes.CADASTRAR_USUARIO:
					BancoAppView.apresentaMenuPrincipal = false;
					UsuarioView.telaCadastroUsuario();				
					break;
				case Constantes.TELA_PRINCIPAL:
					BancoAppView.apresentaMenuPrincipal = false;
					BancoAppView.telaPrincipal();				
					break;
				case Constantes.ENCERRAR_PROGRAMA:
					BancoAppView.encerrarPrograma = true;
					break;
				default:
					throw new CustomException(Constantes.MENSAGEM_OPCAO_INVALIDA);
		}
	}
}
