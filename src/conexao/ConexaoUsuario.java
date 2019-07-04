package conexao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ConexaoUsuario {

	public  SessionFactory construirFabrica(String bd) {
		try {
			String base = "/configuracao/hibernate." + bd + ".xml";
			final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure(base).build();
			return new MetadataSources(registro).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public SessionFactory getSessionFactory(String bd) {
		SessionFactory retorno = null;
		if (bd == null)
			return retorno;
		return construirFabrica(bd);
	}

	public void shutdown(String bd) {
		getSessionFactory(bd).close();
	}
}
