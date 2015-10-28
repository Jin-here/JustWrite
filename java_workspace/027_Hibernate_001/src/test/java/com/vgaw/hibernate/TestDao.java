package com.vgaw.hibernate;

import com.vgaw.hibernate.dao.saveDao;
import org.junit.Test;

public class TestDao {

	@Test
	public void testCreateTable() {
		saveDao dao = new saveDao();
		dao.saveUser(null);
	}

}
