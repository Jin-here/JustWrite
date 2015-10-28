package com.example.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
private static HibernateUtil instance;
	
	private SessionFactory factory;
	private HibernateUtil() {
		// TODO Auto-generated constructor stub// 加载/hibernate.properties
		Configuration cfg = new Configuration();
		// 加载/hibernate.cfg.xml
		cfg.configure();

		//factory = cfg.buildSessionFactory();

		ServiceRegistry serviceRegistryBuilder = new StandardServiceRegistryBuilder()
				.applySettings(cfg.getProperties()).build();
		factory = cfg
				.buildSessionFactory(serviceRegistryBuilder);
	}
	
	/**
	 * 获取SessionFactory
	 * @return
	 */
	public static HibernateUtil getInstance() {
        if (instance == null) {
            synchronized (HibernateUtil.class) {
                instance = new HibernateUtil();
            }
        }
        return instance;
    }
	
	/**
	 * 打开Session
	 * @return
	 */
	public Session openSession(){
		Session sess = factory.openSession();
		return sess;
	}
	
	/**
	 * 关闭Session
	 * @param session
	 */
	public void closeSession(Session session){
		session.close();
	}
}
