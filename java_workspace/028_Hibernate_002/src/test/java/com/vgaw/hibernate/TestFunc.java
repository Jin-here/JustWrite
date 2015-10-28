package com.vgaw.hibernate;

import com.vgaw.hibernate.pojo.Group;
import com.vgaw.hibernate.pojo.User;
import com.vgaw.hibernate.util.Easy;
import com.vgaw.hibernate.util.HibernateUtil;
import junit.framework.TestCase;
import org.hibernate.Session;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2015/9/18.
 */
public class TestFunc extends TestCase{
    public void testCRUD(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        //add test
        /*User user = new User();
        user.setPassword("123");
        user.setNickName("caojin");
        session.persist(user);

        User user1 = new User();
        user1.setPassword("123");
        user1.setNickName("gusihan");
        session.persist(user1);

        List<User> members = new ArrayList<User>();
        members.add(user);
        members.add(user1);
        Group group = new Group();
        group.setName("kingdom");
        group.setMembers(members);
        session.persist(group);*/

        //query test
        //1:simple query
        List<Group> groups = session.createQuery("from Group").list();
        for (Group group : groups){
            //cascade delete by default
            session.delete(group);
        }
        List<User> members = session.createQuery("from User").list();
        for (User member : members){
            //cascade delete by default
            session.delete(member);
        }
        //2:fuck query
        /*String nickName = "caojin";
        User user = (User) session.createQuery("select p from User p where p.nickName = :pnickName")
                .setParameter("pnickName", nickName)
                .uniqueResult();
        List<Group> groups = user.getGroups();
        for (Group group : groups){
            System.out.println(group.getName());
        }*/




        session.getTransaction().commit();

        HibernateUtil.getSessionFactory().close();
    }

    public void testAdd(){
        new Easy() {
            @Override
            public void in(Session session) {
                User user = new User();
                user.setNickName("chenkai");
                user.setPassword("123456");

                Group group = new Group();
                group.setName("kingdom");
                session.persist(group);

                Group group1 = new Group();
                group.setName("kingdom1");
                session.persist(group1);

                List<Group> groups = new ArrayList<Group>();
                groups.add(group);
                groups.add(group1);

                user.setGroups(groups);
                session.persist(user);
            }
        }.execute();




        HibernateUtil.getSessionFactory().close();
    }


    public void testFetch(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = (User) session.createQuery("select p from User p left join fetch p.groups where p.id = :pid")
                .setParameter("pid", "c60abebc-78cd-430c-924f-5e4be4071ac3")
                .uniqueResult();
        Group group = new Group();
        group.setName("kingdom2");
        session.persist(group);
        session.getTransaction().commit();

        user.getGroups().add(group);

        Session session1 = HibernateUtil.getSessionFactory().getCurrentSession();
        session1.beginTransaction();
        session1.update(user);
        session1.getTransaction().commit();
    }

}
