package org.ssh.dao.process;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.ssh.bean.TTaskDelegate;
import org.ssh.dao.BaseDao;

@Repository
public class TaskDelegateDao extends BaseDao<TTaskDelegate> {

	public TTaskDelegate getTaskDelegateInfo(String username) {
		TTaskDelegate result = new TTaskDelegate();
		Session session = this.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(TTaskDelegate.class);
		criteria.add(Restrictions.eq("userId", username));
		try {
			List<TTaskDelegate> delegateInfo = this.findByCriteria(criteria);
			if (delegateInfo.size()!=0) {
				result = delegateInfo.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		return result;
	}
}
