package br.ufrn.bti.banco1000.model;

import java.util.Date;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public abstract class GenericModel {
	protected int id;
	protected Date dataCadastro;
	protected Date dataModificacao;
	protected Integer idUsuarioAcao;
	public abstract void validate() throws Exception;
	public abstract void build(String[] args) throws Exception;
	
	public GenericModel() {}
	
	public GenericModel(int id) {
		this.id = id;
	}

	public GenericModel(int id, Date dataCadastro) {
		this.id = id;
		this.dataCadastro = dataCadastro;
	}

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
	
	public int getIdUsuarioAcao() {
		return idUsuarioAcao;
	}
	
	public void setIdUsuarioAcao(int idUsuarioAcao) {
		this.idUsuarioAcao = idUsuarioAcao;
	}
}
