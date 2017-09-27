package visao;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelo.Evento;
import modelo.Ingresso;
import controle.IngressoControle;

@ManagedBean(name="IngressoBean")
@SessionScoped
public class IngressoBean {
	
	private Ingresso ingresso = new Ingresso();
	private Ingresso ingressoSelecionado;
	private IngressoControle ingressoControle = new IngressoControle();
	private List<Ingresso> listIngresso = new ArrayList<Ingresso>();
	private List<Evento> listEvento;
	private String confirmaSenha;
	
	
	FacesContext context = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
	
	
	
	
	public String salvar() {
		FacesContext faces = FacesContext.getCurrentInstance();
		if (!loginBean.getSenha().equals(confirmaSenha)) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas não conferem!", ""));
			return "";
		}
		try {
			ingressoControle.salvar(ingresso);
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso!", ""));
			ingresso = new Ingresso();
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		return "index";
	}
	
	public String efetuarCompra() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

		if (loginBean.isLogado() == true) {
			return "ingresso";
		}else{		
			return "login";
		}
	}
	
	public String comprar() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			ingressoControle.salvar(ingresso);
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso!", ""));
			ingresso = new Ingresso();
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		return "listaIngresso";
	}
	
	public String buscarTodos() {
		listIngresso = ingressoControle.buscarTodos();
		return "/admin/listarIngresso";
	}
	
	public String cadastroIngresso() {
		if (!(ingresso == null)) {
			ingresso = new Ingresso();
			return "/admin/cadastroIngresso";
		}
		return "/admin/cadastroIngresso";
	}
	
	public String alterar() {
		FacesContext faces = FacesContext.getCurrentInstance();
		if (ingressoSelecionado == null) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Você deve selecionar um item para alterar", ""));
			return "";
		}
		this.ingresso = this.ingressoSelecionado;
		return "/admin/cadastroIngresso";
	}
	
	public String excluir() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			ingressoControle.excluir(ingressoSelecionado);
			listIngresso.remove(ingressoSelecionado);
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingresso excluido com sucesso!", ""));
			return "";
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		return "";
	}
	
	public String voltar() {
		ingresso = new Ingresso();
		return "/admin/indexADM";
	}
	
	public String buscarPorId() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			ingresso = ingressoControle.buscarPorId(ingresso.getIdIngresso());
			return "cadastroIngresso";
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		
		return "";
	}
		
	
	
	
	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public Ingresso getIngresso() {
		return ingresso;
	}
	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}
	public Ingresso getIngressoSelecionado() {
		return ingressoSelecionado;
	}
	public void setIngressoSelecionado(Ingresso ingressoSelecionado) {
		this.ingressoSelecionado = ingressoSelecionado;
	}
	public IngressoControle getIngressoControle() {
		return ingressoControle;
	}
	public void setIngressoControle(IngressoControle ingressoControle) {
		this.ingressoControle = ingressoControle;
	}
	public List<Ingresso> getListIngresso() {
		return listIngresso;
	}
	public void setListIngresso(List<Ingresso> listIngresso) {
		listEvento = ingressoControle.buscarTodosEventos();
		this.listIngresso = listIngresso;
	}
	public List<Evento> getListEvento() {
		listEvento = ingressoControle.buscarTodosEventos();
		return listEvento;
	}
	public void setListEvento(List<Evento> listEvento) {
		this.listEvento = listEvento;
	}
	
	

}
