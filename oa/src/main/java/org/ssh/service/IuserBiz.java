package org.ssh.service;

import java.util.List;

import org.ssh.bean.User;

public interface IuserBiz {
	public List<User> getListUser() throws Exception;
}
