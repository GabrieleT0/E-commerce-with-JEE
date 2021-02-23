package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.model.FotoProdotto;

@WebServlet("/GetFoto")
public class GetFoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetFoto() {
		super();
	}

	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String isDriverManager = request.getParameter("driver");
		if(isDriverManager == null || isDriverManager.equals("")) {
			isDriverManager = "datasource";
		}
		

		FotoProdotto model = null;
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		model = new FotoProdotto(ds);
		
		String codice = (String) request.getParameter("codice");
		if (codice != null) 
		{
			byte[] bt;
			try {
				bt = model.load(codice);
				ServletOutputStream out = response.getOutputStream();
				if (bt != null) 
				{
					out.write(bt);
					response.setContentType("image/jpeg");
				}
				out.close();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
