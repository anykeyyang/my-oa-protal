package org.ssh.service.process.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssh.service.process.ProcessService;

@Service("processService")
@SuppressWarnings({ "rawtypes", "unchecked" })
@Transactional
public class ProcessServiceImpl implements ProcessService {
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private FormService formService;
	@Resource
	private IdentityService identityService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private ManagementService managementService;

	@Override
	public List<ProcessDefinition> getProcessDefinitionActive() {
		return repositoryService.createProcessDefinitionQuery().active().list();
	}

	@Override
	public List<Task> getUserTask(String userName) {
		
		return taskService.createTaskQuery().taskAssignee(userName).list();
	}

	@Override
	public List<Task> getTaskListActive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistoricProcessInstance> getHasActivedProcessByUser(
			String userName, Boolean hasFinished) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessInstance startProcessInstance(String processDefinitionId,
			String userId) {
		Map map = new HashMap();
		map.put("owner", userId);
		this.identityService.setAuthenticatedUserId(userId);
		ProcessInstance instance = runtimeService.startProcessInstanceById(
				processDefinitionId, map);
		return instance;

	}

	@Override
	public void deployProcess(String barFile) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean depolyProcess(InputStream is, String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeDeployProcess(String deploymentId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void claimTask(String taskId, String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getRederForm(String taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskFormData getFormData(String taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean completeTask(String taskId, Map map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream readProcessSource(String processInstanceId) {
		// TODO Auto-generated method stub
		return null;
	}

}
