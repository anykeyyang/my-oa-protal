package org.ssh.listener;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.EngineServices;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.task.Task;
import org.ssh.bean.TTaskDelegate;
import org.ssh.service.process.ProcessService;
import org.ssh.service.process.TaskDelegateService;

public class TaskDelegateListener implements ActivitiEventListener {

	@Resource
	private TaskDelegateService taskDelegateService;
	@Resource
	private ProcessService processService;

	@Override
	public void onEvent(ActivitiEvent event) {
		String processInstanceId = event.getProcessInstanceId();
		EngineServices service = event.getEngineServices();
		List<Task> tasks = service.getTaskService().createTaskQuery()
				.processInstanceId(processInstanceId).active().list();
		for (Task task : tasks) {
			String assignUser = task.getAssignee();
			if (assignUser != null) {
				TTaskDelegate delegateInfo = taskDelegateService
						.getTaskDelegateInfo(assignUser);
				String delegateUser = delegateInfo.getDelegateUser();
				if (delegateUser != null) {
					processService.delegateTask(task.getId(), delegateUser);
				}
			}
		}
	}

	@Override
	public boolean isFailOnException() {
		return false;
	}

}
