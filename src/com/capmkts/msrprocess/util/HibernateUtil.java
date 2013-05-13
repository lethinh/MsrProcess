package com.capmkts.msrprocess.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {
	
		if (sessionFactory == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			// sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		
		return sessionFactory;
	}

	public static Session getSession() {
		return getSessionFactory().openSession();
	}

	public static void save(Object object) {

		Session session = getSession();
		session.save(object);
		session.flush();
		session.close();
	}

	public static void update(Object object) {

		Session session = getSession();
		session.update(object);
		session.flush();
		session.close();
	}

	public static void delete(Object object) {

		Session session = getSession();
		session.delete(object);
		session.flush();
		session.close();
	}

	

}
