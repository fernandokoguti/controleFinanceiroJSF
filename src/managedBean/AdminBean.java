package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;

import DAO.MovimentacaoDAO;
import conexao.ConexaoUsuario;
import modelo.Movimentacao;
import modelo.Usuario;

@ManagedBean(name = "adminBean")
@RequestScoped
public class AdminBean {
	// private Session sessionAdmUsuario =
	// Conexao.getSessionFactory().openSession();
	// private UsuarioDAO udao = new UsuarioDAO();
	private MovimentacaoDAO mdao = new MovimentacaoDAO();
	private List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
	private List<Movimentacao> movimentacoesAdm = new ArrayList<Movimentacao>();

	@PersistenceContext
	private EntityManagerFactory emf;

	public List<Movimentacao> pegarTodos() {
		System.out.println("buscou usuário");
		try {
			EntityManager em = emf.createEntityManager();
			Query<Usuario> qUser = (Query<Usuario>) em.createQuery("from Usuario");
			List<Usuario> users = qUser.getResultList();
			System.out.println(qUser+"###"+qUser.getResultList());
		 //List<Usuario> users = udao.buscarTodos();
			for (Usuario usuario : users) {
				String base = usuario.getNomeBase();
				Session sessionAdm = ConexaoUsuario.getSessionFactory(base).openSession();
				Query<Movimentacao> q = sessionAdm.createQuery("from Movimentacao");
				movimentacoes = q.getResultList();
				for (Movimentacao movimentacao : movimentacoes) {
					movimentacoesAdm.add(movimentacao);
				}
				ConexaoUsuario.getSessionFactory(base).close();
			}
			return movimentacoesAdm;
		} catch (Exception e) {
			System.out.println("PAuleou " + e);// TODO: handle exception
		}
		return null;
	}
}
