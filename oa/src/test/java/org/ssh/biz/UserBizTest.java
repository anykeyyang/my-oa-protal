package org.ssh.biz;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class UserBizTest {
	@Resource
	private IuserBiz userBiz;

	@Test
	public void test() {

		System.out.println(userBiz.getListUser());
	}

}
