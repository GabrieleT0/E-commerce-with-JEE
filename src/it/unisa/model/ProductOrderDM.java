package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class ProductOrderDM implements ProductOrderModel {
	
	private static DataSource ds = null;
	@SuppressWarnings("static-access")
	public ProductOrderDM(DataSource ds) {
		this.ds = ds;
		
		System.out.println("DataSource Product Order Model creation....");
	}
	
	public void doSave(ProductOrderBean prodotti) throws SQLException {
		Connection connection = null;
		PreparedStatement ps= null;	
		
		String sql = "INSERT INTO composto" +
				"(codice,numeroOrdine) VALUES (?, ?)";
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(sql);
			
			ps.setString(1, prodotti.getCodice());
			ps.setString(2, prodotti.getNumeroOrdine());
			
			ps.executeUpdate();
			
			
		} finally {
			try {
				if(ps != null) 
					ps.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}		
		
	}
	
	public Collection <ProductOrderBean> doRetrieveByNumOrdine(String numeroOrdine) throws SQLException {
		Connection connection = null;
		PreparedStatement ps= null;	

		
		Collection<ProductOrderBean>prodottiOrdine= new LinkedList<ProductOrderBean>();
		
		String sql = "SELECT * FROM composto WHERE numeroOrdine=?";
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(sql);
			
			ps.setString(1,numeroOrdine);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductOrderBean bean = new ProductOrderBean();
				bean.setNumeroOrdine(rs.getString("numeroOrdine"));
				bean.setCodice(rs.getString("codice"));
				prodottiOrdine.add(bean);
			}
			
			
		} finally {
			try {
				if(ps != null) 
					ps.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}		
		
		return prodottiOrdine ;
	}
	

	
	

}
