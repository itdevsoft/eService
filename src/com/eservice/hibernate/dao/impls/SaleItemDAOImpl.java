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

import com.eservice.core.beans.SaleItem;
import com.eservice.core.daos.SaleItemDAO;
import com.eservice.hibernate.utils.HibernateExceptionUtil;

@Repository("hibernateSaleItemDAO")
// Default is read only
@Transactional
public class SaleItemDAOImpl implements SaleItemDAO {

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

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Map saveSaleItem(SaleItem saleItem) {
		Map resultMap = new HashMap();
		Long id = 0L;
		Transaction trns = null;
		try {
			trns = getSessionFactory().getCurrentSession().beginTransaction();

			resultMap.put("id", id);
			id = (Long) getHibernateTemplate().save(saleItem);
			trns.commit();
			resultMap.put("saleItem",saleItem);
			resultMap.put("id",id);
		} catch (Exception ex) {
			resultMap.put("success", false);
			if (trns != null) {
				trns.rollback();
			}
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

	@Override
	public Map updateSaleItem(SaleItem saleItem) {
		Map resultMap = new HashMap();
		Transaction trns = null;
		try {
			trns = getSessionFactory().getCurrentSession().beginTransaction();

			getHibernateTemplate().update(saleItem);
			trns.commit();
			resultMap.put("saleItem",saleItem);
		} catch (Exception ex) {
			resultMap.put("success", false);
			if (trns != null) {
				trns.rollback();
			}
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

	@Override
	public SaleItem selectSaleItem(long id) throws Exception {
		return getHibernateTemplate().get(SaleItem.class, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SaleItem> selectSaleItems(String criteria,Object[] criteriaValues) throws Exception {
		
		List<SaleItem> saleItemList =null;
		Transaction trns = getSessionFactory().getCurrentSession().beginTransaction();
		saleItemList =  getHibernateTemplate().find(criteria,criteriaValues);
		trns.commit();
		if(saleItemList!=null && saleItemList.size()>0)
			return saleItemList;
		else
			return new ArrayList<SaleItem>();
	}

}
