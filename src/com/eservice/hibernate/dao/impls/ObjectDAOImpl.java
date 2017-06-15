package com.eservice.hibernate.dao.impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eservice.core.daos.ObjectDAO;
import com.eservice.hibernate.utils.HibernateExceptionUtil;


@Repository("hibernateObjectDAO")
// Default is read only
@Transactional
public class  ObjectDAOImpl<E> implements ObjectDAO<E> {

	private Class<E> clazz;
	
	private SessionFactory sessionFactory;

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.hibernateTemplate = new HibernateTemplate(getSessionFactory());
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Map saveObject(E obj) {
		System.out.println("saveObject obj ="+obj.getClass().getSimpleName());
		Map resultMap = new HashMap();
		Transaction trns = null;
		try {
			System.out.println("saveObject getSessionFactory ="+getSessionFactory());
			System.out.println("saveObject getCurrentSession() ="+getSessionFactory().getCurrentSession());
			trns = getSessionFactory().getCurrentSession().beginTransaction();
			System.out.println("saveObject trns ="+trns);
			getHibernateTemplate().save(obj);
			trns.commit();
			
			resultMap.put(obj.getClass().getSimpleName(),obj);
		} catch (Exception ex) {
			resultMap.put("success", false);
			if (trns != null) {
				trns.rollback();
			}
			resultMap.put(obj.getClass().getSimpleName(),obj);
			if (HibernateExceptionUtil.isViolatedConstraint(ex))
				resultMap.put("message", HibernateExceptionUtil
						.getViolatedConstraintName(ex));
			else
				resultMap.put("message", ex.getCause().getMessage());
			return resultMap;
		}
		resultMap.put("success", true);
		resultMap.put("message", "Successfully saved");
		return resultMap;
	}

	public Map updateObject(E obj) {
		Map resultMap = new HashMap();
		Transaction trns = null;
		try {
			trns = getSessionFactory().getCurrentSession().beginTransaction();

			getHibernateTemplate().update(obj);
			trns.commit();
			resultMap.put(obj.getClass().getSimpleName(),obj);
		} catch (Exception ex) {
			resultMap.put("success", false);
			if (trns != null) {
				trns.rollback();
			}
			resultMap.put(obj.getClass().getSimpleName(),obj);
			if (HibernateExceptionUtil.isViolatedConstraint(ex))
				resultMap.put("message", HibernateExceptionUtil
						.getViolatedConstraintName(ex));
			else
				resultMap.put("message", ex.getCause().getMessage());
			return resultMap;
		}
		resultMap.put("success", true);
		resultMap.put("message", "Successfully updated");
		return resultMap;
	}

	public E selectObject(long id) throws Exception {
		return getHibernateTemplate().get(getClazz(), id);
	}
	@SuppressWarnings("unchecked")
	public List<E> selectObjects(String criteria,Object[] criteriaValues) throws Exception {
		
		List<E> objList =null;
		Transaction trns = getSessionFactory().getCurrentSession().beginTransaction();
		objList =  getHibernateTemplate().find(criteria,criteriaValues);
		trns.commit();
		if(objList!=null && objList.size()>0)
			return objList;
		else
			return new ArrayList<E>();
	}

	public void setClazz(Class<E> clazz) {
		this.clazz = clazz;
	}

	public Class<E> getClazz() {
		return clazz;
	}

	
}
