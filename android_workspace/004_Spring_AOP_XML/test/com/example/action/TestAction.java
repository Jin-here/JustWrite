package com.example.action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.aspect.ExtraAdvice;
import com.example.aspect.LogManager;
import com.example.dao.Dao;
import com.example.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestAction {
	//��������������ļ��е�IDһ�£������ҵ������Զ�ע��
	@Autowired
	Dao userDao = null;
	@Autowired
	Dao thingDao = null;
	@Autowired
	LogManager logManager = null;
	
	@Test
	public void TestUserDao(){
		//userDao.add();
		userDao.showTip();
		thingDao.add();
		((ExtraAdvice)userDao).sell("������", "�¿�");
		//userDao.delete();
		//thingDao.add();
	}
}
