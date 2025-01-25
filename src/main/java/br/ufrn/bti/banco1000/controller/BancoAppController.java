package br.ufrn.bti.banco1000.controller;

import java.util.HashMap;
import java.util.Map;

import br.ufrn.bti.banco1000.constantes.Comandos;
import br.ufrn.bti.banco1000.exceptions.CustomException;
import br.ufrn.bti.banco1000.exceptions.NegocioException;
import br.ufrn.bti.banco1000.exceptions.SegurancaException;
import br.ufrn.bti.banco1000.view.BancoAppView;
import br.ufrn.bti.banco1000.view.ContaView;
import br.ufrn.bti.banco1000.view.UsuarioView;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class BancoAppController {
	public static final Map<Integer, String> OPCOES_TELA_INICIAL = new HashMap<>();
	
	static {
		OPCOES_TELA_INICIAL.put(Comandos.ENTRAR_SISTEMA, "Entrar no Sistema");
		OPCOES_TELA_INICIAL.put(Comandos.CADASTRAR_USUARIO, "Cadastrar-se");
		OPCOES_TELA_INICIAL.put(Comandos.ENCERRAR_PROGRAMA, "Encerrar Programa");
	}
	
	public static final Map<Integer, String> OPCOES_USUARIO_LOGADO = new HashMap<>();
	
	static {
		OPCOES_USUARIO_LOGADO.put(Comandos.ENCERRAR_SESSAO_USUARIO, "Encerrar Sessão.");
		OPCOES_USUARIO_LOGADO.put(Comandos.VISUALIZAR_MINHAS_CONTAS, "Acessar Minhas Contas.");
		OPCOES_USUARIO_LOGADO.put(Comandos.CADASTRAR_NOVA_CONTA, "Criar Nova Conta.");
	}
	
	public static void redirectViewByOpcao(int opcao) throws CustomException, SegurancaException, NegocioException {
		switch(opcao) {
			case Comandos.TELA_INICIAL:
				BancoAppView.iniciarApp();
				break;
			case Comandos.ENTRAR_SISTEMA:
				UsuarioView.telaEntrarSistema();
				break;
			case Comandos.CADASTRAR_USUARIO:
				UsuarioView.telaCadastroUsuario();
				break;
			case Comandos.ENCERRAR_PROGRAMA:
				break;
			default:
				throw new CustomException("Ops, opção inválida. Tente novamente.\n");
		}
	}
	
	public static void redirectViewLogadoByOpcao(int opcao) throws CustomException, SegurancaException, NegocioException {
		switch(opcao) {
			case Comandos.TELA_USUARIO_LOGADO:
				BancoAppView.showTelaUsuarioLogado();
			case Comandos.VISUALIZAR_MINHAS_CONTAS:
				BancoAppView.showMinhasContas();
				break;
			case Comandos.CADASTRAR_NOVA_CONTA:
				ContaView.telaCadastroConta();
				break;
			case Comandos.ENCERRAR_SESSAO_USUARIO:
				break;
			default:
				throw new CustomException("Ops, opção inválida. Tente novamente.\n");
		}
	}
}
