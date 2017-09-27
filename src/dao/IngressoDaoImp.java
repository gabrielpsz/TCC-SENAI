package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import conexao.ConnectionManager;
import modelo.Ingresso;

public class IngressoDaoImp extends GenericHibernateDao<Ingresso> implements IngressoDAO{

	private EntityManager manager;
	
	public IngressoDaoImp() {
		manager = ConnectionManager.getEntityManager();
	}

	@Override
	public Ingresso buscarPorId(long idIngresso) {
		return manager.find(Ingresso.class, idIngresso);
	}

	@Override
	public List<Ingresso> buscarPorCodigo(String codigo) {
		Query query = manager.createNamedQuery("Ingresso.buscarPorCodigo");
		query.setParameter("codigo", codigo);
		return query.getResultList();
	}

	@Override
	public List<Ingresso> buscarTodos() {
		Query query = manager.createNamedQuery("Ingresso.buscarTodos");
		return query.getResultList();
	}
	
}
