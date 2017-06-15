package com.eservice.spring.service.impls;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eservice.core.beans.Category;
import com.eservice.core.beans.ItemType;
import com.eservice.core.beans.NestedCategory;
import com.eservice.core.beans.NestedCategoryItem;
import com.eservice.core.beans.Item;
import com.eservice.core.beans.NestedCategorySaleItem;
import com.eservice.core.beans.SaleItem;
import com.eservice.core.daos.CategoryHierarchyDAO;
import com.eservice.core.services.SystemSetUpService;
@Repository("springSystemSetUpService")
public class SystemSetUpServiceImpl implements SystemSetUpService {

	
	@Autowired
	private CategoryHierarchyDAO springCategoryHierarchyDAO;
	
	
	public void setSpringCategoryHierarchyDAO(CategoryHierarchyDAO springCategoryHierarchyDAO) {
		this.springCategoryHierarchyDAO = springCategoryHierarchyDAO;
	}

	public CategoryHierarchyDAO getSpringCategoryHierarchyDAO() {
		return springCategoryHierarchyDAO;
	}

	@Override
	public List<NestedCategory> getImediateCaregoryList(long categoryId) throws Exception{
		System.out.println(this.getClass().getName()+"--->getImediateCaregoryList");
		return getSpringCategoryHierarchyDAO().selectImediateCaregoryList(categoryId);
	}
	@Override
	public List<NestedCategory> getSinglePathCaregoryList(long categoryId) throws Exception{
		return getSpringCategoryHierarchyDAO().selectSinglePathCaregoryList(categoryId);
	}

	@Override
	public long addNestedCaregory(NestedCategory nestedCategory) throws Exception{
		return getSpringCategoryHierarchyDAO().insertNestedCaregory(nestedCategory);
	}

	@Override
	public boolean deleteNestedCaregory(long categoryId,String categoryType) throws Exception {
		return getSpringCategoryHierarchyDAO().deleteNestedCaregory(categoryId,categoryType);
	}

	
	@Override
	public Map updateCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<NestedCategoryItem> getNestedCategoryItems(String criteria, Object[] criteriaValues) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean assignItemsToNestedCategory(NestedCategory nestedCategory, List<Item> items) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeItemsFromNestedCategory(List<NestedCategoryItem> nestedCategoryItems) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<NestedCategory> getNestedCategories(String criteria,Object[] criteriaValues) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NestedCategory getNestedCategory(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public boolean setNestedCategoryItem(NestedCategoryItem nestedCategoryItem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map addSaleItem(SaleItem saleItem) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleItem getSaleItem(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map updateSaleItem(SaleItem saleItem) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleItem> getSaleItems(String criteria, Object[] criteriaValues)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleItem> setSaleItems(String criteria, Object[] criteriaValues)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setNestedCategorySaleItem(
			NestedCategorySaleItem nestedCategorySaleItem) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<NestedCategorySaleItem> getNestedCategorySaleItems(
			String criteria, Object[] criteriaValues) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean assignSaleItemsToNestedCategory(NestedCategory nestedCategory, List<SaleItem> saleItems)	throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	
}
