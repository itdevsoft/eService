package com.eservice.core.daos;

import java.util.List;
import java.util.Map;

import com.eservice.core.beans.SaleItem;

public interface SaleItemDAO {
	public Map saveSaleItem(SaleItem saleItem);

	public Map updateSaleItem(SaleItem saleItem);

	public List<SaleItem> selectSaleItems(String criteria,Object[] criteriaValues) throws Exception;

	public SaleItem selectSaleItem(long id) throws Exception;
}
