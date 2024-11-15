package br.ufrn.bti.banco1000.controller;

import br.ufrn.bti.banco1000.constantes.Constantes;
import br.ufrn.bti.banco1000.custom.exceptions.CustomException;
import br.ufrn.bti.banco1000.custom.exceptions.SegurancaException;
import br.ufrn.bti.banco1000.dataset.UsuarioDataset;
import br.ufrn.bti.banco1000.enums.TipoUsuarioEnum;
import br.ufrn.bti.banco1000.model.Usuario;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class UsuarioController extends GenericController<Usuario> {
	private UsuarioDataset usuarioDataset = new UsuarioDataset();
	
	@Override
	public void processarByOpcao(int opcao, Usuario obj) throws CustomException, SegurancaException {
		clearMensagens();
		
		switch(opcao) {
			case Constantes.ENTRAR_SISTEMA:
				try {					
					login(obj.getLogin(), obj.getSenha());
				} catch (SegurancaException e) {
					erros.add(e.getMessage());
					redirectViewByOpcao(opcao);
				}
				break;
			case Constantes.CADASTRAR_USUARIO:
				criarUsuario(obj);
				break;
			case Constantes.ENCERRAR_SESSAO_USUARIO:
				logout();
				break;
		}
	}
	
	private void criarUsuario(Usuario usuario) throws CustomException, SegurancaException {		
		try {
			usuario.setLogin(usuario.getPessoaCliente().getCpf());
			usuario.setTipoUsuario(TipoUsuarioEnum.Cliente.tipo);

			usuarioDataset.criar(usuario);
			sucessos.add("Cadastro realizado com sucesso!\n");
			
			redirectViewByOpcao(Constantes.TELA_INICIAL);
		} catch (CustomException e) {
			erros.add(e.getMessage());
			
			redirectViewByOpcao(Constantes.CADASTRAR_USUARIO);
		}
	}
	
	private void login(String loginCPF, String senha) throws SegurancaException, CustomException {
		Usuario usuario = UsuarioDataset.recuperarUsuarioPorCpf(loginCPF);
		
		if(usuario != null && usuario.getSenha().equals(senha)) {
			setUsuarioAutenticado(usuario);
			sucessos.add("Autenticação realizada com sucesso!\n");
			redirectViewByOpcao(Constantes.TELA_PRINCIPAL);
		} else {
			throw new SegurancaException("Login/Senha não conferem. Tente novamente!\n");
		}
	}
	
	private void logout() throws CustomException, SegurancaException {
		setUsuarioAutenticado(null);
		sucessos.add("Usuário deslogado com sucesso!\n");
		
		redirectViewByOpcao(Constantes.TELA_INICIAL);
	}
}
