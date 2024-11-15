package br.ufrn.bti.banco1000.dataset;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.ufrn.bti.banco1000.custom.exceptions.CustomException;
import br.ufrn.bti.banco1000.model.Cliente;
import br.ufrn.bti.banco1000.model.Usuario;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class UsuarioDataset implements GenericDataset<Usuario> {
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
	
	@Override
	public void criar(Usuario usuario) throws CustomException {
		if(!usuarios.containsKey(usuario.getLogin())) {
			usuario.setId(gerarProximoId());
			usuario.setDataCadastro(new Date());
			usuarios.put(usuario.getLogin(), usuario);
			return;
		}
		
		throw new CustomException("Já existe usuário com o CPF informado. Tente novamente!\n");
	}

	@Override
	public Usuario recuperar(int id) {
		return usuarios.values().stream()
				        .filter(usuario -> usuario.getId() == id)
				        .findFirst()
				        .orElse(null);
	}

	@Override
	public Usuario atualizar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public int gerarProximoId() {
	    return usuarios.isEmpty() ? 1 : Collections.max(usuarios.values(), Comparator.comparingInt(Usuario::getId)).getId() + 1;
	}
	
	public static Usuario recuperarUsuarioPorCpf(String cpf) {
		return usuarios.get(cpf);
	}
}
