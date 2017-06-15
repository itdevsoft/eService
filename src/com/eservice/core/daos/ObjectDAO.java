package com.eservice.core.daos;

import java.util.List;
import java.util.Map;


public interface ObjectDAO<E extends Object> {
	
	public Map saveObject(E  object);
	public Map updateObject(E object);

	public List<E> selectObjects(String criteria,Object[] criteriaValues) throws Exception;

	public E selectObject(long id) throws Exception;
}
