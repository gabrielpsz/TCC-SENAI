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

import org.hibernate.exception.ConstraintViolationException;

import controle.ModalidadeControle;
import modelo.Modalidade;

@ManagedBean(name="ModalidadeBean")
@SessionScoped
public class ModalidadeBean {

	private Modalidade modalidade = new Modalidade();
	private Modalidade modalidadeSelecionada = new Modalidade();
	private List<Modalidade> listModalidade = new ArrayList<Modalidade>();
	private ModalidadeControle modalidadeControle = new ModalidadeControle();
	
	public String salvar() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			modalidadeControle.salvar(modalidade);
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso!", ""));
			modalidade = new Modalidade();
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
			return "";
		}	
		return "/admin/cadastroModalidade";
	}
	
	public String buscarPorId() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			modalidade = modalidadeControle.buscarPorId(modalidade.getIdModalidade());
			return "cadastroModalidade";
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String cadastroModalidade() {
		if (!(modalidade == null)) {
			modalidade = new Modalidade();
			return "/admin/cadastroModalidade";
		}
		return "/admin/cadastroModalidade";
	}
	
	public String buscarTodos() {
		listModalidade = modalidadeControle.buscarTodos();
		return "/admin/listarModalidade";
	}
	
	public String alterar() {
		FacesContext faces = FacesContext.getCurrentInstance();
		if (modalidadeSelecionada == null) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Você deve selecionar um item para alterar", ""));
			return "";
		}
		this.modalidade = this.modalidadeSelecionada;
		return "/admin/cadastroModalidade";
	}
	
	public String excluir() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			modalidadeControle.excluir(modalidadeSelecionada);
			listModalidade.remove(modalidadeSelecionada);
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modalidade excluida com sucesso!", ""));
			return "";
		} catch (ConstraintViolationException cve) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível excluir uma modalidade. A modalidade está vinculado a um evento", ""));
			return "";
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		return "";
	}	
	public String buscarPorTipo() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			this.listModalidade = modalidadeControle.buscarPorTipo(this.modalidade.getTipo());
		} catch (Exception e) {
			e.printStackTrace();
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			return "";
		}
		return "/admin/listarModalidade";
		
		
	}
	
	public String voltar() {
		modalidade = new Modalidade();
		return "/admin/indexADM";
	}

	
	public void getGerarRelatorio() {
		try {
			File file = new File(modalidadeControle.gerarRelatorio());
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
	
	public Modalidade getModalidade() {
		return modalidade;
	}
	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}
	public Modalidade getModalidadeSelecionada() {
		return modalidadeSelecionada;
	}
	public void setModalidadeSelecionada(Modalidade modalidadeSelecionada) {
		this.modalidadeSelecionada = modalidadeSelecionada;
	}
	public List<Modalidade> getListModalidade() {
		return listModalidade;
	}
	public void setListModalidade(List<Modalidade> listModalidade) {
		this.listModalidade = listModalidade;
	}
	public ModalidadeControle getModalidadeControle() {
		return modalidadeControle;
	}
	public void setModalidadeControle(ModalidadeControle modalidadeControle) {
		this.modalidadeControle = modalidadeControle;
	}
}
