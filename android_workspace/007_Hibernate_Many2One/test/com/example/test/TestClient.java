package com.example.test;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.po.Group;
import com.example.po.User;
import com.example.util.HibernateUtil;

public class TestClient extends TestCase {
	public void testSave1() {
		HibernateUtil util = HibernateUtil.getInstance();

		Session session = null;
		Transaction tx = null;

		try {
			session = util.openSession();
			tx = session.beginTransaction();

			Group group = new Group();
			group.setName("»Æ¶Ä¶¾");
			session.persist(group);
			
			User user = new User();
			user.setName("²Ü½ø");
			user.setGroup(group);
			
			session.persist(user);
			
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null){
				tx.rollback();
			}
			throw e;
		} finally {
			if (session != null) {
				util.closeSession(session);
			}
		}
	}
	
	public void testSave2() {
		HibernateUtil util = HibernateUtil.getInstance();

		Session session = null;
		Transaction tx = null;

		try {
			session = util.openSession();
			tx = session.beginTransaction();

			User user = new User();
			user.setName("²Ü½ø");
			session.persist(user);
			
			Group group = new Group();
			group.setName("»Æ¶Ä¶¾");
			session.persist(group);
			
			user.setGroup(group);
			
			
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null){
				tx.rollback();
			}
			throw e;
		} finally {
			if (session != null) {
				util.closeSession(session);
			}
		}
	}
	
	public void testLoad1() {
		HibernateUtil util = HibernateUtil.getInstance();

		Session session = null;
		Transaction tx = null;

		try {
			session = util.openSession();
			tx = session.beginTransaction();

			User user = (User) session.load(User.class, 1);
			System.out.println(user.getName());
			System.out.println(user.getGroup().getName());
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null){
				tx.rollback();
			}
			throw e;
		} finally {
			if (session != null) {
				util.closeSession(session);
			}
		}
	}
	
	public void testGet1() {
		HibernateUtil util = HibernateUtil.getInstance();

		Session session = null;
		Transaction tx = null;

		try {
			session = util.openSession();
			tx = session.beginTransaction();

			User user = (User) session.get(User.class, 1);
			System.out.println(user.getName());
			System.out.println(user.getGroup().getName());
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null){
				tx.rollback();
			}
			throw e;
		} finally {
			if (session != null) {
				util.closeSession(session);
			}
		}
	}
	
}
