package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductOrderModel {

	public void doSave(ProductOrderBean prodotti) throws SQLException;
	
	public Collection <ProductOrderBean> doRetrieveByNumOrdine(String numeroOrdine) throws SQLException;
}
