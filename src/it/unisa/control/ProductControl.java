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
import it.unisa.model.ProductModel;
import it.unisa.model.Cart;
import it.unisa.model.ProductBean;
import it.unisa.model.ProductModelDM;


@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

    public ProductControl() {
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
		
		
		
		@SuppressWarnings("unchecked")
		Cart<ProductBean> cart = (Cart<ProductBean>)request.getSession().getAttribute("carrello");
		if(cart == null) {
			cart = new Cart<ProductBean>();
			request.getSession().setAttribute("carrello", cart);
		}
		
		
		String sort = request.getParameter("sort");
		String action = request.getParameter("action");
		
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
				
				} else if(action.equals("clearCart")) {
					cart.deleteItems();
				
				} else if(action.equals("deleteCart")) {
					String codice = request.getParameter("codice");
					ProductBean bean = model.doRetrieveByKey(codice);
					if(bean != null && !bean.isEmpty()) {
						cart.deleteItem(bean);			
					}
				}
					
			}
			
		}catch(SQLException| NumberFormatException e){
			System.out.println("Errore: "+e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		request.setAttribute("cart", cart);
		
		try {
			request.removeAttribute("prodotti");
			request.setAttribute("prodotti", model.doRetrieveAll(""));
			request.setAttribute("prodotti", model.doRetrieveAll(sort));
		} catch(SQLException e) {
			System.out.println("Errore: "+e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Vetrina.jsp");
		dispatcher.forward(request, response);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
