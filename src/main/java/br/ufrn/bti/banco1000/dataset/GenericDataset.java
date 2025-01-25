package br.ufrn.bti.banco1000.dataset;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public interface GenericDataset<T> {
	public void criar(T obj) throws Exception;
	public T recuperar(int id);
	public T atualizar(int id);
	public void excluir(int id);
	public int gerarProximoId();
}