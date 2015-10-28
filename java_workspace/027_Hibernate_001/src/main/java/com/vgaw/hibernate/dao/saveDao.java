package com.vgaw.hibernate.dao;

import com.vgaw.hibernate.pojo.Group;
import com.vgaw.hibernate.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class saveDao {

	public Session getSession()
	{
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.getCurrentSession();
		return session;
	}
	
	public void saveUser(User user)
	{
		Session session= this.getSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}
}
