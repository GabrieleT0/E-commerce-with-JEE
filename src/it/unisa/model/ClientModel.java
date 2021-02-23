package it.unisa.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface ClientModel {

	public Collection<ClientBean> doRetrieveClient (String order) throws SQLException, IOException;
	
	public void Registration(ClientBean cliente) throws SQLException;
	
	public void doDeleteAccount (ClientBean cliente) throws SQLException;

	ClientBean doRetrieveByMailPsw(String email, String password) throws SQLException;

	Collection<ClientBean> doRetrieveClient() throws SQLException, IOException;
	
	public ClientBean doRetrieveByMail(String email) throws SQLException;

	public void doUpdate(ClientBean cliente)throws SQLException;


}


