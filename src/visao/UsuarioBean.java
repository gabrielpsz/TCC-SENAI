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

import org.primefaces.event.FlowEvent;

import modelo.Usuario;
import controle.UsuarioControle;

@ManagedBean (name="UsuarioBean")
@SessionScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private List<Usuario> listUsuario = new ArrayList<Usuario>();
	private UsuarioControle usuarioControle = new UsuarioControle();
	private Usuario usuarioSelecionado = new Usuario();
	private String confirmaSenha;
	private boolean skip;

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public UsuarioBean() {
		usuarioControle = new UsuarioControle();
	}
	
	public String salvar() {
		FacesContext faces = FacesContext.getCurrentInstance();
		if (!usuario.getSenha().equals(confirmaSenha)) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas não conferem!", ""));
			return "";
		}
			try {
				usuarioControle.salvar(usuario);
				faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso!", ""));
			} catch (Exception e) {
				faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
				e.printStackTrace();
				return "";
			}	
			return "index";
	}
	
	public String buscarPorId() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			usuario = usuarioControle.buscarPorId(usuario.getIdUsuario());
			return "cadastroUsuario";
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String cadastroUsuario() {
		if (!(usuario == null)) {
			usuario = new Usuario();
			return "cadastroUsuario";
		}
		return "cadastroUsuario";
	}
	

	public String buscarTodos() {
		listUsuario = usuarioControle.buscarTodos();
		return "listarUsuario";
	}
	
	public String alterar() {
		FacesContext faces = FacesContext.getCurrentInstance();
		if (usuarioSelecionado == null) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Você deve selecionar um item para alterar", ""));
			return "";
		}
		this.usuario = this.usuarioSelecionado;
		return "cadastroUsuario";
	}
	
	public String excluir() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			usuarioControle.excluir(usuarioSelecionado);
			listUsuario.remove(usuarioSelecionado);
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário excluido com sucesso!", ""));
			return "";
		} catch (Exception e) {
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		return "";
	}
	
	public String buscarPorCpf() {
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			this.listUsuario = usuarioControle.buscarPorCPF(this.usuario.getCpf());
		} catch (Exception e) {
			e.printStackTrace();
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			return "";
		}
		return "listarUsuario";
	}
	
	public void getGerarRelatorio() {
		try {
			File file = new File(usuarioControle.gerarRelatorio());
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
	
	public String voltar() {
		usuario = new Usuario();
		return "index";
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getListUsuario() {
		return listUsuario;
	}
	public void setListUsuario(List<Usuario> listUsuario) {
		this.listUsuario = listUsuario;
	}
	public UsuarioControle getUsuarioControle() {
		return usuarioControle;
	}
	public void setUsuarioControle(UsuarioControle usuarioControle) {
		this.usuarioControle = usuarioControle;
	}
	
	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
	
//	public void irAdm() {
//		FacesContext context = FacesContext.getCurrentInstance();
//		HttpSession session = ((HttpServletRequest) context.getELContext()).getSession();
//		LoginBean loginBean = (LoginBean) session.getAttribute("LoginBean");
//	}
	
}
