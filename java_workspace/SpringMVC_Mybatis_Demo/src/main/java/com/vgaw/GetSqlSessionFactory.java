package com.vgaw;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by caojin on 2016/5/22.
 */
public class GetSqlSessionFactory {
    private static SqlSessionFactory sqlSessionFactory;

    private GetSqlSessionFactory() {
    }

    public static synchronized SqlSessionFactory getSqlSessionFactory() {
        try {
            if (sqlSessionFactory == null) {
                InputStream isRef = GetSqlSession.class.getResourceAsStream("/db.properties");
                Properties properties = new Properties();
                properties.load(isRef);

                String resource = "mybatis-config.xml";
                InputStream in = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(in, properties);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }

}
