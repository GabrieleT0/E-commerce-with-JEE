package it.unisa.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class OrderModelDM implements OrderModel{
	
	private static DataSource ds = null;
	@SuppressWarnings("static-access")
	public OrderModelDM(DataSource ds) {
		this.ds = ds;
		
		System.out.println("DataSource Order Model creation....");
	}

	@Override
	public OrderBean doRetrieveByMail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement ps= null;	
		OrderBean bean = new OrderBean();
		
		String sql = "SELECT numeroOrdine FROM Ordine WHERE email=?";
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(sql);
			ps.setString(1,email);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				bean.setNumeroOrdine(rs.getString("numeroOrdine"));
					
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
		
		return bean;
	}

	@Override
	public Collection<OrderBean> doRetrieveAll(String email) throws SQLException, IOException {
		Connection connection=null;
		PreparedStatement ps=null;
		
		Collection<OrderBean>ordini= new LinkedList<OrderBean>();
		
		String selectSQL="SELECT * FROM ordine WHERE email=?";
		
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(selectSQL);
			ps.setString(1,email);
			ResultSet rs=ps.executeQuery();
		
			while(rs.next()) {
				OrderBean ordine = new OrderBean();
				ordine.setNumeroOrdine(rs.getString("numeroOrdine"));
				ordine.setDataOrdine(rs.getString("dataOrdine"));
				ordine.setImportoOrdine(rs.getString("importoOrdine"));
				ordine.setTipoPagamento(rs.getString("tipoPagamento"));
				ordini.add(ordine);
			}
			
			
			
		}finally {
			try {
			if(ps!=null)
				ps.close();
		} finally {
			if (connection != null)
				connection.close();
		}
		}
			return ordini;
	}

	@Override
	public void doSave(OrderBean ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement ps= null;	
		
		String sql = "INSERT INTO ordine (importoOrdine,email,tipoPagamento) VALUES (?, ?, ?)";
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(sql);
			
			ps.setString(1, ordine.getImportoOrdine());
			ps.setString(2, ordine.getEmail());
			ps.setNString(3, ordine.geTipoPagamento());
			
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

	@Override
	public void doDelete(OrderBean ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement ps= null;	
		
		String sql="DELETE FROM ordine WHERE numeroOrdine=?";
		
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(sql);
			
			ps.setString(1,ordine.getNumeroOrdine());
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

	@Override
	public OrderBean doRetrieveByKey(String numeroOrdine) throws SQLException {
		Connection connection=null;
		PreparedStatement ps=null;

		OrderBean bean= new OrderBean();
		String selectSQL="SELECT * FROM ordine WHERE numeroOrdine=?";
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(selectSQL);
			ps.setString(1, numeroOrdine);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				bean.setEmail(rs.getString("email"));
				bean.setDataOrdine(rs.getString("dataOrdine"));
				bean.setImportoOrdine(rs.getString("importoOrdine"));
				bean.setNumeroOrdine(rs.getString("numeroOrdine"));	
				bean.setTipoPagamento(rs.getString("tipoPagamento"));
			
			}

	}finally {
		try {
		if(ps!=null)
			ps.close();
	} finally {
		if (connection != null)
			connection.close();
	}
	}
		return bean;
}
	
	public Collection<OrderBean> doRetrieveAllAdmin() throws SQLException {
		Connection connection=null;
		PreparedStatement ps=null;
		
		Collection<OrderBean>ordiniAdmin= new LinkedList<OrderBean>();
		
		String selectSQL="SELECT * FROM ordine";
		
		
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(selectSQL);
			ResultSet rs=ps.executeQuery();
		
			while(rs.next()) {
				OrderBean ordine = new OrderBean();
				ordine.setNumeroOrdine(rs.getString("numeroOrdine"));
				ordine.setEmail(rs.getString("email"));
				ordine.setDataOrdine(rs.getString("dataOrdine"));
				ordine.setImportoOrdine(rs.getString("importoOrdine"));
				ordine.setTipoPagamento(rs.getString("tipoPagamento"));
				ordiniAdmin.add(ordine);
			}
				
		}finally {
			try {
			if(ps!=null)
				ps.close();
		} finally {
			if (connection != null)
				connection.close();
		}
		}
			return ordiniAdmin;
	}
	

}
