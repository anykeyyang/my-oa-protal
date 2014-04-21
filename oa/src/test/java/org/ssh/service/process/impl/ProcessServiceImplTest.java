package org.ssh.service.process.impl;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
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

	@Test
	public void testStartProcess() {
		ProcessInstance instance = processService.startProcessInstance(
				"leave:1:4", "kafeitu");
		assertNotNull(instance);
		System.out.println("id:" + instance.getProcessInstanceId());
	}

	//@Test
	public void testGetUserTask() {
		List<Task> tasks =processService.getUserTask("kafeitu");
		assertNotNull(tasks);
		System.out.println("tid:" + tasks.get(0).getId());
	}

}
