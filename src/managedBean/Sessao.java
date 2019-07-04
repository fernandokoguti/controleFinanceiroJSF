package managedBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import DAO.UsuarioDAO;
import conexao.Conexao;
import modelo.Usuario;

@ManagedBean(name="sessao")
@SessionScoped
public class Sessao {
	// Variável estática que conterá a instancia da classe
	private static Sessao instance;
	private Conexao conexao = new Conexao();
	private Session sessao = conexao.getSessionFactory().openSession();
	private static Usuario usuarioLogado;
	private String login;
	private String senha;

	// Construtor
	public Sessao() {
	}

	// Método público estático de acesso único ao objeto!
	public static Sessao getInstance() {
		if (instance == null) {
			usuarioLogado = new Usuario();
			instance = new Sessao();
		}
		return instance;
	}

	public Usuario getUsuarioLogado() {
		System.out.println("Entrou getUsuarioLogado com usuario " + usuarioLogado.getNome());
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuario) {
		Sessao.usuarioLogado = usuario;
	}

	public String logar() {
		String retorno = "";
		System.out.println("entrou login");
		System.out.println(login + " " + senha);
//			Session sessao = ConexaoAdm.getSessionFactory().openSession();
		try {
			UsuarioDAO ud = new UsuarioDAO();
			Usuario usuario = ud.buscarLogin(login, senha);
			Sessao.usuarioLogado = usuario;
			if (usuarioLogado == null) {
				FacesContext.getCurrentInstance().addMessage(":formLogin:messagesLogin",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou Senha Inválidos", "Login Inválido"));
				System.out.println("entrou senha errada");
				retorno = null;
			} else {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(false);
				if (session != null)
					session.setAttribute("usuario", usuarioLogado);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioLogado);
				System.out.println("entrou senha certa");
				if(usuarioLogado.getTipoUsuario() == 0)
					retorno = "/index?faces-redirect=true";
				else
					retorno = "/admin?faces-redirect=true";
			}

		} catch (HibernateException e) {
			// sessao.getTransaction().rollback();
			new Exception("Não possível retornar resultado " + e);
		} finally {
			conexao.shutdown();
		}
		return retorno;
	}

	public String logoff() {
		sessao.close();
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

}
