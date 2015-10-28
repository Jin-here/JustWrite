package com.vgaw.hibernate.util;

import org.hibernate.Session;

/**
 * Created by Administrator on 2015/9/25.
 */
public abstract class Easy {
    public Easy(){}

    public void execute(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        in(session);

        session.getTransaction().commit();
    }

    public abstract void in(Session session);
}
