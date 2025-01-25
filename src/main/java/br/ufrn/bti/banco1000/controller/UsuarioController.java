package br.ufrn.bti.banco1000.controller;

import br.ufrn.bti.banco1000.constantes.Comandos;
import br.ufrn.bti.banco1000.dataset.ContaDataset;
import br.ufrn.bti.banco1000.dataset.UsuarioDataset;
import br.ufrn.bti.banco1000.enums.TipoUsuarioEnum;
import br.ufrn.bti.banco1000.exceptions.CustomException;
import br.ufrn.bti.banco1000.exceptions.NegocioException;
import br.ufrn.bti.banco1000.exceptions.SegurancaException;
import br.ufrn.bti.banco1000.model.Usuario;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class UsuarioController extends GenericController<Usuario> {
	
	UsuarioDataset usuarioDataset = new UsuarioDataset();
	
	@Override
	public void processarByOpcao(int opcao, Usuario obj) throws CustomException, SegurancaException, NegocioException {
		switch(opcao) {
			case(Comandos.ENTRAR_SISTEMA):
				entrar(obj.getLogin(), obj.getSenha());
				if(hasUsuarioAutenticado())
					redirectViewLogadoByOpcao(Comandos.TELA_USUARIO_LOGADO);
				break;
			case(Comandos.CADASTRAR_USUARIO):
				cadastrar(obj);
				break;
			case(Comandos.ENCERRAR_SESSAO_USUARIO):
				sair();
				break;
		}
	}
	
	private void entrar(String login, String senha) throws CustomException, SegurancaException {
		Usuario usuario = usuarioDataset.recuperarUsuarioPorCpf(login);
		
		if(usuario != null && usuario.getSenha().equals(senha)) {
			setUsuarioAutenticado(usuario);
			System.out.println("Autenticação realizada com sucesso!\n");
			return;
		} 
		
		throw new SegurancaException("Login/Senha não conferem. Tente novamente!\n");
	}
	
	private void cadastrar(Usuario obj) throws CustomException, NegocioException {
		obj.setLogin(obj.getPessoa().getCpf());
		obj.setTipoUsuario(TipoUsuarioEnum.Cliente.tipoUsuario);
		obj.validate();
		usuarioDataset.criar(obj);	
	}
	
	private void sair() throws CustomException, SegurancaException {
		setUsuarioAutenticado(null);
		System.out.println("Usuário deslogado com sucesso!\n");
	}
	
	public void refreshContasDoUsuario() throws SegurancaException {
		getUsuarioAutenticado().setContas(ContaDataset.recuperarContasPorCpf(getUsuarioAutenticado().getPessoa().getCpf()));
	}
}