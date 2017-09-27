package dao;

import conexao.ConnectionManager;
import dao.DAO;

public class GenericHibernateDao<E> implements DAO<E> {
	
	@Override
	public void salvar(E e) {
		ConnectionManager.getEntityManager().getTransaction().begin();
		ConnectionManager.getEntityManager().persist(e);
		ConnectionManager.getEntityManager().getTransaction().commit();
	}

	@Override
	public void update() {
		ConnectionManager.getEntityManager().getTransaction().begin();
		ConnectionManager.getEntityManager().getTransaction().commit();
	}

	@Override
	public void excluir(E e) {
		ConnectionManager.getEntityManager().getTransaction().begin();
		ConnectionManager.getEntityManager().remove(e);
		ConnectionManager.getEntityManager().getTransaction().commit();
	}

}
