package org.ssh.service.process.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.ssh.bean.TTaskDelegate;
import org.ssh.dao.process.TaskDelegateDao;
import org.ssh.service.process.TaskDelegateService;

@Service("taskDelegateService")
public class TaskDelegateServiceImpl implements TaskDelegateService {
	@Resource
	private TaskDelegateDao taskDelegateDao;

	@Override
	public TTaskDelegate getTaskDelegateInfo(String username) {
		return taskDelegateDao.getTaskDelegateInfo(username);
	}

}
