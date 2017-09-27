package dao;

import java.util.List;

import modelo.Usuario;

public interface UsuarioDAO extends DAO<Usuario>{
	
	List<Usuario> buscarTodos();
	List<Usuario> buscarPorCPF(String cpf);
	Usuario buscarPorId(long idUsuario);
	Usuario autenticarUsuario(String login, String senha);

}
