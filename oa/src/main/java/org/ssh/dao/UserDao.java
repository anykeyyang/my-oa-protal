package org.ssh.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.ssh.bean.User;

@Repository
public class UserDao {
	@Resource
	private SessionFactory sessionFactory;

	public List<User> getListUser() {
		List<User> list = null;
		list = sessionFactory.openSession().createQuery("from User").list();
		return list;
	}
}
