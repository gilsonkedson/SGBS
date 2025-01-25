package br.ufrn.bti.banco1000.model.conta;

import java.text.ParseException;
import java.util.List;

import br.ufrn.bti.banco1000.dataset.AgenciaMapper;
import br.ufrn.bti.banco1000.model.GenericModel;
import br.ufrn.bti.banco1000.utils.Utils;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class AgenciaConta extends GenericModel {
	private String nome;
	private Integer numeroIdentificador;
	private String endereco;
	private String responsavel;
	private double taxaManutencao;
	private double rendimentoMensal;
	private double totalSaques;
	private List<Conta> contas;
	
	public AgenciaConta() {
	}

	public AgenciaConta(Integer id, Integer numeroIdentificador) {
		super();
		this.id = id;
		this.numeroIdentificador = numeroIdentificador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumeroIdentificador() {
		return numeroIdentificador;
	}

	public void setNumeroIdentificador(Integer numeroIdentificador) {
		this.numeroIdentificador = numeroIdentificador;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public double getTaxaManutencao() {
		return taxaManutencao;
	}

	public void setTaxaManutencao(double taxaManutencao) {
		this.taxaManutencao = taxaManutencao;
	}

	public double getRendimentoMensal() {
		return rendimentoMensal;
	}

	public void setRendimentoMensal(double rendimentoMensal) {
		this.rendimentoMensal = rendimentoMensal;
	}

	public double getTotalSaques() {
		return totalSaques;
	}

	public void setTotalSaques(double totalSaques) {
		this.totalSaques = totalSaques;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	@Override
	public void validate() throws Exception {
		// TODO Auto-generated method stub	
	}

	@Override
	public void build(String[] args) throws ParseException {
		this.setId(Integer.parseInt(args[AgenciaMapper.ID]));
		this.setNumeroIdentificador(Integer.parseInt(args[AgenciaMapper.NUMERO_IDENTIFICADOR]));
		this.setNome(args[AgenciaMapper.NOME]);
		this.setEndereco(args[AgenciaMapper.ENDERECO]);
		this.setResponsavel(args[AgenciaMapper.NOME_RESPONSAVEL]);
		this.setTaxaManutencao(Double.parseDouble(args[AgenciaMapper.TAXA_MANUTENCAO].replace(",", ".")));
		this.setRendimentoMensal(Double.parseDouble(args[AgenciaMapper.RENDIMENTO_MENSAL].replace(",", ".")));
		this.setTotalSaques(Integer.parseInt(args[AgenciaMapper.TOTAL_SAQUES]));
		this.setDataCadastro(Utils.getDataFormatada(args[AgenciaMapper.DATA_CADASTRO]));
	}
	
	@Override
	public String toString() {
		return "%d - %s".formatted(numeroIdentificador, nome);
	}
}
