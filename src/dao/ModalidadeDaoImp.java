package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import conexao.ConnectionManager;
import modelo.Modalidade;

public class ModalidadeDaoImp extends GenericHibernateDao<Modalidade> implements ModalidadeDAO{

	private EntityManager manager;
	
	public ModalidadeDaoImp() {
		manager = ConnectionManager.getEntityManager();
	}
	
	@Override
	public List<Modalidade> buscarTodos() {
		Query query = manager.createNamedQuery("Modalidade.buscarTodos");
		return query.getResultList();
	}

	@Override
	public Modalidade buscarPorId(long idModalidade) {
		return manager.find(Modalidade.class, idModalidade);
	}

	@Override
	public List<Modalidade> buscarPorNome(String nome) {
		Query query = manager.createNamedQuery("Modalidade.buscarPorNome");
		query.setParameter("nome", "%"+nome+"%");
		return query.getResultList();
	}

	@Override
	public List<Modalidade> buscarPorTipo(String tipo) {
		Query query = manager.createNamedQuery("Modalidade.buscarPorTipo");
		query.setParameter("tipo", "%"+tipo+"%");
		return query.getResultList();
	}

}
