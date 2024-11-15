/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

import java.util.ArrayList;

public class Conta extends GenericModel {
    private String nome;
    private Cliente cliente;
    private Usuario usuario;
    private int agencia;
    private int numeroConta;
    private char tipo;
    private int senha;
    private double saldo;
    private ArrayList<Movimentacao> movimentacao = new ArrayList<>();

    public Conta(String nome, int agencia, int numeroConta, char tipo, int senha, double saldo, ArrayList<Movimentacao> movimentacao) {
        this.nome = nome;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.tipo = tipo;
        this.senha = senha;
        this.saldo = saldo;
        this.movimentacao = movimentacao;
    }
    
    public Conta(String nome, int agencia, int numeroConta, char tipo, int senha, double saldo) {
        this.nome = nome;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.tipo = tipo;
        this.senha = senha;
        this.saldo = saldo;
    }
    
    public void depositar(double valor) {
        this.saldo = this.saldo + valor;
        this.movimentacao.add(new Movimentacao("FORMA", this.cliente, "DEPOSITO", 
            valor));
        
    }
    
    public void sacar(double valor) {
        if (this.saldo - valor >= 0) {
            this.saldo = this.saldo - valor;
            this.movimentacao.add(new Movimentacao("FORMA", this.cliente, "SAQUE", 
            valor));
            
        }
    }
    
    public void transferir(int agencia, int numeroConta, double valor) {
        if (this.saldo - valor >= 0) {
           this.saldo = this.saldo - valor; 
        }
    }
    
    public void transferir(Conta conta, double valor) {
        
         if (this.saldo - valor >= 0) {
           this.sacar(valor);
           conta.depositar(valor);
           conta.movimentacao.add(new Movimentacao("FORMA", this.cliente, 
                   "ENTRADA POR TRANSFERENCIA", valor));
           this.movimentacao.add(new Movimentacao("FORMA", this.cliente, 
                   "SAIDA POR TRANSFERENCIA", valor));
        }
    }
    
    public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public int getAgencia() {
		return agencia;
	}
	
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	
	public int getNumeroConta() {
		return numeroConta;
	}
	
	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	public char getTipo() {
		return tipo;
	}
	
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
	public int getSenha() {
		return senha;
	}
	
	public void setSenha(int senha) {
		this.senha = senha;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public ArrayList<Movimentacao> getMovimentacao() {
		return movimentacao;
	}
	
	public void setMovimentacao(ArrayList<Movimentacao> movimentacao) {
		this.movimentacao = movimentacao;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    
	    Conta conta = (Conta) obj;
	    
	    if (this.id == conta.getId()) {
	        return true;
	    }
	    
	    if (conta.getNumeroConta() == this.getNumeroConta()) {
	        return true;
	    }
	    
	    return false;
    }
    
    @Override
    public String toString() {
        return "";
    }
}
