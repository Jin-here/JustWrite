package com.example.test;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.po.IdCard;
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

			IdCard idcard = new IdCard();
			idcard.setCardNum("321088...");
			session.persist(idcard);
			
			User user = new User();
			user.setName("²Ü½ø");
			session.persist(user);
			
			User user1 = new User();
			user1.setName("¹ËË¹º®");
			session.persist(user1);
			
			
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
