package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import conexao.ConnectionManager;
import modelo.Evento;

public class EventoDaoImp extends GenericHibernateDao<Evento> implements EventoDAO{

private EntityManager manager;
	
	public EventoDaoImp() {
		manager = ConnectionManager.getEntityManager();
	}
	
	@Override
	public List<Evento> buscarTodos() {
	Query query = manager.createNamedQuery("Evento.buscarTodos");	
		return query.getResultList();
	}

	@Override
	public List<Evento> buscarPorNome(String nome) {
	Query query = manager.createNamedQuery("Evento.buscarPorNome");	
	query.setParameter("nome", "%"+nome+"%");
	return query.getResultList();
	}

	@Override
	public Evento buscarPorId(long idEvento) {
		return manager.find(Evento.class, idEvento);
	}

}
