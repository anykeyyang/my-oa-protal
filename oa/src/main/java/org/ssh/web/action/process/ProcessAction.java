package org.ssh.web.action.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.DiagramLayout;
import org.activiti.engine.repository.DiagramNode;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.ssh.service.process.ProcessService;
import org.ssh.vo.ActivityInfo;

public class ProcessAction {
	@Resource
	private ProcessService processService;

	private int numPerPage = 20;
	private int totalCount = 0;
	private int pageNum = 1;

	private String processDefinitionId;
	private String processInstanceid;

	private String auditImageBase64;
	private List<ActivityInfo> activityList;
	private List<ProcessDefinition> processDefinitions;
	private List<HistoricProcessInstance> hiProcessInstance;
	private List<Task> tasks;

	/**
	 * 获取部署流程
	 * 
	 * @return
	 */
	public String getDeployedProcess() {
		setProcessDefinitions(processService.getProcessDefinitionActive());
		return "deployedProcess";
	}

	/**
	 * 获取用户参与流程
	 * 
	 * @return
	 */
	public String getInstanceInvolvedUser() {
		hiProcessInstance = processService.getInstanceInvolvedUser("kafeitu");
		return "instanceInvolvedUser";

	}

	/**
	 * 获取用户待办任务
	 * 
	 * @return
	 */
	public String getUserTask() {
		setTasks(processService.getUserTask("leaderuser"));
		return "userTaskManagement";
	}

	public String getAuditImage() {
		activityList = new ArrayList<ActivityInfo>();
		// 获取流程图片
		auditImageBase64 = processService.getProcessImage(processDefinitionId);
		// 获取历史任务
		List<HistoricTaskInstance> finishedTasks = processService
				.getTasksHasFinished(processInstanceid);
		// 获取节点坐标
		Map<String, DiagramNode> layout = processService
				.getDiagramLayout(processDefinitionId);
		// 获取已经完成任务的图片坐标
		for (HistoricTaskInstance taskInstance : finishedTasks) {
			ActivityInfo activity = new ActivityInfo();
			String key = taskInstance.getTaskDefinitionKey();
			DiagramNode node = layout.get(key);
			activity.setTask(taskInstance);
			// 坐标修正
			activity.setX(node.getX() + 4);
			activity.setY(node.getY() + 30);
			activity.setWidth(node.getWidth());
			activity.setHeight(node.getHeight());
			activityList.add(activity);
		}

		return "auditImage";

	}

	public List<ProcessDefinition> getProcessDefinitions() {
		return processDefinitions;
	}

	public void setProcessDefinitions(List<ProcessDefinition> processDefinitions) {
		this.processDefinitions = processDefinitions;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List<HistoricProcessInstance> getHiProcessInstance() {
		return hiProcessInstance;
	}

	public void setHiProcessInstance(
			List<HistoricProcessInstance> hiProcessInstance) {
		this.hiProcessInstance = hiProcessInstance;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getAuditImageBase64() {
		return auditImageBase64;
	}

	public void setAuditImageBase64(String auditImageBase64) {
		this.auditImageBase64 = auditImageBase64;
	}

	public String getProcessInstanceid() {
		return processInstanceid;
	}

	public void setProcessInstanceid(String processInstanceid) {
		this.processInstanceid = processInstanceid;
	}

	public List<ActivityInfo> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ActivityInfo> activityList) {
		this.activityList = activityList;
	}
}
