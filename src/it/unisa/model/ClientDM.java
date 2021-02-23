package it.unisa.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class ClientDM implements ClientModel{
	
	private static DataSource ds = null;
	@SuppressWarnings("static-access")
	public ClientDM(DataSource ds) {
		this.ds = ds;
		
		System.out.println("DataSource Client Model creation....");
	}
			
	@Override
	public void Registration(ClientBean cliente) throws SQLException {
		Connection connection = null;
		PreparedStatement ps= null;	
		
		String sql = "INSERT INTO cliente (email, psw, nome, cognome, città, provincia, via, numeroCivico, cap, numeroTelefono)"+
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
		try {
			connection = ds.getConnection();
			ps=connection.prepareStatement(sql);
				
			ps.setString(1,cliente.getEmail());
			ps.setString(2,cliente.getPassword());
			ps.setString(3,cliente.getNome());
			ps.setString(4,cliente.getCognome());
			ps.setString(5,cliente.getCitta());
			ps.setString(6,cliente.getProvincia());
			ps.setString(7,cliente.getVia());
			ps.setString(8,cliente.getNumerocivico());
			ps.setString(9,cliente.getcap());
			ps.setString(10, cliente.getNumeroTelefono());
				
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
		public void doDeleteAccount(ClientBean cliente) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public ClientBean doRetrieveByMailPsw(String email,String password) throws SQLException {
			Connection connection = null;
			PreparedStatement ps= null;	

			ClientBean bean = new ClientBean();
			
			String sql = "SELECT * FROM cliente WHERE email=? AND psw=?";
			try {
				connection = ds.getConnection();
				ps=connection.prepareStatement(sql);
				
				ps.setString(1,email);
				ps.setString(2,password);
				
				ResultSet rs = ps.executeQuery();
			
				while(rs.next()) {
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("psw"));
					bean.setNome(rs.getString("nome"));
					bean.setCognome(rs.getString("cognome"));
					bean.setCitta(rs.getString("città"));
					bean.setProvincia(rs.getString("provincia"));
					bean.setVia(rs.getString("via"));
					bean.setNumerocivico(rs.getString("numeroCivico"));
					bean.setcap(rs.getString("cap"));
				}
			System.out.print(bean);
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
		public Collection<ClientBean> doRetrieveClient() throws SQLException, IOException {
			Connection connection=null;
			PreparedStatement ps=null;
			
			Collection<ClientBean> clienti = new LinkedList<ClientBean>();
		
			String selectSQL="SELECT * FROM Cliente";
			
			try {
				connection = ds.getConnection();
				ps=connection.prepareStatement(selectSQL);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					ClientBean bean = new ClientBean();
					bean.setEmail(rs.getString("email"));
					bean.setNome(rs.getString("nome"));
					bean.setCognome(rs.getString("cognome"));
					bean.setCitta(rs.getString("città"));
					bean.setProvincia(rs.getString("provincia"));
					bean.setVia(rs.getString("via"));
					bean.setNumerocivico(rs.getString("numeroCivico"));
					bean.setcap(rs.getString("cap"));
					bean.setNumeroTelefono(rs.getString("numeroTelefono"));
					clienti.add(bean);
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
			return clienti;
	}

		@Override
		public ClientBean doRetrieveByMail(String email) throws SQLException {
			Connection connection = null;
			PreparedStatement ps= null;	
			
			ClientBean bean = new ClientBean();
			
			String sql = "SELECT * FROM cliente WHERE email=?";
			
			try {
				connection = ds.getConnection();
				ps=connection.prepareStatement(sql);
				
				ps.setString(1,email);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("psw"));
					bean.setNome(rs.getString("nome"));
					bean.setCognome(rs.getString("cognome"));
					bean.setCitta(rs.getString("città"));
					bean.setProvincia(rs.getString("provincia"));
					bean.setVia(rs.getString("via"));
					bean.setNumerocivico(rs.getString("numeroCivico"));
					bean.setcap(rs.getString("cap"));
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
		public Collection<ClientBean> doRetrieveClient(String order) throws SQLException, IOException {
			// TODO Auto-generated method stub
			return null;
		}
		
		public void doUpdate(ClientBean cliente)throws SQLException{
			Connection connection = null;
			PreparedStatement ps= null;	
			
			String sql="UPDATE cliente SET città= ?, provincia= ?, via= ?, numeroCivico= ?, cap=? WHERE email= ?";
			try {
				connection = ds.getConnection();
				ps=connection.prepareStatement(sql);
				
				ps.setString(1, cliente.getCitta());
				ps.setString(2, cliente.getProvincia());
				ps.setString(3, cliente.getVia());
				ps.setString(4, cliente.getNumerocivico());
				ps.setString(5, cliente.getcap());
				ps.setString(6, cliente.getEmail());
			
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
}





