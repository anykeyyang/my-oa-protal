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
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.bpmn.parser.BpmnParser;
import org.activiti.engine.repository.DiagramLayout;
import org.activiti.engine.repository.DiagramNode;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Base64InputStream;
import org.springframework.stereotype.Service;
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
	public Boolean completeTask(String taskId, Map map) {
		try {
			taskService.complete(taskId, map);
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

	public Document parseXml(InputStream bpmnXmlStream) {
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

	public Map<String, DiagramNode> getElementBoundsFromBpmnDi(
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

	public DiagramNode parseBounds(Element boundsElement) {
		DiagramNode bounds = new DiagramNode();
		bounds.setX(Double.valueOf(boundsElement.getAttribute("x")));
		bounds.setY(Double.valueOf(boundsElement.getAttribute("y")));
		bounds.setWidth(Double.valueOf(boundsElement.getAttribute("width")));
		bounds.setHeight(Double.valueOf(boundsElement.getAttribute("height")));
		return bounds;
	}
}
