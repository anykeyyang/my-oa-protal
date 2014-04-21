package org.ssh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.ssh.bean.User;

@Repository
public class UserDao extends BaseDao<User> {

	public List<User> getListUser() throws Exception {
		List<User> list = null;
		list = this.findAll();
		return list;
	}
}
