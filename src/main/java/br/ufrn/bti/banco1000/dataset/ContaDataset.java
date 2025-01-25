package br.ufrn.bti.banco1000.dataset;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.exceptions.CsvException;

import br.ufrn.bti.banco1000.exceptions.CustomException;
import br.ufrn.bti.banco1000.exceptions.NegocioException;
import br.ufrn.bti.banco1000.model.Usuario;
import br.ufrn.bti.banco1000.model.conta.AgenciaConta;
import br.ufrn.bti.banco1000.model.conta.Conta;
import br.ufrn.bti.banco1000.utils.Utils;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class ContaDataset implements GenericDataset<Conta> {
	
	public static Map<Integer, Conta> contasMap = new HashMap<Integer, Conta>();
	private static final String NOME_ARQUIVO_CONTAS= "conta.csv";
	private static List<String[]> linhasContasCSV = null;

	@Override
	public void criar(Conta obj) throws CustomException, NegocioException {
		try {
			inicializarCacheDataset();
		} catch (IOException | CsvException | ParseException e) {
			e.printStackTrace();
		}
		
		obj.setId(gerarProximoId());
		
		int numeroConta = gerarNumeroConta();
		while(contasMap.get(numeroConta) != null)
			numeroConta = gerarNumeroConta();
		
		obj.setNumeroConta(gerarNumeroConta());
		obj.setDataCadastro(new Date());
		
		
		for(Conta conta : recuperarContasPorCpf(obj.getCliente().getCpf())) {
			if(conta.getAgencia().getId() == obj.getAgencia().getId() && conta.getTipoConta() == obj.getTipoConta()) {
				throw new NegocioException("Não foi possível a criação da nova conta. Você já possui uma outra nesta agência para o tipo escolhido: " 
						+ conta.getTipoConta() + "\n");
			}
		}
		
		contasMap.put(obj.getNumeroConta(), obj);
		
		try {
			linhasContasCSV.add(new String[] {
					String.valueOf(obj.getId()),
					String.valueOf(obj.getNumeroConta()),
					String.valueOf(obj.getSaldo()),
					String.valueOf(obj.getCliente().getId()),
					String.valueOf(obj.getAgencia().getId()),
					String.valueOf(obj.getTipoConta()),
					Utils.getDataFormatadaStr(obj.getDataCadastro())
			});
			
			linhasContasCSV = DatasetUtils.escreverLinhasCSV(NOME_ARQUIVO_CONTAS, linhasContasCSV);
			System.out.println("Conta criada com sucesso!");
		} catch (IOException | CsvException | ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Conta recuperar(int id) {
		// TODO Auto-generated method stub
		return null;
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
		 return contasMap.isEmpty() ? 1 : contasMap.values().stream()
	                .mapToInt(Conta::getId)
	                .max()
	                .orElse(0) + 1;
	}
	
	private static void inicializarCacheDataset() throws IOException, CsvException, ParseException {
		 linhasContasCSV = DatasetUtils.lerLinhasCSV(NOME_ARQUIVO_CONTAS);
		 
		 if (linhasContasCSV == null || linhasContasCSV.isEmpty()) {
	            System.out.println("Nenhum dado encontrado no arquivo: " + NOME_ARQUIVO_CONTAS);
	            return;
	        }

	        for (int i = 1; i < linhasContasCSV.size(); i++) {
	            String[] dados = linhasContasCSV.get(i);
	            Conta conta = new Conta();
	            conta.build(dados);

	            if (conta.getNumeroConta() > 0) {
	            	conta.setCliente(UsuarioDataset.recuperarPessoa(conta.getCliente().getId()));
	            	conta.setAgencia(AgenciaDataset.recuperar(conta.getAgencia().getId()));
	            	contasMap.put(conta.getNumeroConta(), conta);
	            }
	        }
	 }
	
	public static List<Conta> recuperarContasPorCpf(String cpf) {
		try {
			inicializarCacheDataset();
		} catch (IOException | CsvException | ParseException e) {
			e.printStackTrace();
		}
		
		if(!contasMap.isEmpty())
			return contasMap.values().stream()
		        .filter(conta -> conta.getCliente().getCpf().equals(cpf)).toList();
		
		return new ArrayList<Conta>();
	}
	
	private int gerarNumeroConta() {
		return (int)(Math.random() * Integer.MAX_VALUE);
	}

}
