package org.ssh.service.process;

import org.ssh.bean.TTaskDelegate;

public interface TaskDelegateService {
	/**
	 * 获取用户任务委派信息
	 * 
	 * @param username
	 * @return
	 */
	public TTaskDelegate getTaskDelegateInfo(String username);
}
