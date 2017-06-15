package com.eservice.hibernate.service.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eservice.core.beans.Category;
import com.eservice.core.beans.Item;
import com.eservice.core.beans.NestedCategory;
import com.eservice.core.beans.NestedCategoryItem;
import com.eservice.core.beans.NestedCategorySaleItem;
import com.eservice.core.beans.SaleItem;
import com.eservice.core.daos.CategoryHierarchyDAO;
import com.eservice.core.daos.SaleItemDAO;
import com.eservice.core.services.SystemSetUpService;
@Repository("hibernateSystemSetUpService")
public class SystemSetUpServiceImpl implements SystemSetUpService {

	
	
	@Autowired
	private CategoryHierarchyDAO hibernateCategoryHierarchyDAO;
	
	@Autowired
	private SaleItemDAO hibernateSaleItemDAO;
	
	public void setHibernateCategoryHierarchyDAO(CategoryHierarchyDAO hibernateCategoryHierarchyDAO) {
		this.hibernateCategoryHierarchyDAO = hibernateCategoryHierarchyDAO;
	}

	public CategoryHierarchyDAO getHibernateCategoryHierarchyDAO() {
		return hibernateCategoryHierarchyDAO;
	}
	
	public SaleItemDAO getHibernateSaleItemDAO() {
		return hibernateSaleItemDAO;
	}

	public void setHibernateSaleItemDAO(SaleItemDAO hibernateSaleItemDAO) {
		this.hibernateSaleItemDAO = hibernateSaleItemDAO;
	}

	@Override
	public boolean setNestedCategoryItem(NestedCategoryItem nestedCategoryItem) {
		return getHibernateCategoryHierarchyDAO().setNestedCategoryItem(nestedCategoryItem);
	}

	
	@Override
	public Map updateCategory(Category category) {
		return getHibernateCategoryHierarchyDAO().updateCategory(category);
	}
	@Override
	public List<NestedCategoryItem> getNestedCategoryItems(String criteria, Object[] criteriaValues) throws Exception {
		return getHibernateCategoryHierarchyDAO().selectNestedCategoryItems(criteria,criteriaValues);
	}

	@Override
	public long addNestedCaregory(NestedCategory nestedCategory) {
		// TODO Auto-generated method stub
		return 0L;
	}

	@Override
	public List<NestedCategory> getImediateCaregoryList(long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NestedCategory> getSinglePathCaregoryList(long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteNestedCaregory(long categoryId,String categoryType) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean assignItemsToNestedCategory(NestedCategory nestedCategory, List<Item> items)	throws Exception {
		// delete the existing assign items
		System.out.println("assignItemsToNestedCategory-->nestedCategory="+nestedCategory.getId()+"--items="+items.size());
		String criteria="from NestedCategoryItem n where n.compoundKey.nestedCategory.id=?";
		Object[] criteriaValues={nestedCategory.getId()};
		removeItemsFromNestedCategory(getNestedCategoryItems(criteria,criteriaValues));
		//insert the new items
		List<NestedCategoryItem> nestedCategoryItems =new ArrayList<NestedCategoryItem>();
		for(Item item:items){
			if(item.isAssigned()){
				NestedCategoryItem nestedCategoryItem=new NestedCategoryItem();
				nestedCategoryItem.setItem(item);
				nestedCategoryItem.setNestedCategory(nestedCategory);
				nestedCategoryItems.add(nestedCategoryItem);
			}
			
		}
		return getHibernateCategoryHierarchyDAO().saveItemsToNestedCategory(nestedCategoryItems);
	}

	@Override
	public boolean removeItemsFromNestedCategory(List<NestedCategoryItem> nestedCategoryItems) throws Exception {
		return getHibernateCategoryHierarchyDAO().deleteItemsFromNestedCategory(nestedCategoryItems);
	}

	@Override
	public List<NestedCategory> getNestedCategories(String criteria,Object[] criteriaValues) throws Exception {
		return getHibernateCategoryHierarchyDAO().selectNestedCategories(criteria, criteriaValues);
	}

	@Override
	public NestedCategory getNestedCategory(long id) throws Exception {
		return getHibernateCategoryHierarchyDAO().selectNestedCategory(id);
	}

	@Override
	public Map addSaleItem(SaleItem saleItem) throws Exception {
		return getHibernateSaleItemDAO().saveSaleItem(saleItem);
	}

	@Override
	public SaleItem getSaleItem(long id) throws Exception {
		return getHibernateSaleItemDAO().selectSaleItem(id);
	}

	@Override
	public Map updateSaleItem(SaleItem saleItem) throws Exception {
		return getHibernateSaleItemDAO().updateSaleItem(saleItem);
	}

	@Override
	public List<SaleItem> getSaleItems(String criteria, Object[] criteriaValues) throws Exception {
		return getHibernateSaleItemDAO().selectSaleItems(criteria, criteriaValues);
	}

	@Override
	public List<SaleItem> setSaleItems(String criteria, Object[] criteriaValues) throws Exception {
		return getHibernateSaleItemDAO().selectSaleItems(criteria, criteriaValues);
	}

	@Override
	public boolean setNestedCategorySaleItem(NestedCategorySaleItem nestedCategorySaleItem) throws Exception {
		return getHibernateCategoryHierarchyDAO().setNestedCategoryItem(nestedCategorySaleItem);
	}

	@Override
	public List<NestedCategorySaleItem> getNestedCategorySaleItems(String criteria, Object[] criteriaValues) throws Exception {
		return getHibernateCategoryHierarchyDAO().selectNestedCategoryItems(criteria, criteriaValues);
	}

	@Override
	public boolean assignSaleItemsToNestedCategory(NestedCategory nestedCategory, List<SaleItem> saleItems)	throws Exception {
		return false;
	}

	
	

}
