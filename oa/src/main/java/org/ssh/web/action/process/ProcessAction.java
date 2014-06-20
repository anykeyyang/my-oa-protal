package org.ssh.web.action.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.DiagramNode;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.ssh.service.process.ProcessService;
import org.ssh.vo.ActivityInfo;
import org.ssh.web.action.BaseAction;

public class ProcessAction extends BaseAction {
	@Resource
	private ProcessService processService;

	private int numPerPage = 20;
	private int totalCount = 0;
	private int pageNum = 1;

	private String processDefinitionId;
	private String processInstanceid;
	private String taskid;

	private String auditImageBase64;
	private List<ActivityInfo> activityList;
	private List<ProcessDefinition> processDefinitions;
	private List<HistoricProcessInstance> hiProcessInstance;
	private List<Task> tasks;

	public String test() {
		return "test";
	}

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

	/**
	 * 流程跟踪图片生成
	 * 
	 * @return
	 */
	public String getAuditImage() {
		activityList = new ArrayList<ActivityInfo>();
		List<String> keyList = new ArrayList<String>();
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
			if (keyList.contains(key)) {
				int i = keyList.indexOf(key);
				ActivityInfo info = activityList.get(i);
				info.getWithdrawTasks().add(taskInstance);
				// 判断任务是否完成
				if (taskInstance.getEndTime() != null) {
					info.setState("withdraw");
				} else {
					info.setState("current");
				}
			} else {
				DiagramNode node = layout.get(key);
				activity.setTask(taskInstance);
				// 坐标修正
				activity.setX(node.getX() + 26);
				activity.setY(node.getY() + 47);
				activity.setWidth(node.getWidth());
				activity.setHeight(node.getHeight());
				activity.setState("complete");
				activityList.add(activity);
				keyList.add(key);
			}

		}

		return "auditImage";

	}

	/**
	 * 任务签收
	 */
	public void claim() {
		String userid = (String) this.request.getSession().getAttribute(
				"userid");
		processService.claimTask(taskid, userid);
	}

	/**
	 * 处理任务
	 * 
	 * @return
	 */
	public String completeTask() {
		// Map map = request.getParameterMap();
		// 根据businessKey不同，Null传入不同的VO对象
		processService.completeTask(taskid, null);
		return null;

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

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
}
