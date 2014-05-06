package org.ssh.dao.process;

import org.springframework.stereotype.Repository;
import org.ssh.bean.process.ActHiTaskinst;
import org.ssh.dao.BaseDao;

@Repository
public class ProcessDao extends BaseDao<ActHiTaskinst> {

	public boolean updateHiTaskInstance(ActHiTaskinst t) {
		this.update(t);
		return true;

	}

	public ActHiTaskinst gethiTaskInstanceByid(String taskid) {
		try {
			return this.get(taskid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
