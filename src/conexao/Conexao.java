package conexao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Conexao {
	
	private final SessionFactory sessionFactory = construirFabrica();
	
	private SessionFactory construirFabrica() {
		try {
		final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure("/configuracao/hibernate.cf.xml").build();
		
		return new MetadataSources(registro).buildMetadata().buildSessionFactory();	
	}catch(Exception e){
		e.printStackTrace();
		return null;
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void shutdown() {
		getSessionFactory().close();
	}
	
}
