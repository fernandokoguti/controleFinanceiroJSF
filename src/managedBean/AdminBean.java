package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
import org.hibernate.query.Query;

import conexao.ConexaoUsuario;
import modelo.Movimentacao;
import modelo.Usuario;

@ManagedBean(name = "adminBean")
@SessionScoped
public class AdminBean {
	private Usuario usuario = new Usuario(1);
	private boolean painel = false;
	private ConexaoUsuario conexao = new ConexaoUsuario();
	
	public List<Usuario> pegarTodosUsuarios() {
		System.out.println("buscou usuário");
		List<Usuario> users = null;
		try {
			String base = "cf";
			Session sessionAdm = conexao.getSessionFactory(base).openSession();
			Query<Usuario> q = sessionAdm.createQuery("from Usuario");
			users = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Pauleou valendo no usuario: "+e.getStackTrace());// TODO: handle exception
		}
		return users;
	}
	
	public void listar(String id) {
		List<Usuario> users = pegarTodosUsuarios();
		for (Usuario u : users) {
			if(u.getId() == Integer.parseInt(id))
				System.out.println("Setou usuario = "+ u.getNome());
				usuario = u;
				painel = true;
		}
	}
	
	public List<Movimentacao> pegarMovimentacoes() {
	System.out.println(usuario.getNome()+" ### entrou pegarMovimentacoes");
	List<Movimentacao> movimentacoes = null;
	try {
		String base = usuario.getNomeBase();
		Session sessionAdm = conexao.getSessionFactory(base).openSession();
		Query<Movimentacao> q = sessionAdm.createQuery("from Movimentacao");
			movimentacoes = q.getResultList();
			return movimentacoes;
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		System.out.println("Pauleou valendo no movimentacoes: "+e.getStackTrace());// TODO: handle exception
	}
	return movimentacoes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isPainel() {
		return painel;
	}

	public void setPainel(boolean painel) {
		this.painel = painel;
	}
	
}
