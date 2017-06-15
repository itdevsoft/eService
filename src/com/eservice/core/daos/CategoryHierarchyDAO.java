package com.eservice.core.daos;

import java.util.List;
import java.util.Map;

import com.eservice.core.beans.Category;
import com.eservice.core.beans.NestedCategory;

public interface CategoryHierarchyDAO<T extends Object> {

	public List<NestedCategory> selectImediateCaregoryList(long categoryId) throws Exception;
	public List<NestedCategory> selectSinglePathCaregoryList(long categoryId) throws Exception;
	public long insertNestedCaregory(NestedCategory nestedCategory) throws Exception;
	public boolean deleteNestedCaregory(long categoryId,String categoryType) throws Exception;
	public Map updateCategory(Category category);
	public List<T> selectNestedCategoryItems(String criteria, Object[] criteriaValues) throws Exception;
	public boolean deleteItemsFromNestedCategory(List<T> nestedCategoryItems) throws Exception;
	public boolean saveItemsToNestedCategory(List<T> nestedCategoryItems) throws Exception;
	public List<NestedCategory> selectNestedCategories(String criteria,Object[] criteriaValues) throws Exception;
	public NestedCategory selectNestedCategory(long id) throws Exception;
	public boolean setNestedCategoryItem(T nestedCategoryItem);
}
