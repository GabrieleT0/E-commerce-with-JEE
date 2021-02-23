package it.unisa.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import it.unisa.model.ClientDM;
import it.unisa.model.ClientModel;
import it.unisa.model.OrderBean;
import it.unisa.model.OrderModel;
import it.unisa.model.OrderModelDM;
import it.unisa.model.ProductBean;
import it.unisa.model.ProductModel;
import it.unisa.model.ProductModelDM;
import it.unisa.model.ProductOrderBean;
import it.unisa.model.ProductOrderDM;
import it.unisa.model.ProductOrderModel;

@WebServlet("/AdminClientControl")
public class AdminClientControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public AdminClientControl() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isDriverManager = request.getParameter("driver");
		if(isDriverManager == null || isDriverManager.equals("")) {
			isDriverManager = "datasource";
		}
		
		ClientModel modelClient = null;
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		modelClient = new ClientDM(ds);
		OrderModel modelOrder = null;
		modelOrder = new OrderModelDM(ds);
		ProductOrderModel model3 = null;
		model3 = new ProductOrderDM(ds);
		ProductModel modelProduct = null;
		modelProduct = new ProductModelDM(ds);
		
		
		String action = request.getParameter("action");
		try {			
			if(action!=null) {
				if(action.equals("showClients")) {
					PrintWriter out = response.getWriter();
					Gson gson = new Gson();
					out.print(gson.toJson(modelClient.doRetrieveClient()));
					out.flush();
					out.close();
			}else if(action.equals("showOrders")) {
				PrintWriter out = response.getWriter();
				Gson gson = new Gson();
				out.print(gson.toJson(modelOrder.doRetrieveAllAdmin()));
				out.flush();
				out.close();
			} else if(action.equals("deleteOrder")) {
				String numeroOrdine = request.getParameter("numeroOrdine");
				OrderBean bean = modelOrder.doRetrieveByKey(numeroOrdine);
				if(bean!=null && !bean.isEmpty()) {
					modelOrder.doDelete(bean);
			}
			
			} else if(action.equals("details")) {
				Collection<ProductBean> prodottiOrdine=null;
				String numeroOrdine = request.getParameter("numeroOrdine");
				Collection <ProductOrderBean> prodotti =  model3.doRetrieveByNumOrdine(numeroOrdine);
				for(ProductOrderBean prod: prodotti) {
					String codice = prod.getCodice();
					prodottiOrdine = modelProduct.doRetrieveAllByKey(codice);
				}
				request.setAttribute("prodottiOrdine", prodottiOrdine);
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ProdottiOrdine.jsp");
				dispatcher.forward(request, response);
				prodottiOrdine.clear();
			}
			
			
			}
		} catch(SQLException e) {
			System.out.println("Errore: "+e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/AdminView.jsp");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
