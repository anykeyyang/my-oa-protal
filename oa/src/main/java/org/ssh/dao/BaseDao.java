package org.ssh.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.ssh.vo.Page;

/**
 * 基于hibernate的BaseDao
 * Spring3对Hibernate4已经没有了HibernateDaoSupport和HibernateTemplate的支持，使用了原生态的API
 * 
 * 
 * 
 * @param <T>
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class BaseDao<T extends Serializable> {
	@Resource
	private SessionFactory sessionFactory;
	// 当前泛型类
	private Class entityClass;
	// 当前主键名称
	private String pkname;
	private final String HQL_LIST_ALL;
	private final String HQL_COUNT_ALL;

	public Class getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

	public BaseDao() {
		// 获取当前泛型类
		Type type = this.getClass().getGenericSuperclass();
		if (type.toString().indexOf("BaseDao") != -1) {
			ParameterizedType type1 = (ParameterizedType) type;
			Type[] types = type1.getActualTypeArguments();
			setEntityClass((Class) types[0]);
		} else {
			type = ((Class) type).getGenericSuperclass();
			ParameterizedType type1 = (ParameterizedType) type;
			Type[] types = type1.getActualTypeArguments();
			setEntityClass((Class) types[0]);
		}
		getPkname();
		HQL_LIST_ALL = "from " + this.entityClass.getSimpleName()
				+ " order by " + pkname + " desc";
		HQL_COUNT_ALL = "select count(*) from "
				+ this.entityClass.getSimpleName();
	}

	/**
	 * 获取session
	 * 
	 * @return
	 */
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * 获取主键名称
	 * 
	 * @return
	 */
	public String getPkname() {
		Field[] fields = this.entityClass.getDeclaredFields();// 反射类字段
		for (Field field : fields) {
			if (field.isAnnotationPresent(Id.class)) {
				this.pkname = field.getName();
				break;
			}
		}
		return pkname;
	}

	/**
	 * 保存实例
	 * 
	 * @param t
	 * @throws HibernateException
	 */
	public void save(T t) throws HibernateException {
		Session session = null;
		try {
			session = getCurrentSession();
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new HibernateException(e);
		} 
	}

	/**
	 * 修改实例
	 * 
	 * @param t
	 * @throws HibernateException
	 */
	public void update(T t) throws HibernateException {
		Session session = null;
		try {
			session = getCurrentSession();
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new HibernateException(e);
		} 
	}

	/**
	 * 删除实例
	 * 
	 * @param t
	 * @throws HibernateException
	 */
	public void delete(T t) throws HibernateException {
		Session session = null;
		try {
			session = getCurrentSession();
			session.beginTransaction();
			session.delete(t);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new HibernateException(e);
		}
	}

	/**
	 * 获取实例
	 * 
	 * @param id
	 * @throws HibernateException
	 */
	public T get(Serializable id) throws Exception {
		Session session = null;
		T t = null;
		try {
			session = getCurrentSession();
			session.beginTransaction();
			t = (T) session.get(getEntityClass(), id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new HibernateException(e);
		} 
		return t;
	}

	/**
	 * 查询全部
	 * 
	 * @throws HibernateException
	 */
	public List<T> findAll() throws Exception {
		List<T> list = null;
		Session session = null;
		try {
			session = getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(HQL_LIST_ALL);
			list = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return list;
	}

	/**
	 * 查询总数
	 * 
	 * @throws HibernateException
	 */
	public Integer findAllCount() throws Exception {
		Session session = null;
		Integer count = 0;
		try {
			session = getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(HQL_COUNT_ALL);
			List<?> list = query.list();
			session.getTransaction().commit();
			if (list != null && !list.isEmpty()) {
				count = ((Integer) list.get(0)).intValue();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return count;
	}

	/**
	 * QBC查询
	 * 
	 * @param criteria
	 * @throws HibernateException
	 */
	public List<T> findByCriteria(Criteria criteria) throws Exception {
		List<T> list = null;
		try {
			list = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return list;
	}

	/**
	 * QBE查询
	 * 
	 * @param t
	 * @throws HibernateException
	 */
	public List<T> findByExample(T t) throws Exception {
		List<T> list = null;
		Session session = null;
		Example example = Example.create(t);
		try {
			session = getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(getEntityClass());
			criteria.add(example);
			list = criteria.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return list;
	}

	/**
	 * HQL查询
	 * 
	 * @param hql
	 * @param objects
	 * @throws HibernateException
	 */
	public List<Object[]> findByHql(String hql, final Object... objects)
			throws Exception {
		List<Object[]> list = null;
		Session session = null;
		try {
			session = getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(hql);
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	/**
	 * SQL查询
	 * 
	 * @param hql
	 * @param objects
	 * @throws HibernateException
	 */
	public List<Object[]> findBySql(String sql, final Object... objects) {
		List<Object[]> list = null;
		Session session = null;
		try {
			session = getCurrentSession();
			session.beginTransaction();
			Query query = session.createSQLQuery(sql);
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	/**
	 * 分页查询
	 * 
	 * @param criteria
	 * @param page
	 */
	public Page<T> findByPage(Criteria criteria, Page<T> page) {
		// 总记录 获取后取消 投影
		Integer totalCount = Integer.valueOf(criteria
				.setProjection(Projections.rowCount()).uniqueResult()
				.toString());
		criteria.setProjection(null);
		// 查询分页信息
		criteria.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
		criteria.setMaxResults(page.getPageSize());
		page.setTotalcount(totalCount);
		page.setResult(criteria.list());
		return page;
	}
}
