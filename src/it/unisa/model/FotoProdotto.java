package it.unisa.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;



public class FotoProdotto {
	
	private static DataSource ds = null;
	@SuppressWarnings("static-access")
	public FotoProdotto(DataSource ds) {
		this.ds = ds;
		
		System.out.println("DataSource Foto Prodotto creation....");
	}
	
	
	
	public synchronized static byte[] load(String id) throws SQLException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		byte[] bt = null;

		try {
			connection = ds.getConnection();
			String sql = "SELECT photo FROM prodotto WHERE codice = ?";
			ps = connection.prepareStatement(sql);
			
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				bt = rs.getBytes("photo");
			}

		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		} 
			finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bt;
	}

	public synchronized static void updatePhoto(String codice, String photo) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ds.getConnection();

			stmt = con.prepareStatement("UPDATE prodotto SET photo = ? WHERE codice = ?");
			
			File file = new File(photo);
			try {
				FileInputStream fis = new FileInputStream(file);
				stmt.setBinaryStream(1, fis, fis.available());
				stmt.setString(2, codice);
				
				stmt.executeUpdate();
				
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (con != null)
					con.close();
			}
		}
	}	
}
