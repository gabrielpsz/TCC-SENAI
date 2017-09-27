package dao;

import java.util.List;

import modelo.Ingresso;

public interface IngressoDAO extends DAO<Ingresso>{

	Ingresso buscarPorId(long idIngresso);
	List<Ingresso> buscarPorCodigo(String codigo);
	List<Ingresso> buscarTodos();
}
