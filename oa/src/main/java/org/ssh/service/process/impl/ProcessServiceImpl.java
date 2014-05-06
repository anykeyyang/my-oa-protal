package org.ssh.service.process.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.parser.BpmnParser;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.DiagramNode;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.ssh.bean.process.ActHiTaskinst;
import org.ssh.dao.process.ProcessDao;
import org.ssh.service.process.ProcessService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service("processService")
@SuppressWarnings({ "rawtypes", "unchecked" })
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
	private ProcessDao processDao;

	@Override
	public List<ProcessDefinition> getProcessDefinitionActive() {
		return repositoryService.createProcessDefinitionQuery().active().list();
	}

	@Override
	public List<Task> getUserTask(String userName) {
		List<Task> userTasks = new ArrayList<Task>();
		// 已经分配的任务
		List<Task> tasksHasAssigned = taskService.createTaskQuery()
				.taskAssignee(userName).list();
		userTasks.addAll(tasksHasAssigned);
		// 分配到所属部门还没有被签收的任务
		List<Group> groupBelong = identityService.createGroupQuery()
				.groupMember(userName).list();

		List<String> groups = new ArrayList<String>();

		for (Group group : groupBelong) {
			groups.add(group.getId());
		}

		List<Task> tasksNeedtobeClaimed = taskService.createTaskQuery()
				.taskCandidateGroupIn(groups).taskUnassigned().list();
		userTasks.addAll(tasksNeedtobeClaimed);
		return userTasks;
	}

	@Override
	public List<Task> getTaskListActive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistoricProcessInstance> getHasActivedProcessByUser(
			String userId, String finishFlag) {
		List<HistoricProcessInstance> result = new ArrayList<HistoricProcessInstance>();
		if ("finish".equals(finishFlag)) {
			result = this.historyService.createHistoricProcessInstanceQuery()
					.finished().startedBy(userId).list();
		} else if ("unfinish".equals(finishFlag)) {
			result = this.historyService.createHistoricProcessInstanceQuery()
					.unfinished().startedBy(userId).list();
		} else {
			result = this.historyService.createHistoricProcessInstanceQuery()
					.startedBy(userId).list();
		}
		return result;
	}

	@Override
	public ProcessInstance startProcessInstance(String processDefinitionId,
			String businessKey, String userId) {
		Map map = new HashMap();
		map.put("owner", userId);
		this.identityService.setAuthenticatedUserId(userId);
		ProcessInstance instance = runtimeService.startProcessInstanceById(
				processDefinitionId, businessKey, map);
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
		this.repositoryService.deleteDeployment(deploymentId, true);
	}

	@Override
	public void claimTask(String taskId, String userId) {
		taskService.claim(taskId, userId);
	}

	@Override
	public Object getRederForm(String taskId) {

		return this.formService.getRenderedTaskForm(taskId);
	}

	@Override
	public TaskFormData getFormData(String taskId) {
		return this.formService.getTaskFormData(taskId);
	}

	@Override
	public <T> Boolean completeTask(String taskId, T t) {
		try {
			// dao存储t转换成 model
			Map variables = new HashMap();
			variables.put("deptLeaderPass", true);
			taskService.complete(taskId, variables);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public String getProcessImage(String processDefinitionId) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		// 获取流程图片
		InputStream is = this.repositoryService
				.getProcessDiagram(processDefinitionId);
		String auditImage = "";
		try {
			byte[] buff = new byte[1024];
			int rc = 0;
			while ((rc = is.read(buff, 0, 1024)) != -1) {
				os.write(buff, 0, rc);
			}
			auditImage = Base64.encodeBase64String(os.toByteArray());
			is.close();
		} catch (Exception e) {
		}

		return auditImage;
	}

	@Override
	public Map<String, DiagramNode> getDiagramLayout(String processDefinitionId) {
		InputStream is = this.repositoryService
				.getProcessModel(processDefinitionId);
		Document bpmnModel = parseXml(is);
		Map<String, DiagramNode> map = this
				.getElementBoundsFromBpmnDi(bpmnModel);
		return map;
	}

	@Override
	public List<HistoricTaskInstance> getTasksHasFinished(
			String processInstanceid) {
		List<HistoricTaskInstance> historyTasks = this.historyService
				.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceid).list();
		return historyTasks;
	}

	@Override
	public List<HistoricProcessInstance> getInstanceInvolvedUser(String userid) {
		return this.historyService.createHistoricProcessInstanceQuery()
				.involvedUser(userid).list();
	}

	/**
	 * 驳回流程
	 * 
	 * @param taskId
	 *            当前任务ID
	 * @param activityId
	 *            驳回节点ID
	 * @param variables
	 *            流程存储参数
	 * @throws Exception
	 */
	@Override
	public void backProcess(String taskId, String activityId,
			Map<String, Object> variables) throws Exception {
		if (StringUtils.isEmpty(activityId)) {
			throw new Exception("驳回目标节点ID为空！");
		}

		// 查找所有并行任务节点，同时驳回
		List<Task> taskList = findTaskListByKey(
				findProcessInstanceByTaskId(taskId).getId(),
				findTaskById(taskId).getTaskDefinitionKey());
		for (Task task : taskList) {
			commitProcess(task.getId(), variables, activityId);
		}
	}

	/**
	 * 根据当前任务ID，查询可以驳回的任务节点
	 * 
	 * @param taskId
	 *            当前任务ID
	 */
	@Override
	public List<ActivityImpl> findBackAvtivity(String taskId) throws Exception {
		List<ActivityImpl> rtnList = iteratorBackActivity(taskId,
				findActivitiImpl(taskId, null), new ArrayList<ActivityImpl>(),
				new ArrayList<ActivityImpl>());
		return reverList(rtnList);
	}

	/**
	 * 中止流程(特权人直接审批通过等)
	 * 
	 * @param taskId
	 */
	@Override
	public void endProcess(String taskId) throws Exception {
		ActivityImpl endActivity = findActivitiImpl(taskId, "end");
		commitProcess(taskId, null, endActivity.getId());
	}

	@Override
	public boolean cancelProcessInstance(String processInstanceid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean suspendProcessInstance(String processInstanceid) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 流程删除
	 * 
	 * @param processInstanceid
	 * @return
	 */
	@Override
	public boolean deleteProcessInstance(String processInstanceid) {
		return false;

	}

	/**
	 * 清空指定活动节点流向
	 * 
	 * @param activityImpl
	 *            活动节点
	 * @return 节点流向集合
	 */
	private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
		// 存储当前节点所有流向临时变量
		List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
		// 获取当前节点所有流向，存储到临时变量，然后清空
		List<PvmTransition> pvmTransitionList = activityImpl
				.getOutgoingTransitions();
		for (PvmTransition pvmTransition : pvmTransitionList) {
			oriPvmTransitionList.add(pvmTransition);
		}
		pvmTransitionList.clear();

		return oriPvmTransitionList;
	}

	/**
	 * @param taskId
	 *            当前任务ID
	 * @param variables
	 *            流程变量
	 * @param activityId
	 *            流程转向执行任务节点ID<br>
	 *            此参数为空，默认为提交操作
	 * @throws Exception
	 */
	private void commitProcess(String taskId, Map<String, Object> variables,
			String activityId) throws Exception {
		if (variables == null) {
			variables = new HashMap<String, Object>();
		}
		// 跳转节点为空，默认提交操作
		if (StringUtils.isEmpty(activityId)) {
			taskService.complete(taskId, variables);
		} else {// 流程转向操作
			turnTransition(taskId, activityId, variables);
		}
	}

	/**
	 * 根据流入任务集合，查询最近一次的流入任务节点
	 * 
	 * @param processInstance
	 *            流程实例
	 * @param tempList
	 *            流入任务集合
	 * @return
	 */
	private ActivityImpl filterNewestActivity(ProcessInstance processInstance,
			List<ActivityImpl> tempList) {
		while (tempList.size() > 0) {
			ActivityImpl activity_1 = tempList.get(0);
			HistoricActivityInstance activityInstance_1 = findHistoricUserTask(
					processInstance, activity_1.getId());
			if (activityInstance_1 == null) {
				tempList.remove(activity_1);
				continue;
			}

			if (tempList.size() > 1) {
				ActivityImpl activity_2 = tempList.get(1);
				HistoricActivityInstance activityInstance_2 = findHistoricUserTask(
						processInstance, activity_2.getId());
				if (activityInstance_2 == null) {
					tempList.remove(activity_2);
					continue;
				}

				if (activityInstance_1.getEndTime().before(
						activityInstance_2.getEndTime())) {
					tempList.remove(activity_1);
				} else {
					tempList.remove(activity_2);
				}
			} else {
				break;
			}
		}
		if (tempList.size() > 0) {
			return tempList.get(0);
		}
		return null;
	}

	/**
	 * 根据任务ID和节点ID获取活动节点 <br>
	 * 
	 * @param taskId
	 *            任务ID
	 * @param activityId
	 *            活动节点ID <br>
	 *            如果为null或""，则默认查询当前活动节点 <br>
	 *            如果为"end"，则查询结束节点 <br>
	 * 
	 * @return
	 * @throws Exception
	 */
	private ActivityImpl findActivitiImpl(String taskId, String activityId)
			throws Exception {
		// 取得流程定义
		ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);

		// 获取当前活动节点ID
		if (StringUtils.isEmpty(activityId)) {
			activityId = findTaskById(taskId).getTaskDefinitionKey();
		}

		// 根据流程定义，获取该流程实例的结束节点
		if (activityId.toUpperCase().equals("END")) {
			for (ActivityImpl activityImpl : processDefinition.getActivities()) {
				List<PvmTransition> pvmTransitionList = activityImpl
						.getOutgoingTransitions();
				if (pvmTransitionList.isEmpty()) {
					return activityImpl;
				}
			}
		}

		// 根据节点ID，获取对应的活动节点
		ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)
				.findActivity(activityId);

		return activityImpl;
	}

	/**
	 * 查询指定任务节点的最新记录
	 * 
	 * @param processInstance
	 *            流程实例
	 * @param activityId
	 * @return
	 */
	private HistoricActivityInstance findHistoricUserTask(
			ProcessInstance processInstance, String activityId) {
		HistoricActivityInstance rtnVal = null;
		// 查询当前流程实例审批结束的历史节点
		List<HistoricActivityInstance> historicActivityInstances = historyService
				.createHistoricActivityInstanceQuery().activityType("userTask")
				.processInstanceId(processInstance.getId())
				.activityId(activityId).finished()
				.orderByHistoricActivityInstanceEndTime().desc().list();
		if (historicActivityInstances.size() > 0) {
			rtnVal = historicActivityInstances.get(0);
		}

		return rtnVal;
	}

	/**
	 * 根据当前节点，查询输出流向是否为并行终点，如果为并行终点，则拼装对应的并行起点ID
	 * 
	 * @param activityImpl
	 *            当前节点
	 * @return
	 */
	private String findParallelGatewayId(ActivityImpl activityImpl) {
		List<PvmTransition> incomingTransitions = activityImpl
				.getOutgoingTransitions();
		for (PvmTransition pvmTransition : incomingTransitions) {
			TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;
			activityImpl = transitionImpl.getDestination();
			String type = (String) activityImpl.getProperty("type");
			if ("parallelGateway".equals(type)) {// 并行路线
				String gatewayId = activityImpl.getId();
				String gatewayType = gatewayId.substring(gatewayId
						.lastIndexOf("_") + 1);
				if ("END".equals(gatewayType.toUpperCase())) {
					return gatewayId.substring(0, gatewayId.lastIndexOf("_"))
							+ "_start";
				}
			}
		}
		return null;
	}

	/**
	 * 根据任务ID获取流程定义
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	public ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(
			String taskId) throws Exception {
		// 取得流程定义
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(findTaskById(taskId)
						.getProcessDefinitionId());

		if (processDefinition == null) {
			throw new Exception("流程定义未找到!");
		}

		return processDefinition;
	}

	/**
	 * 根据任务ID获取对应的流程实例
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	public ProcessInstance findProcessInstanceByTaskId(String taskId)
			throws Exception {
		// 找到流程实例
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()
				.processInstanceId(findTaskById(taskId).getProcessInstanceId())
				.singleResult();
		if (processInstance == null) {
			throw new Exception("流程实例未找到!");
		}
		return processInstance;
	}

	/**
	 * 根据任务ID获得任务实例
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	private TaskEntity findTaskById(String taskId) throws Exception {
		TaskEntity task = (TaskEntity) taskService.createTaskQuery()
				.taskId(taskId).singleResult();
		if (task == null) {
			throw new Exception("任务实例未找到!");
		}
		return task;
	}

	/**
	 * 根据流程实例ID和任务key值查询所有同级任务集合
	 * 
	 * @param processInstanceId
	 * @param key
	 * @return
	 */
	private List<Task> findTaskListByKey(String processInstanceId, String key) {
		return taskService.createTaskQuery()
				.processInstanceId(processInstanceId).taskDefinitionKey(key)
				.list();
	}

	/**
	 * 迭代循环流程树结构，查询当前节点可驳回的任务节点
	 * 
	 * @param taskId
	 *            当前任务ID
	 * @param currActivity
	 *            当前活动节点
	 * @param rtnList
	 *            存储回退节点集合
	 * @param tempList
	 *            临时存储节点集合（存储一次迭代过程中的同级userTask节点）
	 * @return 回退节点集合
	 */
	private List<ActivityImpl> iteratorBackActivity(String taskId,
			ActivityImpl currActivity, List<ActivityImpl> rtnList,
			List<ActivityImpl> tempList) throws Exception {
		// 查询流程定义，生成流程树结构
		ProcessInstance processInstance = findProcessInstanceByTaskId(taskId);

		// 当前节点的流入来源
		List<PvmTransition> incomingTransitions = currActivity
		// .getOutgoingTransitions()
				.getIncomingTransitions();
		// 条件分支节点集合，userTask节点遍历完毕，迭代遍历此集合，查询条件分支对应的userTask节点
		List<ActivityImpl> exclusiveGateways = new ArrayList<ActivityImpl>();
		// 并行节点集合，userTask节点遍历完毕，迭代遍历此集合，查询并行节点对应的userTask节点
		List<ActivityImpl> parallelGateways = new ArrayList<ActivityImpl>();
		// 遍历当前节点所有流入路径
		for (PvmTransition pvmTransition : incomingTransitions) {
			TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;
			ActivityImpl activityImpl = transitionImpl.getSource();
			String type = (String) activityImpl.getProperty("type");
			/**
			 * 并行节点配置要求：<br>
			 * 必须成对出现，且要求分别配置节点ID为:XXX_start(开始)，XXX_end(结束)
			 */
			if ("parallelGateway".equals(type)) {// 并行路线
				String gatewayId = activityImpl.getId();
				String gatewayType = gatewayId.substring(gatewayId
						.lastIndexOf("_") + 1);
				if ("START".equals(gatewayType.toUpperCase())) {// 并行起点，停止递归
					return rtnList;
				} else {// 并行终点，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点
					parallelGateways.add(activityImpl);
				}
			} else if ("startEvent".equals(type)) {// 开始节点，停止递归
				return rtnList;
			} else if ("userTask".equals(type)) {// 用户任务
				tempList.add(activityImpl);
			} else if ("exclusiveGateway".equals(type)) {// 分支路线，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点
				currActivity = transitionImpl.getSource();
				exclusiveGateways.add(currActivity);
			}
		}

		/**
		 * 迭代条件分支集合，查询对应的userTask节点
		 */
		for (ActivityImpl activityImpl : exclusiveGateways) {
			iteratorBackActivity(taskId, activityImpl, rtnList, tempList);
		}

		/**
		 * 迭代并行集合，查询对应的userTask节点
		 */
		for (ActivityImpl activityImpl : parallelGateways) {
			iteratorBackActivity(taskId, activityImpl, rtnList, tempList);
		}

		/**
		 * 根据同级userTask集合，过滤最近发生的节点
		 */
		currActivity = filterNewestActivity(processInstance, tempList);
		if (currActivity != null) {
			// 查询当前节点的流向是否为并行终点，并获取并行起点ID
			String id = findParallelGatewayId(currActivity);
			if (StringUtils.isEmpty(id)) {// 并行起点ID为空，此节点流向不是并行终点，符合驳回条件，存储此节点
				rtnList.add(currActivity);
			} else {// 根据并行起点ID查询当前节点，然后迭代查询其对应的userTask任务节点
				currActivity = findActivitiImpl(taskId, id);
			}

			// 清空本次迭代临时集合
			tempList.clear();
			// 执行下次迭代
			iteratorBackActivity(taskId, currActivity, rtnList, tempList);
		}
		return rtnList;
	}

	/**
	 * 还原指定活动节点流向
	 * 
	 * @param activityImpl
	 *            活动节点
	 * @param oriPvmTransitionList
	 *            原有节点流向集合
	 */
	private void restoreTransition(ActivityImpl activityImpl,
			List<PvmTransition> oriPvmTransitionList) {
		// 清空现有流向
		List<PvmTransition> pvmTransitionList = activityImpl
				.getOutgoingTransitions();
		pvmTransitionList.clear();
		// 还原以前流向
		for (PvmTransition pvmTransition : oriPvmTransitionList) {
			pvmTransitionList.add(pvmTransition);
		}
	}

	/**
	 * 反向排序list集合，便于驳回节点按顺序显示
	 * 
	 * @param list
	 * @return
	 */
	private List<ActivityImpl> reverList(List<ActivityImpl> list) {
		List<ActivityImpl> rtnList = new ArrayList<ActivityImpl>();
		// 由于迭代出现重复数据，排除重复
		for (int i = list.size(); i > 0; i--) {
			if (!rtnList.contains(list.get(i - 1)))
				rtnList.add(list.get(i - 1));
		}
		return rtnList;
	}

	/**
	 * 流程转向操作
	 * 
	 * @param taskId
	 *            当前任务ID
	 * @param activityId
	 *            目标节点任务ID
	 * @param variables
	 *            流程变量
	 * @throws Exception
	 */
	private void turnTransition(String taskId, String activityId,
			Map<String, Object> variables) throws Exception {
		// 当前节点
		ActivityImpl currActivity = findActivitiImpl(taskId, null);
		// 清空当前流向
		List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);

		// 创建新流向
		TransitionImpl newTransition = currActivity.createOutgoingTransition();
		// 目标节点
		ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);
		// 设置新流向的目标节点
		newTransition.setDestination(pointActivity);
		// 执行转向任务
		taskService.complete(taskId, variables);
		
		// 修改deleteReason
		ActHiTaskinst hitaskInstance = processDao.gethiTaskInstanceByid(taskId);
		hitaskInstance.setDeleteReason("withdraw");
		processDao.update(hitaskInstance);
		
		// 删除目标节点新流入
		pointActivity.getIncomingTransitions().remove(newTransition);

		// 还原以前流向
		restoreTransition(currActivity, oriPvmTransitionList);
	}

	/**
	 * 将inputstream 转化为dom
	 * 
	 * @param bpmnXmlStream
	 * @return
	 */
	private Document parseXml(InputStream bpmnXmlStream) {
		// Initiate DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// Get one that understands namespaces
		factory.setNamespaceAware(true);

		DocumentBuilder builder;
		Document bpmnModel;
		try {
			// Get DocumentBuilder
			builder = factory.newDocumentBuilder();
			// Parse and load the Document into memory
			bpmnModel = builder.parse(bpmnXmlStream);
		} catch (Exception e) {
			throw new ActivitiException("Error while parsing BPMN model.", e);
		}
		return bpmnModel;
	}

	private Map<String, DiagramNode> getElementBoundsFromBpmnDi(
			Document bpmnModel) {
		Map<String, DiagramNode> listOfBounds = new HashMap<String, DiagramNode>();
		// iterate over all DI shapes
		NodeList shapes = bpmnModel.getElementsByTagNameNS(
				BpmnParser.BPMN_DI_NS, "BPMNShape");
		for (int i = 0; i < shapes.getLength(); i++) {
			Element shape = (Element) shapes.item(i);
			String bpmnElementId = shape.getAttribute("bpmnElement");
			// get bounds of shape
			NodeList childNodes = shape.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node childNode = childNodes.item(j);
				if (childNode instanceof Element
						&& BpmnParser.BPMN_DC_NS.equals(childNode
								.getNamespaceURI())
						&& "Bounds".equals(childNode.getLocalName())) {
					DiagramNode bounds = parseBounds((Element) childNode);
					bounds.setId(bpmnElementId);
					listOfBounds.put(bpmnElementId, bounds);
					break;
				}
			}
		}
		return listOfBounds;
	}

	/**
	 * 获取坐标信息
	 * 
	 * @param boundsElement
	 * @return
	 */
	private DiagramNode parseBounds(Element boundsElement) {
		DiagramNode bounds = new DiagramNode();
		bounds.setX(Double.valueOf(boundsElement.getAttribute("x")));
		bounds.setY(Double.valueOf(boundsElement.getAttribute("y")));
		bounds.setWidth(Double.valueOf(boundsElement.getAttribute("width")));
		bounds.setHeight(Double.valueOf(boundsElement.getAttribute("height")));
		return bounds;
	}

}
