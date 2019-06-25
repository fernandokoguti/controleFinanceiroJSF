package managedBean;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import DAO.UsuarioDAO;
import conexao.ConexaoAdm;
import modelo.Usuario;

public class Sessao {
		// Variável estática que conterá a instancia da classe
		private static Sessao instance;
		private static Usuario usuarioLogado;

		// Construtor 
		public Sessao() {}

		// Método público estático de acesso único ao objeto!
		public static Sessao getInstance() {
		    if (instance == null) {
		        usuarioLogado = new Usuario();
		        instance = new Sessao();
		    }
		    return instance;
		}

		public Usuario getUsuarioLogado() {
			System.out.println("Entrou getUsuarioLogado com usuario"+usuarioLogado.getNome());
			return usuarioLogado;}
		
		public void setUsuarioLogado (Usuario usuario) {Sessao.usuarioLogado  = usuario;}
		
		public static String verificaLogin(String login, String senha) throws Exception {
			System.out.println("entrou login");
			System.out.println(login + " " + senha);
//			Session sessao = ConexaoAdm.getSessionFactory().openSession();
		    try {
		    	//sessao.beginTransaction();
//		    	Query q = sessao.createQuery("from Usuario where login = :login AND senha = :senha");
//		    	q.setString("login",login);
//		    	q.setString("senha",senha);
//		    	Usuario usuario = (Usuario)q.uniqueResult();
		    	UsuarioDAO ud =  new UsuarioDAO();
		    	Usuario usuario = ud.buscarLogin(login, senha);
		    	Sessao.usuarioLogado = usuario;
		    	//sessao.getTransaction().commit();
		    	if(usuario != null)
		    	return ("Bem Vindo Usuário " + usuario.getNome() + "!");
		    	return ("Usuário ou senha inválidos!");
		    } catch( HibernateException e ) {
		    	//sessao.getTransaction().rollback();
		    	throw new Exception("Não possível retornar resultado " + e);
		    } finally {
				ConexaoAdm.shutdown();
		    }
		}
}

