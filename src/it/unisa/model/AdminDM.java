package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class AdminDM implements Admin {
	
	private static DataSource ds = null;
	@SuppressWarnings("static-access")
	public AdminDM(DataSource ds) {
		this.ds = ds;
		
		System.out.println("DataSource Product Model creation....");
	}
	
	
	public AdminBean Login(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement ps= null;	
		AdminBean bean = new AdminBean();
		
		String sql = "SELECT * FROM admin WHERE username=?";
		
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(sql);
			
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
			}
		
	
		
	} finally {
		try {
			if(ps != null) 
				ps.close();
		} finally {
			if(connection != null)
			connection.close();
		}
	}
		return bean;
	}
}
