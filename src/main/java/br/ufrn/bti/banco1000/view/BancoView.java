package br.ufrn.bti.banco1000.view;

import java.util.Scanner;

import br.ufrn.bti.banco1000.constantes.Constantes;
import br.ufrn.bti.banco1000.controller.BancoController;

public class BancoView {
	private static BancoController bancoController = new BancoController();
	private static Boolean isExecucao = true;
	private static int opcaoEmUtilizacaoMenuInicial;
	
	public static void iniciarApp() {
		System.out.println("Bem vindo ao IMD Bank.");
		System.out.println("Escolha uma de nossas opções disponíveis: ");
		System.out.println();
		
		Scanner leitor = new Scanner(System.in);
		
		while(isExecucao) {
			apresentaMenuInicial();
			try {
				opcaoEmUtilizacaoMenuInicial = leitor.nextInt();
				bancoController.processarOpcaoEscolhidaMenuInicial(opcaoEmUtilizacaoMenuInicial);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} 
		}
		
		leitor.close();
	}
	
	private static void apresentaMenuInicial() {	
		for(Integer opcao : Constantes.OPCOES_TELA_INICIAL.keySet()) {
			System.out.println(opcao + " - " + Constantes.OPCOES_TELA_INICIAL.get(opcao));
		}
		
		System.out.println();
	}
	
	public static void encerraExecucao() {
			isExecucao = false;
			System.out.println("Aplicação encerrada com sucesso.");
	}
	
	public static void telaEntrarSistema() {
		
	}
	
	public static void telaCadastroUsuario() {
		
	}
	
}
