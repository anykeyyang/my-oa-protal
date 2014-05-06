package org.ssh.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.ssh.bean.User;
import org.ssh.service.IuserBiz;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Resource
	private IuserBiz userBiz;
	
	private List<User> list;
	private String email;
	private String password;
	private String username;

	@Override
	public String execute() {
		try {
			list = userBiz.getListUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
