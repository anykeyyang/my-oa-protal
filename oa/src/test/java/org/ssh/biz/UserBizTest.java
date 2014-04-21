package org.ssh.biz;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ssh.service.IuserBiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class UserBizTest {
	@Resource
	private IuserBiz userBiz;

	@Test
	public void test() {

		try {
			System.out.println(userBiz.getListUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
