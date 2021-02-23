package it.unisa.model;

import java.sql.SQLException;

public interface Admin {

	public AdminBean Login(String username) throws SQLException;
}
