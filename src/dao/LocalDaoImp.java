package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import conexao.ConnectionManager;
import modelo.Local;
import modelo.Modalidade;

public class LocalDaoImp extends GenericHibernateDao<Local> implements LocalDAO{

	private EntityManager manager;
	
	public LocalDaoImp() {
		manager = ConnectionManager.getEntityManager();
	}
	
	@Override
	public List<Local> buscarTodos() {
		Query query = manager.createNamedQuery("Local.buscarTodos");
		return query.getResultList();
	}

	@Override
	public Local buscarPorId(long idLocal) {
		return manager.find(Local.class, idLocal);
	}

	@Override
	public List<Local> buscarPorCidade(String cidade) {
		Query query = manager.createNamedQuery("Local.buscarPorCidade");
		query.setParameter("cidade", "%"+cidade+"%");
		return query.getResultList();
	}

	@Override
	public List<Local> buscarPorCep(String cep) {
		Query query = manager.createNamedQuery("Local.buscarPorCep");
		query.setParameter("cep", cep);
		return query.getResultList();
	}

}
