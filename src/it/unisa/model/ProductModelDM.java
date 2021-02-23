package it.unisa.model;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class ProductModelDM implements ProductModel{
	
	private static DataSource ds = null;
	@SuppressWarnings("static-access")
	public ProductModelDM(DataSource ds) {
		this.ds = ds;
		
		System.out.println("DataSource Product Model creation....");
	}
	
	Collection<ProductBean>prodottiOrder= new LinkedList<ProductBean>();
	
	public ProductBean doRetrieveByKey(String codice) throws SQLException {
		Connection connection=null;
		PreparedStatement ps=null;
		
		ProductBean bean= new ProductBean();
		
		String selectSQL="SELECT * FROM prodotto WHERE codice=?";
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(selectSQL);
			ps.setString(1,codice);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				bean.setCodice(rs.getString("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getDouble("prezzo"));				
			}
			System.out.println(bean);
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

	@Override
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException, IOException{
		Connection connection=null;
		PreparedStatement ps=null;
		
		Collection<ProductBean>prodotti= new LinkedList<ProductBean>();
	
		String selectSQL="SELECT * FROM prodotto";
		
		if(order!=null && !order.equals("")){
			selectSQL="SELECT * FROM prodotto ORDER BY " +order;
		}
		
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(selectSQL);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				ProductBean bean= new ProductBean();
				bean.setCodice(rs.getString("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getDouble("prezzo"));				
	            prodotti.add(bean);
			
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
			return prodotti;
	}
	
	public static void add(ProductBean prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement ps= null;	

		String sql = "INSERT INTO prodotto (codice, nome, descrizione, prezzo) VALUES (?, ?, ?, ?)";
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(sql);		
			
			ps.setString(1,prodotto.getCodice());
			ps.setString(2,prodotto.getNome());
			ps.setString(3,prodotto.getDescrizione());
			ps.setDouble(4,prodotto.getPrezzo());
			
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
	public void doSave(ProductBean prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement ps= null;	
		
		String sql = "INSERT INTO prodotto" +
				"(codice,nome,descrizione,prezzo) VALUES (?, ?, ?, ?)";
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(sql);
			
			ps.setString(1, prodotto.getCodice());
			ps.setString(2, prodotto.getNome());
			ps.setString(3, prodotto.getDescrizione());
			ps.setDouble(4, prodotto.getPrezzo());
			
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
	public void doUpdate(ProductBean prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement ps= null;	
		
		String sql="UPDATE prodotto SET nome= ?, descrizione= ?, prezzo= ? WHERE codice= ?";
		
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(sql);
			
			ps.setString(1, prodotto.getNome());
			ps.setString(2, prodotto.getDescrizione());
			ps.setDouble(3, prodotto.getPrezzo());
			ps.setString(4, prodotto.getCodice());
		
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
	public void doDelete(ProductBean prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement ps= null;	
		
		String sql="DELETE FROM prodotto WHERE codice=?";
		
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(sql);
			
			ps.setString(1, prodotto.getCodice() );
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
	
	public Collection<ProductBean> doRetrieveAllByKey(String codice) throws SQLException, IOException{
		Connection connection=null;
		PreparedStatement ps=null;
		
		
		String selectSQL="SELECT * FROM prodotto WHERE codice=?";
		
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(selectSQL);
			ps.setString(1, codice);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				ProductBean bean= new ProductBean();
				bean.setCodice(rs.getString("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getDouble("prezzo"));			
	            prodottiOrder.add(bean);
			
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
		return prodottiOrder;
}
	
	public Collection<ProductBean> getProdotti(String carattere) throws SQLException{
		Collection<ProductBean> list = new LinkedList <ProductBean>();
		Connection connection=null;
		PreparedStatement ps=null;
        String selectSQL = "SELECT * FROM prodotto WHERE nome LIKE ?";
        

		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(selectSQL);
			ps.setString(1, carattere);
			ResultSet rs=ps.executeQuery();
        
		
		while(rs.next()) {
			ProductBean bean= new ProductBean();
			bean.setCodice(rs.getString("codice"));
			bean.setNome(rs.getString("nome"));
			bean.setDescrizione(rs.getString("descrizione"));
			bean.setPrezzo(rs.getDouble("prezzo"));
			list.add(bean);
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
	return list;
	
	}

}

