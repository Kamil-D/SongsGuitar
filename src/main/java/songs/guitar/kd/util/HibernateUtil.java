package songs.guitar.kd.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static final SessionFactory sf; // Set up once for an app?
	
	static {
		sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory() {
		return sf;
	}

}
