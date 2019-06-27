package conexao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ConexaoUsuario {
	private static final SessionFactory sessionAdm = construirFabrica("cfadm");
	private static final SessionFactory sessionUsuario = construirFabrica("cfb1");
	private static final SessionFactory sessionUsuario1 = construirFabrica("cfb2");

	private static SessionFactory construirFabrica(String bd) {
		try {
			String base = "/configuracao/hibernate." + bd + ".xml";
			final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure(base).build();
			return new MetadataSources(registro).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static SessionFactory getSessionFactory(String bd) {
		SessionFactory retorno = null;
		switch (bd) {
		case "cfadm":
			retorno = sessionAdm;
			break;
		case "cfb1":
			retorno = sessionUsuario;
			break;
		case "cfb2":
			retorno = sessionUsuario1;
			break;
		}
		return retorno;
	}

	public static void shutdown(String bd) {
		switch (bd) {
		case "cfadm":
			sessionAdm.close();
			break;
		case "cfb1":
			sessionUsuario.close();
			break;
		case "cfb2":
			sessionUsuario1.close();
			break;
		}
	}
}
