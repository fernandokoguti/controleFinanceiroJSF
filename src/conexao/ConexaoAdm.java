package conexao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ConexaoAdm {
	
	private static final SessionFactory sessionFactory = construirFabrica();
	
	private static SessionFactory construirFabrica() {
		try {
		final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure("/configuracao/hibernate.cfadm.xml").build();
		
		return new MetadataSources(registro).buildMetadata().buildSessionFactory();	
	}catch(Exception e){
		e.printStackTrace();
		return null;
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}
	
}
