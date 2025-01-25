package br.ufrn.bti.banco1000.dataset;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.exceptions.CsvException;

import br.ufrn.bti.banco1000.exceptions.CustomException;
import br.ufrn.bti.banco1000.model.Pessoa;
import br.ufrn.bti.banco1000.model.Usuario;
import br.ufrn.bti.banco1000.utils.Utils;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class UsuarioDataset implements GenericDataset<Usuario> {

    private static final Map<String, Usuario> usuariosMap = new HashMap<>();
    public static final Map<Integer, Pessoa> pessoasMap = new HashMap<>();
    private static List<String[]> linhasPessoasCSV = null;
    private static List<String[]> linhasUsuariosCSV = null;
    private static final String NOME_ARQUIVO_USUARIO = "usuario.csv";
    private static final String NOME_ARQUIVO_PESSOA = "pessoa.csv";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    static {
        DATE_FORMAT.setLenient(false);
    }

    @Override
    public void criar(Usuario usuario) throws CustomException {
        verificarCache();
        validarUsuarioExistente(usuario.getLogin());
        atribuirIdsEDatas(usuario);
        adicionarNosMapas(usuario);
        
        try {
            atualizarCSV(usuario);
        } catch (IOException | CsvException | ParseException e) {
            throw new CustomException("Erro ao criar o usuário: " + e.getMessage());
        }
    }

    @Override
    public Usuario recuperar(int id) {
        try {
			verificarCache();
		} catch (CustomException e) {
			e.printStackTrace();
		}
        
        Usuario usuario = usuariosMap.values().stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
        
        if(usuario != null)
        	usuario.setContas(ContaDataset.recuperarContasPorCpf(usuario.getPessoa().getCpf()));
        
        return usuario;
    }
    
    public static Pessoa recuperarPessoa(int id) {
        try {
			verificarCache();
		} catch (CustomException e) {
			e.printStackTrace();
		}
        
        return pessoasMap.values().stream()
                .filter(pessoa -> pessoa.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Usuario atualizar(int id) {
        return null;
    }

    @Override
    public void excluir(int id) {}

    @Override
    public int gerarProximoId() {
        return usuariosMap.isEmpty() ? 1 : usuariosMap.values().stream()
                .mapToInt(Usuario::getId)
                .max()
                .orElse(0) + 1;
    }

    public int gerarProximoIdPessoa() {
        return pessoasMap.isEmpty() ? 1 : pessoasMap.values().stream()
                .mapToInt(Pessoa::getId)
                .max()
                .orElse(0) + 1;
    }

    public Usuario recuperarUsuarioPorCpf(String login) throws CustomException {
        verificarCache();
        
        Usuario usuario = usuariosMap.get(login);
        if(usuario != null)
        	usuario.setContas(ContaDataset.recuperarContasPorCpf(usuario.getPessoa().getCpf()));
        
        return usuario;
    }

    private static void inicializarCacheDataset() throws IOException, CsvException, CustomException {
        try {
            inicializarPessoas();
            inicializarUsuarios();
        } catch (IOException | CsvException | ParseException e) {
            throw new CustomException("Erro ao inicializar o dataset" + e.getMessage());
        }
    }

    private static void inicializarPessoas() throws IOException, CsvException, ParseException {
        linhasPessoasCSV = DatasetUtils.lerLinhasCSV(NOME_ARQUIVO_PESSOA);
        if (linhasPessoasCSV == null || linhasPessoasCSV.isEmpty()) {
            System.out.println("Nenhum dado encontrado no arquivo: " + NOME_ARQUIVO_PESSOA);
            return;
        }

        for (int i = 1; i < linhasPessoasCSV.size(); i++) {
            String[] dados = linhasPessoasCSV.get(i);
            Pessoa pessoa = new Pessoa();
            pessoa.build(dados);

            if (pessoa.getId() > 0) {
                pessoasMap.put(pessoa.getId(), pessoa);
            }
        }
    }

    private static void inicializarUsuarios() throws IOException, CsvException, ParseException {
        linhasUsuariosCSV = DatasetUtils.lerLinhasCSV(NOME_ARQUIVO_USUARIO);
        if (linhasUsuariosCSV == null || linhasUsuariosCSV.isEmpty()) {
            System.out.println("Nenhum dado encontrado no arquivo: " + NOME_ARQUIVO_USUARIO);
            return;
        }

        for (int i = 1; i < linhasUsuariosCSV.size(); i++) {
            String[] dados = linhasUsuariosCSV.get(i);
            Usuario usuario = new Usuario();
            usuario.build(dados);

            if (usuario != null) {
                usuariosMap.put(usuario.getLogin(), usuario);
            }
        }
    }

    private static void verificarCache() throws CustomException {
        if (usuariosMap.isEmpty()) {
            try {
                inicializarCacheDataset();
            } catch (IOException | CsvException e) {
                throw new CustomException("Erro ao inicializar cache" + e.getMessage());
            }
        }
    }

    private void validarUsuarioExistente(String login) throws CustomException {
        if (usuariosMap.containsKey(login)) {
            throw new CustomException("Já existe um usuário com o login informado. Tente novamente!\n");
        }
    }

    private void atribuirIdsEDatas(Usuario usuario) {
        usuario.setId(gerarProximoId());
        usuario.getPessoa().setId(gerarProximoIdPessoa());
        usuario.getPessoa().setDataCadastro(new Date());
        usuario.setDataCadastro(new Date());
    }

    private void adicionarNosMapas(Usuario usuario) {
        pessoasMap.put(usuario.getPessoa().getId(), usuario.getPessoa());
        usuariosMap.put(usuario.getLogin(), usuario);
    }

    private void atualizarCSV(Usuario usuario) throws ParseException, IOException, CsvException {
        atualizarPessoasCSV(usuario);
        atualizarUsuariosCSV(usuario);
    }

    private void atualizarPessoasCSV(Usuario usuario) throws IOException, CsvException, ParseException {
        linhasPessoasCSV.add(new String[] {
            String.valueOf(usuario.getPessoa().getId()), 
            usuario.getPessoa().getNome(),
            usuario.getPessoa().getEmail(),
            usuario.getPessoa().getNumeroCelular(), 
            usuario.getPessoa().getCpf(), 
            Utils.getDataFormatadaStr(usuario.getPessoa().getDataNascimento()),
            Utils.getDataFormatadaStr(usuario.getPessoa().getDataCadastro())
        });
        linhasPessoasCSV = DatasetUtils.escreverLinhasCSV(NOME_ARQUIVO_PESSOA, linhasPessoasCSV);
    }

    private void atualizarUsuariosCSV(Usuario usuario) throws IOException, CsvException, ParseException {
        linhasUsuariosCSV.add(new String[] {
            String.valueOf(usuario.getId()), 
            usuario.getLogin(), 
            usuario.getSenha(),
            String.valueOf(usuario.getPessoa().getId()), 
            String.valueOf(usuario.getTipoUsuario()), 
            Utils.getDataFormatadaStr(usuario.getDataCadastro())
        });
        linhasUsuariosCSV = DatasetUtils.escreverLinhasCSV(NOME_ARQUIVO_USUARIO, linhasUsuariosCSV);
        System.out.println("Usuário criado com sucesso: " + usuario.getLogin() + "\n");
    }
}
