package controle;


import java.util.List;


import modelo.Evento;
import modelo.Ingresso;
import dao.DAOFactory;
import dao.EventoDAO;
import dao.IngressoDAO;

public class IngressoControle {

	IngressoDAO ingressoDao = DAOFactory.getIngressoDao();
	EventoDAO eventoDao = DAOFactory.getEventoDao();

	public void salvar(Ingresso ingresso) throws Exception {
		
		
		if (ingresso.getEvento() == null || ingresso.getEvento().trim().isEmpty()) {
			throw new Exception("O evento é obrigatório");
		}
		if (ingresso.getIdComprador() == 0) {
			throw new Exception("É necessário um comprador para concluir a compra");
		}
		if (ingresso.getEmailComprador() == null || ingresso.getEmailComprador().trim().isEmpty()) {
			throw new Exception("É necessário o Email do comprador para concluir a compra");
		}
		if (ingresso.getIdIngresso() == 0) {
			ingressoDao.salvar(ingresso);
		} else {
			ingressoDao.update();
		}
	}

	public List<Ingresso> buscarTodos() {
		return ingressoDao.buscarTodos();
	}

	public void excluir(Ingresso ingresso) throws Exception {
		if (ingresso == null) {
			throw new Exception("Deve-se selecionar um ingresso para excluir");
		}
		ingressoDao.excluir(ingresso);
	}

	public List<Ingresso> buscarPorCodigo(String codigo) throws Exception {
		if (codigo == null || codigo.trim().isEmpty()) {
			throw new Exception(
					"O codigo do ingresso é necessário para a consulta");
		}
		return ingressoDao.buscarPorCodigo(codigo);
	}

	public Ingresso buscarPorId(long idIngresso) throws Exception {
		if (idIngresso <= 0) {
			throw new Exception("O id deve ser maior que 0");
		}
		return ingressoDao.buscarPorId(idIngresso);
	}

	public Evento buscarEventoPorId(long idEvento) {
		return eventoDao.buscarPorId(idEvento);
	}

	public List<Evento> buscarTodosEventos() {
		return eventoDao.buscarTodos();
	}

}
