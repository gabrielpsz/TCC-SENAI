package dao;

import java.util.List;

import modelo.Modalidade;

public interface ModalidadeDAO extends DAO<Modalidade>{

	List<Modalidade> buscarTodos();
	Modalidade buscarPorId(long idModalidade);
	List<Modalidade> buscarPorNome(String nome);
	List<Modalidade> buscarPorTipo(String tipo);
}
