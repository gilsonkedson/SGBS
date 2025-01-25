package br.ufrn.bti.banco1000.view;

import java.util.Scanner;

import br.ufrn.bti.banco1000.constantes.Comandos;
import br.ufrn.bti.banco1000.controller.ContaController;
import br.ufrn.bti.banco1000.dataset.AgenciaDataset;
import br.ufrn.bti.banco1000.exceptions.CustomException;
import br.ufrn.bti.banco1000.exceptions.NegocioException;
import br.ufrn.bti.banco1000.exceptions.SegurancaException;
import br.ufrn.bti.banco1000.model.conta.AgenciaConta;
import br.ufrn.bti.banco1000.model.conta.Conta;


/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class ContaView extends GenericView {
	private static ContaController contaController = new ContaController();
	
	public static void telaCadastroConta() throws CustomException, SegurancaException, NegocioException {
		leitor = new Scanner(System.in);
		
		System.out.println("---- CRIAR CONTA ----");
		System.out.println("--- Formulário de Cadastro ----");
		
		double saldo;
		int numeroIdentificadorAgencia;
		char tipoConta;
		
		apresentarAgenciasDisponiveis();
		System.out.print("Agência: ");
		numeroIdentificadorAgencia = leitor.nextInt();
		System.out.print("Saldo inicial: ");
		saldo = leitor.nextDouble();
		System.out.print("Tipo da conta (S = Salário, C = Corrente, P = Poupança): ");
		tipoConta = leitor.next().toUpperCase().charAt(0);
		
		Conta conta = new Conta();
		conta.setAgencia(new AgenciaConta(0, numeroIdentificadorAgencia));
		conta.setSaldo(saldo);
		conta.setTipoConta(tipoConta);
		conta.setCliente(usuarioController.getUsuarioAutenticado().getPessoa());
		
		contaController.processarByOpcao(Comandos.CADASTRAR_NOVA_CONTA, conta);
	}

	private static void apresentarAgenciasDisponiveis() {
		AgenciaDataset agenciaDS = new AgenciaDataset();
		
		System.out.println("Agências disponíveis: ");
		for(AgenciaConta agencia : agenciaDS.recuperarAgencias()) {
			System.out.println(agencia);
		}
	}
}
