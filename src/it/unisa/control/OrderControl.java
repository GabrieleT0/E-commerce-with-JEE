package it.unisa.control;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import it.unisa.model.Cart;
import it.unisa.model.ClientBean;
import it.unisa.model.OrderBean;
import it.unisa.model.OrderModel;
import it.unisa.model.OrderModelDM;
import it.unisa.model.ProductBean;
import it.unisa.model.ProductModel;
import it.unisa.model.ProductModelDM;
import it.unisa.model.ProductOrderBean;
import it.unisa.model.ProductOrderDM;
import it.unisa.model.ProductOrderModel;

/**
 * Servlet implementation class OrderControl
 */
@WebServlet("/OrderControl")
public class OrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
    public OrderControl() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isDriverManager = request.getParameter("driver");
		if(isDriverManager == null || isDriverManager.equals("")) {
			isDriverManager = "datasource";
		}
		
		ProductModel model3 = null;
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		model3 = new ProductModelDM(ds);
		
		OrderModel model = null;
		model = new OrderModelDM(ds);
		
		ProductOrderModel model2 = null;
		model2 = new ProductOrderDM(ds);
		
		String action = request.getParameter("action");
		
		try {
			if(action!=null) {
				if(action.equals("acquista")) {
			@SuppressWarnings("unchecked")
			Cart<ProductBean> cart = (Cart<ProductBean>)request.getSession().getAttribute("carrello");
			List<ProductBean> prodcart = cart.getItems();
			String importo = request.getParameter("totale"); 
			String tipoPagamento = request.getParameter("tipoPagamento");
			if(request.getSession().getAttribute("account")==null) {
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Login.jsp");
				dispatcher.forward(request, response);
			}
			ClientBean cliente = (ClientBean) request.getSession().getAttribute("account");
			String email = cliente.getEmail();
			OrderBean bean = new OrderBean();
			bean.setEmail(email);
			bean.setImportoOrdine(importo);
			bean.setTipoPagamento(tipoPagamento);
			model.doSave(bean);
			
		
		OrderBean newOrder=model.doRetrieveByMail(email);
		for(ProductBean prod: prodcart) {
			String codice = prod.getCodice();
			String numeroOrdine = newOrder.getNumeroOrdine();
			ProductOrderBean prodOrd = new ProductOrderBean();
			prodOrd.setCodice(codice);
			prodOrd.setNumeroOrdine(numeroOrdine);
			model2.doSave(prodOrd);
			
		}cart.deleteItems();
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Vetrina.jsp");
		dispatcher.forward(request, response);
				
				} else if(action.equals("ordini")) {
					if(request.getSession().getAttribute("account")==null) {
						RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Login.jsp");
						dispatcher.forward(request, response);
					} else {
					ClientBean cliente = (ClientBean) request.getSession().getAttribute("account");	
					String email = cliente.getEmail();
					request.setAttribute("ordini", model.doRetrieveAll(email));
					RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Ordini.jsp");
					dispatcher.forward(request, response);
					}
				} else if(action.equals("details")) {
					Collection<ProductBean> prodottiOrdine=null;
					String numeroOrdine = request.getParameter("numeroOrdine");
					Collection <ProductOrderBean> prodotti =  model2.doRetrieveByNumOrdine(numeroOrdine);
					for(ProductOrderBean prod: prodotti) {
						String codice = prod.getCodice();
						prodottiOrdine = model3.doRetrieveAllByKey(codice);
					}
					request.setAttribute("prodottiOrdine", prodottiOrdine);
					RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Ordini.jsp");
					dispatcher.forward(request, response);
					prodottiOrdine.clear();
				}
		
					
				}
		

		} catch(SQLException e) {
			System.out.println("Errore: "+e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
