package org.ssh.service.process.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.DiagramLayout;
import org.activiti.engine.repository.DiagramNode;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.ssh.service.process.ProcessService;
import org.ssh.util.BusinessKey;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-test.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class ProcessServiceImplTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	private ProcessService processService;

	// @Test
	public void testGetProcessDefinitionActive() {
		assertNotNull(processService);
		List<ProcessDefinition> result = processService
				.getProcessDefinitionActive();
		for (ProcessDefinition pdf : result) {
			System.out.println("key:" + pdf.getKey());
			System.out.println("pdid:" + pdf.getId());

		}
	}

	// @Test
	public void testStartProcess() {
		ProcessInstance instance = processService.startProcessInstance(
				"leave:1:4", BusinessKey.PROCESS_LEAVE, "kafeitu");
		assertNotNull(instance);
		System.out.println("id:" + instance.getProcessInstanceId());
	}

	// @Test
	public void testGetUserTask() {
		List<Task> tasks = processService.getUserTask("leaderuser");
		assertNotNull(tasks);
		for (Task task : tasks) {
			System.out.println("tid:" + task.getId());
			System.out.println("name:" + task.getName());
		}

	}

	 @Test
	public void testGetDiagram() {
		// leave:1:4
		Map<String, DiagramNode> layout = processService
				.getDiagramLayout("leave:1:4");
		assertNotNull(layout);
		Iterator it = layout.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next().toString();
			DiagramNode node = layout.get(key);
			System.out.println(node.getId());
			System.out.println(node.getX());
			System.out.println(node.getY());
		}
	}

	// @Test
	public void testGetAuditImage() {
		String image = processService.getProcessImage("leave:1:4");
		System.out.println(image);
	}

	// @Test
	public void testGetProcessInstance() {
		List<HistoricProcessInstance> list = processService
				.getInstanceInvolvedUser("kafeitu");
		assertNotNull(list);
		System.out.println(list.size());
		for (HistoricProcessInstance hiinstance : list) {
			System.out.println(hiinstance.getBusinessKey());
		}
	}

	//@Test
	public void testGetHiTaskInstance() {
		List<HistoricTaskInstance> tasks = processService
				.getTasksHasFinished("1101");
		assertNotNull(tasks);
		for (HistoricTaskInstance task : tasks) {
			System.out.println(task.getTaskDefinitionKey());
		}
	}

}
