package org.ssh.service.process;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public interface ProcessService {

	/**
	 * 获取数据库中可用的流程
	 * 
	 * @return
	 */
	public List<ProcessDefinition> getProcessDefinitionActive();

	/**
	 * 获取待办的任务
	 * 
	 * @param userName
	 * @return
	 */
	public List<Task> getUserTask(String userName);

	/**
	 * 获取正在运行的流程
	 * 
	 * @return
	 */
	public List<Task> getTaskListActive();

	/**
	 * 获取本人发起的流程
	 * 
	 * @param userName
	 * @param hasFinished
	 *            是否已经完成
	 * @return
	 */
	public List<HistoricProcessInstance> getHasActivedProcessByUser(
			String userName, Boolean hasFinished);

	/**
	 * 流程启动
	 * 
	 * @param processDefinitionId
	 */
	public ProcessInstance startProcessInstance(String processDefinitionId, String userId);

	/**
	 * 流程部署
	 * 
	 * @param barFile
	 */
	public void deployProcess(String barFile);

	/**
	 * 流程部署
	 * 
	 * @param is
	 *            输入流
	 * @param fileName
	 *            文件名
	 */
	public boolean depolyProcess(InputStream is, String fileName);

	/**
	 * 删除部署流程
	 * 
	 * @param deploymendId
	 */
	public void removeDeployProcess(String deploymentId);

	/**
	 * 任务签收
	 * 
	 * @param taskId
	 * @param userId
	 */
	public void claimTask(String taskId, String userId);

	/**
	 * 获取绑定的表单
	 * 
	 * @param taskId
	 * @return
	 */
	public Object getRederForm(String taskId);

	/**
	 * 获取内置表单项
	 * 
	 * @param taskId
	 * @return
	 */
	public TaskFormData getFormData(String taskId);

	/**
	 * 任务完成
	 * 
	 * @param taskId
	 * @return
	 */
	public Boolean completeTask(String taskId, Map map);

	/**
	 * 流程跟踪
	 * 
	 * @param processInstanceId
	 * @return
	 */
	public InputStream readProcessSource(String processInstanceId);
}
