package com.vgaw;

import org.apache.ibatis.session.SqlSession;

/**
 * Created by caojin on 2016/5/22.
 */
public class GetSqlSession {
    private static ThreadLocal<SqlSession> tl = new ThreadLocal<SqlSession>();

    public static SqlSession getSqlSession(){
        SqlSession sqlSession = tl.get();
        if (sqlSession == null){
            sqlSession = GetSqlSessionFactory.getSqlSessionFactory().openSession();
            tl.set(sqlSession);
        }
        System.out.println("获得的sqlSession对象的hashCode:" + sqlSession.hashCode());
        return sqlSession;
    }

    public static void commit(){
        if (tl.get() != null){
            tl.get().commit();
            tl.get().close();
            tl.set(null);
            System.out.println("提交了");
        }
    }

    public static void rollback(){
        if (tl.get() != null){
            tl.get().rollback();
            tl.get().close();
            tl.set(null);
            System.out.println("回滚了");
        }
    }
}
