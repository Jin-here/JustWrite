package com.vgaw;

import com.vgaw.GetSqlSession;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

/**
 * Created by caojin on 2016/5/22.
 */
public class DBOperate {
    public int insert(String sql, Map valueMap){
        SqlSession sqlSession = GetSqlSession.getSqlSession();
        return sqlSession.insert(sql, valueMap);
    }

}
