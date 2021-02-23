package it.unisa.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface ProductModel{
	
	public ProductBean doRetrieveByKey(String codice) throws SQLException;
		
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException, IOException;
	
	public void doSave(ProductBean prodotto) throws SQLException;
	
	public void doUpdate(ProductBean prodotto) throws SQLException;

	public void doDelete(ProductBean prodotto) throws SQLException;
	
	public Collection<ProductBean> doRetrieveAllByKey(String codice) throws SQLException,IOException;
	
	public Collection<ProductBean> getProdotti(String carattere) throws SQLException;
	
}
