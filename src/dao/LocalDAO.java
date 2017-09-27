package dao;

import java.util.List;

import modelo.Local;

public interface LocalDAO extends DAO<Local>{
	
		Local buscarPorId(long idLocal);
		List<Local> buscarTodos();
		List<Local> buscarPorCidade(String cidade);
		List<Local> buscarPorCep(String cep);
}
