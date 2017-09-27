package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import conexao.ConnectionManager;
import modelo.Endereco;
import modelo.Usuario;

public class UsuarioDaoImp extends GenericHibernateDao<Usuario> implements UsuarioDAO{
	
private EntityManager manager;
	
	public UsuarioDaoImp() {
		manager = ConnectionManager.getEntityManager();
	}
	
	@Override
	public Usuario buscarPorId(long idUsuario) {
		return manager.find(Usuario.class, idUsuario);
	}

	@Override
	public List<Usuario> buscarTodos() {
		Query query = manager.createNamedQuery("Usuario.buscarTodos");
		return query.getResultList();
	}

	@Override
	public List<Usuario> buscarPorCPF(String cpf) {
		Query query = manager.createNamedQuery("Usuario.buscarPorCPF");
		query.setParameter("cpf", cpf);
		return query.getResultList();
	}

	@Override
	public Usuario autenticarUsuario(String login, String senha) {
		Query query = manager.createNamedQuery("Usuario.buscarPorEmailSenha");
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		return (Usuario) query.getSingleResult();
	}

}
