package br.ufrn.bti.banco1000.controller;

import br.ufrn.bti.banco1000.constantes.Constantes;
import br.ufrn.bti.banco1000.dataset.UsuarioDataset;
import br.ufrn.bti.banco1000.enums.TipoUsuarioEnum;
import br.ufrn.bti.banco1000.model.Cliente;
import br.ufrn.bti.banco1000.model.Usuario;
import br.ufrn.bti.banco1000.view.BancoView;

public class BancoController {
	private Usuario usuarioAutenticado;

	public void processarOpcaoEscolhidaMenuInicial(int opcao) throws Exception {
		if(!Constantes.OPCOES_TELA_INICIAL.containsKey(opcao)) {
			throw new Exception("Opção inválida. Tente novamente.\n");
		}
		
		switch(opcao) {
			case Constantes.ENTRAR_SISTEMA:
				BancoView.resetCredenciais();
				BancoView.setTelaInicial(false);
				BancoView.telaEntrarSistema();
				break;
			case Constantes.CADASTRAR_USUARIO:	
				BancoView.setTelaInicial(false);
				BancoView.telaCadastroUsuario();
				break;
			default:
				BancoView.encerraExecucao();
				break;
		}
	}
	
	public void login(String loginCPF, String senha) {
		Usuario usuario = UsuarioDataset.getUsuarioPorCpf(loginCPF);
		
		if(usuario != null && usuario.getSenha().equals(senha)) {
			usuarioAutenticado = usuario;
			BancoView.telaInicial();
		} else {
			System.out.println("Login/Senha não conferem. Tente novamente!\n");
			BancoView.telaEntrarSistema();
		}
	}
	
	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}
	
	public boolean hasUsuarioAutenticado() {
		return usuarioAutenticado.getId() > 0;
	}
	
	public void criarUsuario(String nome, String cpf, String email, String telefone, String senha) {
		Cliente cliente = new Cliente(nome, cpf, email, telefone);
		Usuario usuario = new Usuario(cliente, senha, TipoUsuarioEnum.Cliente.tipo);
		
		try {
			UsuarioDataset.criarUsuario(usuario);
			BancoView.setOpcaoEmUtilizacao(Constantes.ENTRAR_SISTEMA);
			System.out.println("Cadastro efetuado com sucesso! Realize autenticação.\n");
			BancoView.telaEntrarSistema();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			BancoView.telaCadastroUsuario();
		}
	}
}
