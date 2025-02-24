package Config;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Model.Tip;

public class HibernateDB {

	private static SessionFactory factory;
	private static Session sesion;
	
	public static Session getSession() throws HibernateException {
		factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Tip.class).buildSessionFactory();
		sesion=factory.openSession();
		return sesion;
	}
}
