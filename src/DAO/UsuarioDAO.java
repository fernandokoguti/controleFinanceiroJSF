package DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;

import conexao.Conexao;
import modelo.Usuario;
	  
	public class UsuarioDAO {
		private Usuario u;
		private Session sessao = Conexao.getSessionFactory().openSession();
		
	 
	public Usuario buscarLogin(String login, String senha) {
		try {
	    	Query<Usuario> q = sessao.createQuery("from Usuario where login = :login AND senha = :senha");
	    	q.setParameter("login",login);
	    	q.setParameter("senha",senha);
	    	u = (Usuario)q.uniqueResult();
			return u;
			} catch (Exception e) {
				return null;
			}
		}
	
	public List<Usuario> buscarTodos() {
		Query<Usuario> q = sessao.createQuery("from Usuario");
		List<Usuario> usuarios = q.getResultList();
		return usuarios;
	}
	
	public Usuario buscarPorId(int id) {
		try {
			Query<Usuario> query = sessao.createQuery("FROM Usuario as u WHERE u.id = :id");
			query.setParameter("id", id);
			return query.uniqueResult();

			} catch (Exception e) {
				return null;
			}
		}
	 
	  public boolean inserirUsuario(Usuario usuario) {
		  try {
				sessao.beginTransaction();
				sessao.save(usuario);
				sessao.getTransaction().commit();
				return true;
	          } catch (Exception e) {
	        	  e.printStackTrace();
	        	  return false;
	          }
	    }
	  
	  public List<Usuario> pegarTodos(){
			Session sessao = Conexao.getSessionFactory().openSession();
			Query<Usuario> query = sessao.createQuery("FROM Usuario");
			return query.list();
		}
		
		public Usuario pegarPorIdNamed(int id){
			Session sessao = Conexao.getSessionFactory().openSession();
			Query<Usuario> query = sessao.getNamedQuery("pegarPorId").setParameter("id", id);
			query.setParameter("id", id);
			return query.uniqueResult();
		}
		
		public List<Usuario> pegarTodosCriteria(){
			Session sessao = Conexao.getSessionFactory().openSession();
			Criteria crit = sessao.createCriteria(Usuario.class);
			return crit.list();
		}

	     
	    public boolean deletarUsuario(Usuario usuario) {
	    	try {
	    		sessao.beginTransaction();
	    		sessao.remove(usuario);
	    		sessao.getTransaction().commit();
	    		return true;
	          } catch (Exception e) {
	        	  e.printStackTrace();
	        	  return false;
	          }
	    }	  
}
