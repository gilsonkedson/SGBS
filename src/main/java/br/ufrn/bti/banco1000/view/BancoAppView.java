package br.ufrn.bti.banco1000.view;

import java.util.Scanner;

import br.ufrn.bti.banco1000.constantes.Comandos;
import br.ufrn.bti.banco1000.constantes.Mensagens;
import br.ufrn.bti.banco1000.controller.BancoAppController;
import br.ufrn.bti.banco1000.exceptions.CustomException;
import br.ufrn.bti.banco1000.exceptions.NegocioException;
import br.ufrn.bti.banco1000.exceptions.SegurancaException;
import br.ufrn.bti.banco1000.model.Usuario;
import br.ufrn.bti.banco1000.model.conta.Conta;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class BancoAppView extends GenericView {
	
	public static void iniciarApp() {
		leitor = new Scanner(System.in);
		int opcao;
		
		do {
			showMenuPrincipal();
			opcao = leitor.nextInt();
			
			try {
				BancoAppController.redirectViewByOpcao(opcao);
			} catch (CustomException | SegurancaException | NegocioException e) {
				System.out.println(e.getMessage());
			}
		
		} while(opcao != Comandos.ENCERRAR_PROGRAMA);
	}
	
	public static void showMinhasContas() {
		try {
			leitor = new Scanner(System.in);
			Usuario usuario = usuarioController.getUsuarioAutenticado();
			
			usuarioController.refreshContasDoUsuario();
			
			if(usuario.getContas().isEmpty()) {
				System.out.println("No momento você não possui contas a apresentar.\n");
				return;
			}
			
			System.out.println("Escolha uma das contas para operá-la:");
			for(Conta conta : usuario.getContas()) {
				System.out.println(conta);
			}
			System.out.println();
			System.out.println("Indique o Nº da conta para executar operações:");
			
			int contaSelecionada = leitor.nextInt();
		} catch (SegurancaException e) {
			return;
		}
	}
	
	private static void showMenuPrincipal() {
		System.out.println("---- BEM VINDO AO " + Mensagens.NOME_BANCO + " -----");
		System.out.println("Escolha uma de nossas opções disponíveis:");
		
		for(Integer opcao : BancoAppController.OPCOES_TELA_INICIAL.keySet()) {
			System.out.println(opcao + " - " + BancoAppController.OPCOES_TELA_INICIAL.get(opcao));
		}
		System.out.println("---------------------------------");
	}
	
	public static void showTelaUsuarioLogado() {
		leitor = new Scanner(System.in);
		int opcao;
		
		do {
			try {
				System.out.println("Bem vindo, %s.".formatted(usuarioController.getUsuarioAutenticado().getPessoa().getNome()));
			} catch (SegurancaException e) {
				return;
			}
			System.out.println();
			System.out.println("Escolha uma de nossas opções disponíveis:");
			showMenuLogado();
			
			opcao = leitor.nextInt();
			
			try {
				BancoAppController.redirectViewLogadoByOpcao(opcao);
			} catch (CustomException | SegurancaException | NegocioException e) {
				System.out.println(e.getMessage());
			}
		
		} while(opcao != Comandos.ENCERRAR_SESSAO_USUARIO);
		
		try {
			usuarioController.processarByOpcao(Comandos.ENCERRAR_SESSAO_USUARIO, null);
		} catch (CustomException | SegurancaException | NegocioException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void showMenuLogado() {
		for(Integer opcao : BancoAppController.OPCOES_USUARIO_LOGADO.keySet()) {
			System.out.println(opcao + " - " + BancoAppController.OPCOES_USUARIO_LOGADO.get(opcao));
		}
		System.out.println("---------------------------------");
	}
	
	private static void showOperacoesConta() {
		
	}
}
