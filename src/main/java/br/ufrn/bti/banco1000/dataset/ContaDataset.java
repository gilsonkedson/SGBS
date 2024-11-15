package br.ufrn.bti.banco1000.dataset;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.ufrn.bti.banco1000.model.Conta;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class ContaDataset implements GenericDataset<Conta> {
	private static Map<Integer, Conta> contas = new HashMap<Integer, Conta>();

	@Override
	public void criar(Conta obj) throws Exception {
	    int numeroConta;
	    
	    do {
	        numeroConta = gerarNumeroConta();
	    } while (contas.containsKey(numeroConta));

	    obj.setId(gerarProximoId());
	    obj.setNumeroConta(numeroConta);
	    obj.setDataCadastro(new Date());

	    contas.put(obj.getNumeroConta(), obj);
	}

	@Override
	public Conta recuperar(int id) {
		return contas.values().stream()
		        .filter(conta -> conta.getId() == id)
		        .findFirst()
		        .orElse(null);
	}

	@Override
	public Conta atualizar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public int gerarProximoId() {
		return contas.isEmpty() ? 1 : Collections.max(contas.values(), Comparator.comparingInt(Conta::getId)).getId() + 1;
	}
	
	public static List<Conta> recuperarContasPorCpf(String cpf) {
		return contas.values().stream()
		        .filter(conta -> conta.getCliente().getCpf().equals(cpf)).toList();
	}
	
	private int gerarNumeroConta() {
		return (int)(Math.random() * Integer.MAX_VALUE);
	}
}
