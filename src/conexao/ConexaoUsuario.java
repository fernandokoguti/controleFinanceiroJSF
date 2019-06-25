package conexao;

import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import modelo.Usuario;

public class ConexaoUsuario {
	private static Usuario u;
	private static final SessionFactory sessionFactory = construirFabrica();
	
	private static SessionFactory construirFabrica() {
		///u = Sessao.getInstance().getUsuarioLogado();
//		u = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
//		if(u != null) {
		try {
				//if(u.getNomeBase().equals("cfb1")) {
			final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure("/configuracao/hibernate.cfb1.xml").build();	
			return new MetadataSources(registro).buildMetadata().buildSessionFactory();
//			}else if(u.getNomeBase().equals("cfb2")) {
//				final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure("/configuracao/hibernate.cfb2.xml").build();	
//				return new MetadataSources(registro).buildMetadata().buildSessionFactory();
//			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
			}
//		}		
}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}
	
}
