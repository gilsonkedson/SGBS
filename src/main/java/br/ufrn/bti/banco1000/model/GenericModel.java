package br.ufrn.bti.banco1000.model;

import java.util.Date;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class GenericModel {
	protected int id;
	protected Date dataCadastro;
	protected Date dataModificacao;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Date getDataModificacao() {
		return dataModificacao;
	}
	
	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
}
