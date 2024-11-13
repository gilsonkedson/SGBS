package br.ufrn.bti.banco1000.dataset;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.ufrn.bti.banco1000.model.Cliente;
import br.ufrn.bti.banco1000.model.Usuario;

public class UsuarioDataset {
	private static Map<String, Usuario> usuarios = new HashMap<String, Usuario>();
	
	// Mock de dados inicial
	static {
		Usuario usuario = new Usuario();
		Cliente cliente = new Cliente("Gilson Kedson", "1111", "kkkk", "kkkk");
		
		usuario.setId(1);
		usuario.setLogin("1111");
		usuario.setSenha("123456");
		usuario.setPessoaCliente(cliente);
		
		usuarios.put(usuario.getLogin(), usuario);
	}
	
	public static void criarUsuario(Usuario usuario) throws Exception {
		if(!usuarios.containsKey(usuario.getLogin())) {
			usuario.setId(usuarios.size()+1);
			usuario.setDataCadastro(new Date());
			usuarios.put(usuario.getLogin(), usuario);
			return;
		}
		
		throw new Exception("Já existe usuário com o CPF informado. Tente novamente!\n");
	}
	
	public static Usuario getUsuarioPorCpf(String cpf) {
		return usuarios.get(cpf);
	}
}
