package dao;

import java.util.List;

import modelo.Evento;

public interface EventoDAO extends DAO<Evento>{

	Evento buscarPorId(long idEvento);
	List<Evento> buscarTodos();
	List<Evento> buscarPorNome(String nome);
}
