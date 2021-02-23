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
import it.unisa.model.ProductModelDM;


@WebServlet("/SearchControl")
public class SearchControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

    public SearchControl() {
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
		
		try {
		String carattere = request.getParameter("search");
		carattere = "%"+carattere+"%";
		request.setAttribute("prodottiS", model.getProdotti(carattere));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		String authorization = request.getHeader("Authorization");
		if(authorization==null) {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/SearchBar.jsp");
		dispatcher.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	} else {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/SearchBarAdmin.jsp");
		dispatcher.forward(request, response);
	}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
