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

import modelo.Local;
import controle.LocalControle;

@ManagedBean(name="LocalBean")
@SessionScoped
public class LocalBean {

	private Local local = new Local();
	private List<Local> listLocal = new ArrayList<Local>();
	private LocalControle localControle = new LocalControle();
	private Local localSelecionado = new Local();
	
	public String salvar() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			localControle.salvar(local);
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso!", ""));
			local = new Local();
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
			return "";
		}	
		return "/admin/cadastroLocal";	
	}
	
	public String cadastroLocal() {
		if (!(local == null)) {
			local = new Local();
			return "/admin/cadastroLocal";
		}
		return "/admin/cadastroLocal";
	}
	
	public String buscarTodos() {
		listLocal = localControle.buscarTodos();
		return "/admin/listarLocal";
	}
	
	public String alterar() {
		FacesContext faces = FacesContext.getCurrentInstance();
		if (localSelecionado == null) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Você deve selecionar um item para alterar", ""));
			return "";
		}
		this.local = this.localSelecionado;
		return "/admin/cadastroLocal";
	}
	
	public String excluir() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			localControle.excluir(localSelecionado);
			listLocal.remove(localSelecionado);
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Local excluido com sucesso!", ""));
			return "";
		} catch (ConstraintViolationException cve) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível excluir um local. O local está vinculado a um evento", ""));
			return "";
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		return "";
	}
	
	public String buscarPorId() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			local = localControle.buscarPorId(local.getIdLocal());
			return "cadastroLocal";
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String buscarPorCidade() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			this.listLocal = localControle.buscarPorCidade(this.local.getCidade());
		} catch (Exception e) {
			e.printStackTrace();
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			return "";
		}
		return "/admin/listarLocal";
	}
	
	public String buscarPorCep() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			this.listLocal = localControle.buscarPorCep(this.local.getCep());
		} catch (Exception e) {
			e.printStackTrace();
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			return "";
		}
		return "/admin/listarLocal";
	}
	
	
	public String voltar() {
		local = new Local();
		return "/admin/indexADM";
	}
	public void getGerarRelatorio() {
		try {
			File file = new File(localControle.gerarRelatorio());
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
	
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public List<Local> getListLocal() {
		return listLocal;
	}
	public void setListLocal(List<Local> listLocal) {
		this.listLocal = listLocal;
	}
	public LocalControle getLocalControle() {
		return localControle;
	}
	public void setLocalControle(LocalControle localControle) {
		this.localControle = localControle;
	}
	public Local getLocalSelecionado() {
		return localSelecionado;
	}
	public void setLocalSelecionado(Local localSelecionado) {
		this.localSelecionado = localSelecionado;
	}
}
