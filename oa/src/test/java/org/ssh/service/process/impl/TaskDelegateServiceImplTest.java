package org.ssh.service.process.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.ssh.bean.TTaskDelegate;
import org.ssh.service.process.TaskDelegateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-test.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class TaskDelegateServiceImplTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	private TaskDelegateService taskDelegateService;

	@Test
	public void testGetTaskDelegateInfo() {
		TTaskDelegate result = taskDelegateService
				.getTaskDelegateInfo("kafeitu");
		System.out.println(result.getDelegateUser());
	}

}
