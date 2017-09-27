package visao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import controle.EventoControle;
import modelo.Evento;
import modelo.Local;
import modelo.Modalidade;

@ManagedBean(name="EventoBean")
@SessionScoped
public class EventoBean {
	
	private Evento evento = new Evento();
	private Evento eventoSelecionado;
	private EventoControle eventoControle = new EventoControle();
	private List<Evento> listEvento = new ArrayList<Evento>();
	private List<Local> listLocal;
	private List<Modalidade> listModalidade;
	
	public List<Modalidade> getListModalidade() {
		listModalidade = eventoControle.buscarTodasModalidades();
		return listModalidade;
	}

	public void setListModalidade(List<Modalidade> listModalidade) {
		this.listModalidade = listModalidade;
	}

	public List<Local> getListLocal() {
		listLocal = eventoControle.buscarTodosLocais();
		return listLocal;
	}

	public void setListLocal(List<Local> listLocal) {
		this.listLocal = listLocal;
	}

	public String salvar() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			eventoControle.salvar(evento);
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso!", ""));
			evento = new Evento();
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		return "/admin/cadastroEvento";
	}
	
	public String buscarTodos() {
		listEvento = eventoControle.buscarTodos();
		return "/admin/listarEvento";
	}
	
	public String buscarTodosIndex() {
		listEvento = eventoControle.buscarTodos();
		return "/index";
	}
	
	public String cadastroEvento() {
		if (!(evento == null)) {
			evento = new Evento();
			return "/admin/cadastroEvento";
		}
		return "/admin/cadastroEvento";
	}
	
	public String alterar() {
		FacesContext faces = FacesContext.getCurrentInstance();
		if (eventoSelecionado == null) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Você deve selecionar um item para alterar", ""));
			return "";
		}
		this.evento = this.eventoSelecionado;
		return "/admin/cadastroEvento";
	}
	
	public String excluir() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			eventoControle.excluir(eventoSelecionado);
			listEvento.remove(eventoSelecionado);
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento excluido com sucesso!", ""));
			return "";
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		return "";
	}
	
	public String voltar() {
		evento = new Evento();
		return "/admin/indexADM";
	}
	
	public String buscarPorId() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			evento = eventoControle.buscarPorId(evento.getIdEvento());
			return "cadastroEvento";
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String buscarPorNome() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			this.listEvento = eventoControle.buscarPorNome(this.evento.getNome());
		} catch (Exception e) {
			e.printStackTrace();
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			return "";
		}
		return "/admin/listarEvento";
	}
	
	public void getGerarRelatorio() {
		try {
			File file = new File(eventoControle.gerarRelatorio());
			byte[] b = fileToByte(file);
			HttpServletResponse res = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			res.setContentType("application/pdf");
			res.setHeader("Content-Disposition", "attachment; filename=\\" + file.getName());
			res.getOutputStream().write(b);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static byte[] fileToByte(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int bytesRead = 0;
		while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
			baos.write(buffer, 0, bytesRead);
		}
		return baos.toByteArray();
	}
	
	
	
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public Evento getEventoSelecionado() {
		return eventoSelecionado;
	}
	public void setEventoSelecionado(Evento eventoSelecionado) {
		this.eventoSelecionado = eventoSelecionado;
	}
	public EventoControle getEventoControle() {
		return eventoControle;
	}
	public void setEventoControle(EventoControle eventoControle) {
		this.eventoControle = eventoControle;
	}
	public List<Evento> getListEvento() {
		return listEvento;
	}
	public void setListEvento(List<Evento> listEvento) {
		this.listEvento = listEvento;
	}
	public EventoBean(Evento evento, Evento eventoSelecionado,
			EventoControle eventoControle, List<Evento> listEvento) {
		super();
		this.evento = evento;
		this.eventoSelecionado = eventoSelecionado;
		this.eventoControle = eventoControle;
		this.listEvento = listEvento;
	}
	public EventoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
}