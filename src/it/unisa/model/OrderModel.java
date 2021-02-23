package it.unisa.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface OrderModel{

	public OrderBean doRetrieveByKey(String numeroOrdine) throws SQLException;

	public Collection<OrderBean> doRetrieveAll(String numeroOrdine) throws SQLException, IOException;
	
	public void doSave(OrderBean Ordine) throws SQLException;
	
	public void doDelete(OrderBean Ordine) throws SQLException;

	OrderBean doRetrieveByMail(String email) throws SQLException;
	
	public Collection<OrderBean> doRetrieveAllAdmin() throws SQLException;

}
