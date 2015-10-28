package com.vgaw.hibernate.test;

import com.vgaw.hibernate.pojo.Group;
import com.vgaw.hibernate.pojo.User;
import com.vgaw.hibernate.util.HibernateUtil;
import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class TestClient extends TestCase {
	public void testSave1() {
		HibernateUtil util = HibernateUtil.getInstance();

		Session session = null;
		Transaction tx = null;

		try {
			session = util.openSession();
			tx = session.beginTransaction();

			User user = new User();
			user.setNickName("caojin");
			user.setPassword("123");

            Group group = new Group();
            group.setName("kingdom");
            session.persist(group);

            Set groups = new HashSet<>();
            groups.add(group);
            user.setGroups(groups);
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
	
}
