package com.eservice.core.services;

import java.util.List;
import java.util.Map;

import com.eservice.core.beans.Category;
import com.eservice.core.beans.Item;
import com.eservice.core.beans.NestedCategory;
import com.eservice.core.beans.NestedCategoryItem;
import com.eservice.core.beans.NestedCategorySaleItem;
import com.eservice.core.beans.SaleItem;


public interface SystemSetUpService {

	public NestedCategory getNestedCategory(long id) throws Exception;
	
	public boolean setNestedCategoryItem(NestedCategoryItem nestedCategoryItem);
	
	public List<NestedCategory> getNestedCategories(String criteria,Object[] criteriaValues) throws Exception;
	
	public List<NestedCategoryItem> getNestedCategoryItems(String criteria,Object[] criteriaValues) throws Exception;
	
	public List<NestedCategory> getImediateCaregoryList(long categoryId) throws Exception;
	
	public List<NestedCategory> getSinglePathCaregoryList(long categoryId) throws Exception;
	
	public long addNestedCaregory(NestedCategory nestedCategory) throws Exception;
	
	public boolean deleteNestedCaregory(long categoryId,String categoryType) throws Exception;

	public Map updateCategory(Category category);

	public boolean assignItemsToNestedCategory(NestedCategory nestedCategory,List<Item> items)throws Exception;

	public boolean removeItemsFromNestedCategory(List<NestedCategoryItem> nestedCategoryItems)throws Exception;
	
	public Map addSaleItem(SaleItem saleItem) throws Exception;
	
	public SaleItem getSaleItem(long id) throws Exception;
	
	public Map updateSaleItem(SaleItem saleItem) throws Exception;
	
	public List<SaleItem> getSaleItems(String criteria, Object[] criteriaValues) throws Exception;
	
	public List<SaleItem> setSaleItems(String criteria,Object[] criteriaValues) throws Exception;
	
	public boolean setNestedCategorySaleItem(NestedCategorySaleItem nestedCategorySaleItem) throws Exception;
	
	public List<NestedCategorySaleItem> getNestedCategorySaleItems(String criteria,Object[] criteriaValues) throws Exception;
	
	public boolean assignSaleItemsToNestedCategory(NestedCategory nestedCategory,List<SaleItem> saleItems)throws Exception;
}
