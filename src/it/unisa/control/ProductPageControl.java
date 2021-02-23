package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import it.unisa.model.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/ProductPageControl")
public class ProductPageControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

    public ProductPageControl() {
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
		String code = request.getParameter("codice");
		@SuppressWarnings("unchecked")
		Cart<ProductBean> cart = (Cart<ProductBean>)request.getSession().getAttribute("carrello");
		
		try {
			if(action!=null) {
				if(action.equals("details")) {
					String codice = request.getParameter("codice");
					request.removeAttribute("prodotti");
					request.setAttribute("prodotto", model.doRetrieveByKey(codice));
				
				} else if(action.equals("addCart")) {
					String codice = request.getParameter("codice");
					ProductBean bean = model.doRetrieveByKey(codice);
					if(bean!=null && !bean.isEmpty()) {
						cart.addItem(bean);
					} 
				
				} else if(action.equals("update")) {
					String codice = request.getParameter("codice");
					String nome = request.getParameter("nome");
					String descrizione = request.getParameter("description");
					double prezzo = Double.parseDouble(request.getParameter("prezzo"));
					
					ProductBean bean = new ProductBean();
				
				if(codice!=null && codice!="")
					bean.setCodice(codice);
					
				if(nome!=null && nome!="")
					bean.setNome(nome);
				
				if(descrizione!=null && descrizione!="")	
					bean.setDescrizione(descrizione);
				
				if(prezzo>0)
					bean.setPrezzo(prezzo);
				
					
					model.doUpdate(bean);
					request.setAttribute("message", "Prodotto"+bean.getNome()+ "aggiornato");
				}
			}
					request.setAttribute("prodotto", model.doRetrieveByKey(code));
					request.setAttribute("cart", cart);
				
			
			
		}catch(SQLException| NumberFormatException e){
			System.out.println("Errore: "+e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/PaginaProdotto.jsp");
		dispatcher.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}