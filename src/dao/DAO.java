package dao;

public interface DAO<E> {

	void salvar(E e);
	void update();
	void excluir(E e);
	
}
