package com.eservice.spring.dao.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eservice.core.beans.Category;
import com.eservice.core.beans.NestedCategory;
import com.eservice.core.daos.CategoryHierarchyDAO;

@Repository("springCategoryHierarchyDAO")
// Default is read only
@Transactional
public class CategoryHierarchyDAOImpl<T extends Object> implements CategoryHierarchyDAO<T> {

	
	private DriverManagerDataSource dataSource;
	
	

	@Autowired
	public void setDataSource(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DriverManagerDataSource getDataSource() {
		return dataSource;
	}

	@Override
	public List<NestedCategory> selectImediateCaregoryList(long categoryId) throws Exception{
		
		System.out.println(this.getClass().getName()+" --->selectImediateCaregoryList");
		
		StoredProcedure sproc = new StoredProcedure(){
			
		};
		sproc.setDataSource(this.getDataSource());
		sproc.setSql("get_immediate_subordinates");
		sproc.setFunction(true);
		sproc.declareParameter(new SqlReturnResultSet("record",new RowMapper<NestedCategory>(){
			public NestedCategory mapRow(ResultSet rs, int rowNum) throws SQLException{
				NestedCategory nestedCategory=new NestedCategory();
				Category category=new Category();
				nestedCategory.setId(rs.getLong("nested_category_id"));
				nestedCategory.setType(rs.getString("nested_category_type"));
				category.setId(rs.getLong("category_id"));
				category.setName(rs.getString("category_name"));
				category.setDiffFactor(rs.getInt("diff_factor"));
				category.setImagePath(rs.getString("image_path"));
				nestedCategory.setCategory(category);
				nestedCategory.setLeft(rs.getInt("category_lft"));
				nestedCategory.setRight(rs.getInt("category_rgt"));
				return nestedCategory;
				}
				} )); 
		sproc.declareParameter(new SqlParameter("catogoryId", Types.INTEGER));
		sproc.compile();
		Map inParams = new HashMap();
		inParams.put("catogoryId", categoryId);
		
		Map results = sproc.execute(inParams);
		System.out.println(this.getClass().getName()+"--->results="+results.size());
		
		List<NestedCategory> rptList = new ArrayList<NestedCategory>();
		if (results != null) {
			rptList = (List<NestedCategory>) results.get("record");
		}
		return rptList;

	}
	
	@Override
	public long insertNestedCaregory(NestedCategory nestedCategory) throws Exception{
		
		StoredProcedure sproc = new StoredProcedure(){
			
		};
		sproc.setDataSource(this.getDataSource());
		sproc.setSql("add_new_node");
		sproc.setFunction(true);
		sproc.declareParameter(new SqlOutParameter("record", Types.BIGINT));
		
		sproc.declareParameter(new SqlParameter("catogoryId", Types.INTEGER));
		sproc.declareParameter(new SqlParameter("parentId", Types.INTEGER));
		sproc.declareParameter(new SqlParameter("categoryName", Types.VARCHAR));
		sproc.declareParameter(new SqlParameter("diffFactor", Types.INTEGER));
		sproc.declareParameter(new SqlParameter("categoryType", Types.VARCHAR));
		sproc.declareParameter(new SqlParameter("imagePath", Types.VARCHAR));
		sproc.compile();
	
		Map inParams = new HashMap();
		
		System.out.format("catogoryId = %s , parentId = %s , categoryName = %s , diffFactor = %s ,categoryType = %s , imagePath = %s", nestedCategory.getId(),nestedCategory.getParentId(),nestedCategory.getCategory().getName(),nestedCategory.getCategory().getDiffFactor(),nestedCategory.getType(),nestedCategory.getCategory().getImagePath());
		
		inParams.put("catogoryId", nestedCategory.getId());
		inParams.put("parentId", nestedCategory.getParentId());
		inParams.put("categoryName", nestedCategory.getCategory().getName());
		inParams.put("diffFactor", nestedCategory.getCategory().getDiffFactor());
		inParams.put("categoryType", nestedCategory.getType());
		inParams.put("imagePath", nestedCategory.getCategory().getImagePath());
		long id=0L;
		Map results = sproc.execute(inParams);
		if (results != null) {
			id = (Long) results.get("record");
		}
		return id;

	}
	
	@Override
	public boolean deleteNestedCaregory(long categoryId,String categoryType) throws Exception{
		
		StoredProcedure sproc = new StoredProcedure(){
			
		};
		sproc.setDataSource(this.getDataSource());
		sproc.setSql("delete_nodes");
		sproc.setFunction(true);
		sproc.declareParameter(new SqlOutParameter("record", Types.BOOLEAN));
		
		sproc.declareParameter(new SqlParameter("catogoryId", Types.INTEGER));
		sproc.declareParameter(new SqlParameter("catogoryType", Types.VARCHAR));
		sproc.compile();
	
		Map inParams = new HashMap();
		
		inParams.put("catogoryId", categoryId);
		inParams.put("catogoryType", categoryType);
		boolean isInsert=false;
		Map results = sproc.execute(inParams);
		if (results != null) {
			isInsert = (Boolean) results.get("record");
		}
		return isInsert;

	}
	@Override
	public List<NestedCategory> selectSinglePathCaregoryList(long categoryId) throws Exception{
		
		StoredProcedure sproc = new StoredProcedure(){
			
		};
		sproc.setDataSource(this.getDataSource());
		sproc.setSql("get_single_path");
		sproc.setFunction(true);
		sproc.declareParameter(new SqlReturnResultSet("record",new RowMapper<NestedCategory>(){
			public NestedCategory mapRow(ResultSet rs, int rowNum) throws SQLException{
				NestedCategory nestedCategory=new NestedCategory();
				Category category=new Category();
				nestedCategory.setId(rs.getLong("nested_category_id"));
				nestedCategory.setType(rs.getString("nested_category_type"));
				category.setId(rs.getLong("category_id"));
				category.setName(rs.getString("category_name"));
				category.setDiffFactor(rs.getInt("diff_factor"));
				category.setImagePath(rs.getString("image_path"));
				nestedCategory.setCategory(category);
				nestedCategory.setLeft(rs.getInt("category_lft"));
				nestedCategory.setRight(rs.getInt("category_rgt"));
				return nestedCategory;
				}
				} )); 
		sproc.declareParameter(new SqlParameter("catogoryId", Types.INTEGER));
		sproc.compile();
		Map inParams = new HashMap();
		inParams.put("catogoryId", categoryId);
		
		Map results = sproc.execute(inParams);
		List<NestedCategory> rptList = new ArrayList<NestedCategory>();
		if (results != null) {
			rptList = (List<NestedCategory>) results.get("record");
		}
		
		return rptList;
	}

	@Override
	public Map updateCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> selectNestedCategoryItems(String criteria, Object[] criteriaValues) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteItemsFromNestedCategory(List<T> nestedCategoryItems) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveItemsToNestedCategory(List<T> nestedCategoryItems)throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<NestedCategory> selectNestedCategories(String criteria,Object[] criteriaValues) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NestedCategory selectNestedCategory(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setNestedCategoryItem(T nestedCategoryItem) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
