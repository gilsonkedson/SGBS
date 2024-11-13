package br.ufrn.bti.banco1000.view;

import java.util.Scanner;

import br.ufrn.bti.banco1000.constantes.Constantes;
import br.ufrn.bti.banco1000.controller.BancoController;

public class BancoView {
	private static BancoController bancoController = new BancoController();
	private static Boolean isExecucao = true;
	private static Boolean isTelaInicial = true;
	private static int opcaoEmUtilizacaoMenuInicial;
	private static Scanner leitor = new Scanner(System.in);
	private static String login;
	private static String senha;
	
	public static void iniciarApp() {
		System.out.println(Constantes.MENSAGEM_SAUDACAO);
		System.out.println(Constantes.MENSAGEM_ESCOLHA);
		System.out.println();
		
		while(isExecucao) {
			if(isTelaInicial) {
				apresentaMenuInicial();
				
				try {
					opcaoEmUtilizacaoMenuInicial = leitor.nextInt();
					bancoController.processarOpcaoEscolhidaMenuInicial(opcaoEmUtilizacaoMenuInicial);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} 
			}
		}
		
		leitor.close();
	}
	
	private static void apresentaMenuInicial() {	
		for(Integer opcao : Constantes.OPCOES_TELA_INICIAL.keySet()) {
			System.out.println(opcao + " - " + Constantes.OPCOES_TELA_INICIAL.get(opcao));
		}
		System.out.println(Constantes.MENSAGEM_TRACINHOS);
	}
	
	public static void encerraExecucao() {
			isExecucao = false;
			System.out.println(Constantes.MENSAGEM_ENCERRAMENTO);
	}
	
	public static void setOpcaoEmUtilizacao(int opcao) {
		opcaoEmUtilizacaoMenuInicial = opcao;
	}
	
	public static void setTelaInicial(boolean telaInicial) {
		isTelaInicial = telaInicial;
	}
	
	public static void telaEntrarSistema() {
		if(opcaoEmUtilizacaoMenuInicial != Constantes.ENTRAR_SISTEMA)
			opcaoEmUtilizacaoMenuInicial = Constantes.ENTRAR_SISTEMA;
		
		System.out.println(Constantes.MENSAGEM_LOGIN);
		System.out.print(Constantes.CAMPO_LOGIN);
		login = leitor.next();
		
		System.out.print(Constantes.CAMPO_SENHA);
		senha = leitor.next();
		System.out.println(Constantes.MENSAGEM_TRACINHOS);
		
		bancoController.login(login, senha);
	}
	
	public static void telaCadastroUsuario() {
		if(opcaoEmUtilizacaoMenuInicial != Constantes.CADASTRAR_USUARIO)
			opcaoEmUtilizacaoMenuInicial = Constantes.CADASTRAR_USUARIO;
		
		String nome, cpf, email, telefone, senha;
		System.out.println(Constantes.MENSAGEM_CADASTRO);
		System.out.println(Constantes.MENSAGEM_INFORMACOES_PESSOAIS);
		
		System.out.print(Constantes.CAMPO_NOME);
	    leitor.nextLine();
	    nome = leitor.nextLine();
	    System.out.print(Constantes.CAMPO_CPF);
	    cpf = leitor.next();
	    System.out.print(Constantes.CAMPO_EMAIL);
	    email = leitor.next();
	    System.out.print(Constantes.CAMPO_TELEFONE);
	    telefone = leitor.next();
	    System.out.print(Constantes.CAMPO_SENHA);
	    senha = leitor.next();
	    System.out.println(Constantes.MENSAGEM_TRACINHOS);
		
		bancoController.criarUsuario(nome, cpf, email, telefone, senha);
	}
	
	public static void telaInicial() {
		System.out.println();
		if(bancoController.hasUsuarioAutenticado()) {
			System.out.println(Constantes.MENSAGEM_BANCO);
			System.out.println("Bem vindo, %s.".formatted(bancoController.getUsuarioAutenticado().getPessoaCliente().getNome()));
			System.out.println(Constantes.MENSAGEM_TRACINHOS);
		}else {
			try {
				bancoController.processarOpcaoEscolhidaMenuInicial(opcaoEmUtilizacaoMenuInicial);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	
	public static void resetCredenciais() {
		login = "";
		senha = "";
	}
	
}
