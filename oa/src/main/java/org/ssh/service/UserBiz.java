package org.ssh.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.ssh.bean.User;
import org.ssh.dao.UserDao;

@Service("userBiz")
public class UserBiz implements IuserBiz {
	@Resource
	private UserDao userDao;

	public List<User> getListUser() throws Exception {
		return userDao.getListUser();
	}
}
