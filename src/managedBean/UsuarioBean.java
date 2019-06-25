package managedBean;

import DAO.UsuarioDAO;
import modelo.Usuario;

public class UsuarioBean {
	UsuarioDAO ud = new UsuarioDAO();
	
	public void inserirPessoa(Usuario u) {
		ud.inserirUsuario(u);
	}
	
	public Usuario buscarLogin(String login, String senha) {
        System.out.println("buscou usuário");
		Usuario user = ud.buscarLogin(login, senha);
		return user;
	}
	
	public Usuario pegarPorId(int id) {
		Usuario u = ud.buscarPorId(id);
		return u;
	}
	
	
}
