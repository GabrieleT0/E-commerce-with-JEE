package it.unisa.control;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.model.ProductBean;
import it.unisa.model.ProductModel;
import it.unisa.model.ProductModelDM;

@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

    public Admin() {
    	super();
    }
    
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isDriverManager = request.getParameter("driver");
		if(isDriverManager == null || isDriverManager.equals("")) {
			isDriverManager = "datasource";
		}
		
		ProductModel model = null;
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		model = new ProductModelDM(ds);
		
		String action = request.getParameter("action");
		
		try {			
			if(action!=null) {
				if(action.equals("insert")) {
					String codice = request.getParameter("codice");   
					String nome = request.getParameter("nome");
					String descrizione = request.getParameter("description");
					Double prezzo = Double.parseDouble(request.getParameter("prezzo"));
					ProductBean bean = new ProductBean();
					if(codice!=null && codice!="")
						bean.setCodice(codice);
					
					if(nome!=null && nome!="")
						bean.setNome(nome);
					
					if(descrizione!=null && descrizione!="")	
						bean.setDescrizione(descrizione);
					
					if(prezzo>0)
						bean.setPrezzo(prezzo);
					
					
					model.doSave(bean);
					request.setAttribute("message", "Prodotto"+bean.getNome()+ "aggiunto");
				
				} else if(action.equals("delete")) {
					String codice = request.getParameter("codice");
					ProductBean bean = model.doRetrieveByKey(codice);
					if(bean!=null && !bean.isEmpty()) {
					model.doDelete(bean);
					request.setAttribute("message", "Prodotto"+bean.getNome()+ "è stato cancellato");
				
					}
				} 
				
				} 
		
		} catch(SQLException e) {
			System.out.println("Errore: "+e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		try {
			request.setAttribute("prodotti", model.doRetrieveAll(""));
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ProductManage.jsp");
		dispatcher.forward(request, response);
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

