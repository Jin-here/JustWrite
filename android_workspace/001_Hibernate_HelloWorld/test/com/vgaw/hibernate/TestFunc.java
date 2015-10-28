package com.vgaw.hibernate;

import com.vgaw.hibernate.pojo.Group;
import com.vgaw.hibernate.pojo.User;
import com.vgaw.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/9/17.
 */

public class TestFunc {
    @Test
    public void testSave(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        /*User user = new User();
        user.setPassword("caojin");
        user.setNickName("²Ü½ø");
        session.persist(user);*/

        Group group = new Group();
        group.setName("kingdom");
        session.persist(group);

        session.getTransaction().commit();

        HibernateUtil.getSessionFactory().close();
    }
}
