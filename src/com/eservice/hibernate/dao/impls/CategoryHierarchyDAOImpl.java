package com.eservice.hibernate.dao.impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eservice.core.beans.Category;
import com.eservice.core.beans.NestedCategory;
import com.eservice.core.daos.CategoryHierarchyDAO;
import com.eservice.hibernate.utils.HibernateExceptionUtil;

@Repository("hibernateCategoryHierarchyDAO")
// Default is read only
@Transactional
public class CategoryHierarchyDAOImpl<T> implements CategoryHierarchyDAO<T> {

	private Class<T> clazz;
	
	private SessionFactory sessionFactory;

	private HibernateTemplate hibernateTemplate;

	private DriverManagerDataSource dataSource;
	
	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

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

	@Autowired
	public void setDataSource(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DriverManagerDataSource getDataSource() {
		return dataSource;
	}

	@Override
	public NestedCategory selectNestedCategory(long id) throws Exception {
		return getHibernateTemplate().get(NestedCategory.class,id);
	}

	@Override
	public Map updateCategory(Category category) {
		Map resultMap = new HashMap();
		Transaction trns = null;
		try {
			trns = getSessionFactory().getCurrentSession().beginTransaction();

			getHibernateTemplate().update(category);
			trns.commit();
			resultMap.put("category",category);
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
	public List<T> selectNestedCategoryItems(String criteria, Object[] criteriaValues) throws Exception {
		List<T> nestedCategoryItemList =null;
		Transaction trns = getSessionFactory().getCurrentSession().beginTransaction();
		nestedCategoryItemList =  getHibernateTemplate().find(criteria,criteriaValues);
		trns.commit();
		getSessionFactory().getCurrentSession().close();
		if(nestedCategoryItemList!=null && nestedCategoryItemList.size()>0)
			return nestedCategoryItemList;
		else
			return new ArrayList<T>();
	}

	@Override
	public boolean deleteItemsFromNestedCategory(List<T> nestedCategoryItems) throws Exception {
		Transaction trns = getSessionFactory().getCurrentSession().beginTransaction();
		getHibernateTemplate().deleteAll(nestedCategoryItems);
		trns.commit();
		getSessionFactory().getCurrentSession().close();
		return true;
	}

	@Override
	public boolean saveItemsToNestedCategory(List<T> nestedCategoryItems)throws Exception {
		Transaction trns = getSessionFactory().getCurrentSession().beginTransaction();
		int i=0;
		for (T nestedCategoryItem: nestedCategoryItems) {
			getHibernateTemplate().save(nestedCategoryItem);
		}
		trns.commit();
		return true;
	}

	@Override
	public List<NestedCategory> selectNestedCategories(String criteria,Object[] criteriaValues) throws Exception {
		
		List<NestedCategory> nestedCategoryList =null;
		Transaction trns = getSessionFactory().getCurrentSession().beginTransaction();
		nestedCategoryList =  getHibernateTemplate().find(criteria,criteriaValues);
		trns.commit();
		if(nestedCategoryList!=null && nestedCategoryList.size()>0)
			return nestedCategoryList;
		else
			return new ArrayList<NestedCategory>();
	}
	
	@Override
	public long insertNestedCaregory(NestedCategory nestedCategory) {
		// TODO Auto-generated method stub
		return 0L;
	}

	@Override
	public List<NestedCategory> selectImediateCaregoryList(long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NestedCategory> selectSinglePathCaregoryList(long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteNestedCaregory(long categoryId,String categoryType) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setNestedCategoryItem(T nestedCategoryItem){
		Transaction trns = null;
		try {
			trns = getSessionFactory().getCurrentSession().beginTransaction();

			getHibernateTemplate().saveOrUpdate(nestedCategoryItem);
			trns.commit();
		} catch (Exception ex) {
			if (trns != null) {
				trns.rollback();
			}
			return false;
		}
		return true;
	}
}
