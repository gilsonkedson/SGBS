package br.ufrn.bti.banco1000.constantes;

import java.util.HashMap;
import java.util.Map;

public class Constantes {
	// OPERAÇÕES DO SISTEMA
	public static final int ENCERRAR_SESSAO = 0;
	public static final int ENTRAR_SISTEMA = 1;
	public static final int CADASTRAR_USUARIO = 2;
	
	// OPERAÇÕES BANCÁRIAS
	public static final int DEPOSITAR = 1;
	public static final int SACAR = 2;
	public static final int TRANSFERIR = 3;
	public static final int PIX = 4;
	
	// MENSAGENS
	public static final String MENSAGEM_SAUDACAO = "---- Bem vindo ao IMD Bank. ----";
	public static final String MENSAGEM_ESCOLHA = "Escolha uma de nossas opções disponíveis: ";
	public static final String MENSAGEM_ENCERRAMENTO = "Aplicação encerrada com sucesso.";
	public static final String MENSAGEM_TRACINHOS = "----------------------------";
	public static final String MENSAGEM_BANCO = "---- IMD BANK ----";
	public static final String MENSAGEM_LOGIN = "---- ENTRAR NO SISTEMA ----";
	public static final String MENSAGEM_CADASTRO = "---- TELA DE CADASTRO ----";
	public static final String MENSAGEM_INFORMACOES_PESSOAIS = "-- Informações de Cadastro --";
	
	// CAMPOS
	public static final String CAMPO_NOME = "Nome: ";
	public static final String CAMPO_CPF= "CPF: ";
	public static final String CAMPO_EMAIL= "Email: ";
	public static final String CAMPO_TELEFONE= "Telefone: ";
	public static final String CAMPO_LOGIN = "Usuário (CPF): ";
	public static final String CAMPO_SENHA = "Senha: ";
	
	public static Map<Integer, String> OPCOES_TELA_INICIAL = new HashMap<Integer, String>();
	
	static {
		OPCOES_TELA_INICIAL.put(ENCERRAR_SESSAO, "Encerrar Sessão.");
		OPCOES_TELA_INICIAL.put(ENTRAR_SISTEMA, "Entrar no Sistema.");
		OPCOES_TELA_INICIAL.put(CADASTRAR_USUARIO, "Cadastrar-se.");
	}
	
	public static Map<Integer, String> OPERACOES_DISPONIVEIS = new HashMap<Integer, String>();
	
	static {
		OPERACOES_DISPONIVEIS.put(DEPOSITAR, "Realizar Depósito.");
		OPERACOES_DISPONIVEIS.put(DEPOSITAR, "Realizar Saque.");
		OPERACOES_DISPONIVEIS.put(DEPOSITAR, "Realizar Transferência.");
		OPERACOES_DISPONIVEIS.put(DEPOSITAR, "Realizar PIX.");
	}
}
