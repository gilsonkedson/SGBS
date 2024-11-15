package br.ufrn.bti.banco1000.view;

import java.util.Scanner;

import br.ufrn.bti.banco1000.constantes.Constantes;
import br.ufrn.bti.banco1000.controller.BancoAppController;
import br.ufrn.bti.banco1000.controller.ContaController;
import br.ufrn.bti.banco1000.controller.UsuarioController;
import br.ufrn.bti.banco1000.custom.exceptions.CustomException;
import br.ufrn.bti.banco1000.custom.exceptions.SegurancaException;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class BancoAppView {
	
	private static Scanner LEITOR;
	private static int opcaoMenu;
	public static boolean encerrarPrograma = false;
	public static boolean apresentaMenuPrincipal = true;
	
	private static BancoAppController bancoAppController = new BancoAppController();
	private static UsuarioController usuarioController = UsuarioView.usuarioController;
	
	public BancoAppView() {
		iniciarApp();
	}
	
	public static void iniciarApp() {
		usuarioController.apresentarMensagens();
		usuarioController.clearMensagens();
		
		LEITOR = new Scanner(System.in);
		
		System.out.println(Constantes.MENSAGEM_SAUDACAO);
		System.out.println(Constantes.MENSAGEM_ESCOLHA);
		
		while(!encerrarPrograma) {
			if(apresentaMenuPrincipal)
				menuPrincipal();
			
			try {
				opcaoMenu = LEITOR.nextInt();
				bancoAppController.redirectViewByOpcao(opcaoMenu);				
			} catch (CustomException | SegurancaException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	private static void menuPrincipal() {
		for(Integer opcao : Constantes.OPCOES_TELA_INICIAL.keySet()) {
			System.out.println(opcao + " - " + Constantes.OPCOES_TELA_INICIAL.get(opcao));
		}
		System.out.println(Constantes.MENSAGEM_TRACINHOS);
	}
	
	private static void menuLogado() {
		for(Integer opcao : Constantes.OPCOES_USUARIO_LOGADO.keySet()) {
			System.out.println(opcao + " - " + Constantes.OPCOES_USUARIO_LOGADO.get(opcao));
		}
		System.out.println(Constantes.MENSAGEM_TRACINHOS);
	}
	
	public static void telaPrincipal() {
		usuarioController.apresentarMensagens();
		
		LEITOR = new Scanner(System.in);
		
		if(usuarioController.hasUsuarioAutenticado()) {
			System.out.println(Constantes.MENSAGEM_BANCO);
			System.out.println("Bem vindo, %s.".formatted(usuarioController.getUsuarioAutenticado().getPessoaCliente().getNome()));
			System.out.println();
			System.out.println(Constantes.MENSAGEM_ESCOLHA);
			
			menuLogado();
			
			try {
				opcaoMenu = LEITOR.nextInt();
				usuarioController.processarByOpcao(opcaoMenu, null);
			} catch (CustomException | SegurancaException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
