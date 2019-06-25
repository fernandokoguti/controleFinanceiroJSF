package managedBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelo.Usuario;

@ManagedBean
@SessionScoped
public class Acesso {
    private String login;
    private String senha;
    public Usuario usuarioLogado;

    public Acesso() {
    }
    
	public String logar() {
		String retorno = "";
		System.out.println("entrou login");
		System.out.println(login + " " + senha);
		UsuarioBean uc = new UsuarioBean();
		usuarioLogado = uc.buscarLogin(login, senha);
		System.out.println("buscou usuário");
		System.out.println(usuarioLogado.getNome());

		if (usuarioLogado == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou Senha Inválidos", "Login Inválido"));
			System.out.println("entrou senha errada");
			retorno = null;
		} else {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			if (session != null) {
				session.setAttribute("usuario", usuarioLogado);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioLogado);
				System.out.println("entrou senha certa");
			}
			retorno = "/index?faces-redirect=true";
		}
		return retorno;
	}

    public String logOff() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        return "/login?faces-redirect=true";
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

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}