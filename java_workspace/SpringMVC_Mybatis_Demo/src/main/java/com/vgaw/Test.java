package com.vgaw;

import com.vgaw.orm.User;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by caojin on 2016/5/22.
 */
public class Test {
    public static void main(String[] args){
        /*User user = GetSqlSession.getSqlSession().selectOne("getUserById", 1);
        System.out.println(user.getName());*/
        List<User> pageList = GetSqlSession.getSqlSession().selectList("selectPage", null, new RowBounds(4, 5));
        System.out.println(pageList.size());
        /*for (int i = 1;i < 5;i++){
            HashMap map = new HashMap();
            map.put("id", i);
            if (i == 4){
                map.put("name", null);
            }else {
                map.put("name", "name" + i);
            }
            GetSqlSession.getSqlSession().update("updateUserById", map);
            GetSqlSession.commit();
        }*/
    }
}
