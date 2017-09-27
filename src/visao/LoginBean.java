package visao;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import controle.UsuarioControle;
import modelo.Usuario;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

	private boolean logado;
	private Usuario usuarioLogado;
	private String login;
	private String senha;
	private UsuarioControle usuarioControle = new UsuarioControle();

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String doLogin() {
		FacesContext context = FacesContext.getCurrentInstance();
		usuarioLogado = usuarioControle.autenticarUsuario(login, senha);
		if (usuarioLogado.getEmail().equals("admin@svio.com") && usuarioLogado.getSenha().equals("adminsvio")) {
			logado = true;
			return "/admin/indexADM";
		}
		if (usuarioLogado == null) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario ou senha inválido!", ""));
		} else {
				logado = true;
				return "index";
		}
	return "";
	}
	
	
	public String logout() {
		usuarioLogado = new Usuario();
		usuarioLogado = null;
		logado = false;
		return "index";
	}
	
	public String logoutOlimpiadas() {
		usuarioLogado = new Usuario();
		usuarioLogado = null;
		logado = false;
		return "olimpiadas";
	}
	
	public String confirmarUsuario() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		if (usuarioLogado.getSenha().equals(loginBean.senha)) {
			return "listaIngresso";
		}
		return "";
	}
	
}
