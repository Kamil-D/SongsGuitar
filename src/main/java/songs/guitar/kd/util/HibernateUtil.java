package songs.guitar.kd.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory; // Set up once for an app?
	// The main purpose of a service registry is to hold, manage and provide access to services.
	private static final ServiceRegistry serviceRegistry;

	static {
		//sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		Configuration configuration = new Configuration();
		configuration.configure("db/hibernate.cfg.xml");

		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

		serviceRegistryBuilder.applySettings(configuration.getProperties());

		serviceRegistry = serviceRegistryBuilder.build();

		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
