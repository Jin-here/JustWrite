package com.vgaw.hibernate.util;

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
		try{
			Configuration cfg = new Configuration();
			// 加载/hibernate.cfg.xml
			cfg.configure();

			//factory = cfg.buildSessionFactory();

			ServiceRegistry serviceRegistryBuilder = new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).build();
			factory = cfg
					.buildSessionFactory(serviceRegistryBuilder);
		}catch (Throwable ex){
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
		}
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
	public Session getSession(){
		return factory.getCurrentSession();
	}

    /**
     * 关闭SessionFactory
     */
    public void close(){
        factory.close();
    }
	
}
