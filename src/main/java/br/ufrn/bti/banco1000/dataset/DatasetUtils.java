package br.ufrn.bti.banco1000.dataset;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class DatasetUtils {

    private static final String BASE_PATH = "/br/ufrn/bti/banco1000/dataset/";
    
    private static Path obterCaminhoArquivo(String nomeArquivo) {
        try {
            URL resource = DatasetUtils.class.getResource(BASE_PATH + nomeArquivo);

            if (resource == null) {
                return null;
            }

            return Paths.get(resource.toURI());
        } catch (Exception e) {
            System.err.println("Erro ao obter o caminho do arquivo: " + e.getMessage());
        }
        return null;
    }

    public static List<String[]> lerLinhasCSV(String nomeArquivo) throws IOException, CsvException {
        Path path = obterCaminhoArquivo(nomeArquivo);

        if (path == null) {
            System.out.println("Arquivo não encontrado: " + nomeArquivo);
            return null;
        }

        try (Reader reader = Files.newBufferedReader(path);
        	CSVReader csvReader = new CSVReader(reader)) {
            return csvReader.readAll();
        }
    }

    public static List<String[]> escreverLinhasCSV(String nomeArquivo, List<String[]> dados) throws IOException, CsvException {
        Path path = obterCaminhoArquivo(nomeArquivo);

        if (path == null) {
            System.out.println("Arquivo não encontrado ou caminho inválido: " + nomeArquivo);
            return null;
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(path.toString()))) {
            for (String[] line : dados) {
                writer.writeNext(line);
            }
        }
        
        return lerLinhasCSV(nomeArquivo);
    }
}
