package dao;


public abstract class DAOFactory {

	private static UsuarioDAO usuarioDao;

	public static UsuarioDAO getUsuarioDao() {
		if (usuarioDao == null) {
			usuarioDao = new UsuarioDaoImp();
		}
		return usuarioDao;
	}
	
	private static LocalDAO localDao;
	public static LocalDAO getLocalDao() {
		if (localDao == null) {
			localDao = new LocalDaoImp();
		}
		return localDao;
	}
	
	private static ModalidadeDAO modalidadeDao;
	public static ModalidadeDAO getModalidadeDao() {
		if (modalidadeDao == null) {
			modalidadeDao = new ModalidadeDaoImp();
		}
		return modalidadeDao;
	}
	
	private static EventoDAO eventoDao;
	public static EventoDAO getEventoDao() {
		if (eventoDao == null) {
			eventoDao = new EventoDaoImp();
		}
		return eventoDao;
	}
	
	private static IngressoDAO ingressoDao;
	public static IngressoDAO getIngressoDao() {
		if (ingressoDao == null) {
			ingressoDao = new IngressoDaoImp();
		}
		return ingressoDao;
	}
}
