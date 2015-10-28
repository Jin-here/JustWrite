package com.example.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.po.Classes;
import com.example.po.Student;
import com.example.util.HibernateUtil;

public class TestClient extends TestCase {
	public void testSave1() {
		HibernateUtil util = HibernateUtil.getInstance();

		Session session = null;
		Transaction tx = null;

		try {
			session = util.openSession();
			tx = session.beginTransaction();

			Classes classes = new Classes();
			//classes.setName("大一");
			classes.setName("大二");
			session.persist(classes);
			
			Set<Student> students = new HashSet<Student>();
			for (int i = 0;i < 10;i++){
				Student s = new Student();
				//s.setName("曹进" + i);
				s.setName("顾斯寒" + i);
				students.add(s);
				session.persist(s);
			}
			classes.setStudents(students);
			
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

			Student stu = new Student();
			stu.setName("曹进");
			
			Classes cls = new Classes();
			cls.setName("大一");
			session.persist(cls);
			
			stu.setCls(cls);
			
			session.persist(stu);
			
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

			Classes c = (Classes) session.load(Classes.class, 1);
			System.out.println(c.getName());
			
			Set<Student> students = c.getStudents();
			for (Iterator<Student> iter = students.iterator();iter.hasNext();){
				Student stu = iter.next();
				System.out.println(stu.getName());
			}
			
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
	
	public void testLoad2() {
		HibernateUtil util = HibernateUtil.getInstance();

		Session session = null;
		Transaction tx = null;

		try {
			session = util.openSession();
			tx = session.beginTransaction();

			Student stu = (Student) session.load(Student.class, 12);
			System.out.println(stu.getName());
			
			System.out.println(stu.getCls().getName());
			
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
