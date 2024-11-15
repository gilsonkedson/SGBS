package br.ufrn.bti.banco1000.view;

import java.util.Scanner;

import br.ufrn.bti.banco1000.constantes.Constantes;
import br.ufrn.bti.banco1000.controller.UsuarioController;
import br.ufrn.bti.banco1000.custom.exceptions.CustomException;
import br.ufrn.bti.banco1000.custom.exceptions.SegurancaException;
import br.ufrn.bti.banco1000.model.Usuario;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class UsuarioView {
	
	private static Scanner LEITOR;
	public static UsuarioController usuarioController = new UsuarioController();
	
	private static String login;
	private static String senha;
	
	public static void telaEntrarSistema() throws SegurancaException, CustomException {
		resetCredenciais();
		
		usuarioController.apresentarMensagens();
		
		LEITOR = new Scanner(System.in);
		
		System.out.println(Constantes.MENSAGEM_LOGIN);
		System.out.print(Constantes.CAMPO_LOGIN);
		login = LEITOR.next();
		
		System.out.print(Constantes.CAMPO_SENHA);
		senha = LEITOR.next();
		System.out.println(Constantes.MENSAGEM_TRACINHOS);
		
		usuarioController.processarByOpcao(Constantes.ENTRAR_SISTEMA, new Usuario(login, senha));
	}
	
	public static void telaCadastroUsuario() throws CustomException, SegurancaException {
		usuarioController.apresentarMensagens();
		
		LEITOR = new Scanner(System.in);
		
		String nome, cpf, email, telefone, senha;
		System.out.println(Constantes.MENSAGEM_CADASTRO);
		System.out.println(Constantes.MENSAGEM_INFORMACOES_PESSOAIS);
		
		System.out.print(Constantes.CAMPO_NOME);
	    nome = LEITOR.nextLine();
	    System.out.print(Constantes.CAMPO_CPF);
	    cpf = LEITOR.next();
	    System.out.print(Constantes.CAMPO_EMAIL);
	    email = LEITOR.next();
	    System.out.print(Constantes.CAMPO_TELEFONE);
	    telefone = LEITOR.next();
	    System.out.print(Constantes.CAMPO_SENHA);
	    senha = LEITOR.next();
	    System.out.println(Constantes.MENSAGEM_TRACINHOS);
		
	    usuarioController.processarByOpcao(
	    		Constantes.CADASTRAR_USUARIO, 
	    		new Usuario(nome, cpf, email, telefone, senha)
	    );
	}
	
	private static void resetCredenciais() {
		login = "";
		senha = "";
	}
}
