package br.ufrn.bti.banco1000.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import br.ufrn.bti.banco1000.constantes.Comandos;
import br.ufrn.bti.banco1000.controller.UsuarioController;
import br.ufrn.bti.banco1000.exceptions.CustomException;
import br.ufrn.bti.banco1000.exceptions.NegocioException;
import br.ufrn.bti.banco1000.exceptions.SegurancaException;
import br.ufrn.bti.banco1000.model.Pessoa;
import br.ufrn.bti.banco1000.model.Usuario;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class UsuarioView extends GenericView {
	
	static UsuarioController usuarioController = new UsuarioController();
	
	public static void telaCadastroUsuario() throws CustomException, SegurancaException, NegocioException {
		System.out.println("---- CADASTRAR-SE ----");
		System.out.println("--- Formulário de Cadastro ----");
		
		leitor = new Scanner(System.in);
		
		String nome, cpf, email, telefone, senha;
		Date dataNascimento;
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);

        System.out.print("NOME: ");
        nome = leitor.nextLine();

        System.out.print("CPF: ");
        cpf = leitor.nextLine();

        System.out.print("DATA DE NASCIMENTO (dd/MM/yyyy): ");
        String dataInformada = leitor.nextLine();
        dataNascimento = null;
        
        try {
            dataNascimento = format.parse(dataInformada);
        } catch (ParseException e) {
            System.out.println("Data de nascimento inválida. Use o formato dd/MM/yyyy.\n");
            telaCadastroUsuario();
            return;
        }

        System.out.print("EMAIL: ");
        email = leitor.nextLine();

        System.out.print("Nº CELULAR: ");
        telefone = leitor.nextLine();

        System.out.print("SENHA: ");
        senha = leitor.nextLine();
	    System.out.println("---------------------------------");
	    
	    Usuario usuario = new Usuario(cpf, senha);
		Pessoa pessoa = new Pessoa(nome, email, telefone, cpf, dataNascimento);
	    	
		usuario.setPessoa(pessoa);
	    	
		usuarioController.processarByOpcao(Comandos.CADASTRAR_USUARIO, usuario);
	}
	
	public static void telaEntrarSistema() throws CustomException, SegurancaException, NegocioException {
		leitor = new Scanner(System.in);
		
		System.out.println("---- ENTRAR NO SISTEMA ----");
		System.out.print("Login (CPF): ");
		String login = leitor.next();
		System.out.print("Senha: ");
		String senha = leitor.next();
		System.out.println("---------------------------------");
		
		usuarioController.processarByOpcao(Comandos.ENTRAR_SISTEMA, new Usuario(login, senha));			
	}
}
