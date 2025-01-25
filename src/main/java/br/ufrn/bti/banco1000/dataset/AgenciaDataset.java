package br.ufrn.bti.banco1000.dataset;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.exceptions.CsvException;

import br.ufrn.bti.banco1000.model.conta.AgenciaConta;

public class AgenciaDataset {
	 private static final Map<Integer, AgenciaConta> agenciasMap = new HashMap<>();
	 private static final String NOME_ARQUIVO_AGENCIAS= "agencia.csv";
	 
	 public Collection<AgenciaConta> recuperarAgencias(){
		 if(agenciasMap.isEmpty()) {
			 try {
				inicializarCacheDataset();
			} catch (IOException | CsvException | ParseException e) {
				e.printStackTrace();
			}
		 }
		 
		 return agenciasMap.values();
	 }
	 
	 public static AgenciaConta recuperar(int id){
		 if(agenciasMap.isEmpty()) {
			 try {
				inicializarCacheDataset();
			} catch (IOException | CsvException | ParseException e) {
				e.printStackTrace();
			}
		 }
		 
		 return agenciasMap.values().stream()
	                .filter(agencia -> agencia.getId() == id)
	                .findFirst()
	                .orElse(null);
	 }
	 
	 public static AgenciaConta recuperarPorNumeroIdentificador(int numeroIdentificador){
		 if(agenciasMap.isEmpty()) {
			 try {
				inicializarCacheDataset();
			} catch (IOException | CsvException | ParseException e) {
				e.printStackTrace();
			}
		 }
		 
		 return agenciasMap.get(numeroIdentificador);
	 }
	 
	 private static void inicializarCacheDataset() throws IOException, CsvException, ParseException {
		 List<String[]> linhasAgenciasCSV = DatasetUtils.lerLinhasCSV(NOME_ARQUIVO_AGENCIAS);
		 
		 if (linhasAgenciasCSV == null || linhasAgenciasCSV.isEmpty()) {
	            System.out.println("Nenhum dado encontrado no arquivo: " + NOME_ARQUIVO_AGENCIAS);
	            return;
	        }

	        for (int i = 1; i < linhasAgenciasCSV.size(); i++) {
	            String[] dados = linhasAgenciasCSV.get(i);
	            AgenciaConta agencia = new AgenciaConta();
	            agencia.build(dados);

	            if (agencia.getNumeroIdentificador() > 0) {
	            	agenciasMap.put(agencia.getNumeroIdentificador(), agencia);
	            }
	        }
	 }
}
