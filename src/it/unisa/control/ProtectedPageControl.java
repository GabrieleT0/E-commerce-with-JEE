package it.unisa.control;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import it.unisa.model.Admin;
import it.unisa.model.AdminBean;
import it.unisa.model.AdminDM;
import java.util.Base64;

@WebServlet(name = "/ProtectPage", urlPatterns = { "/protect" }, initParams = {
		@WebInitParam(name = "passwordFile", value = "passwords.properties") })
public class ProtectedPageControl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String username;
	String password;

	public ProtectedPageControl() {
		super();
	}	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String isDriverManager = request.getParameter("driver");
		if(isDriverManager == null || isDriverManager.equals("")) {
			isDriverManager = "datasource";
		}
		
		Admin model = null;
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		model = new AdminDM(ds);
		
		
		System.out.println("isSecure: " + request.isSecure());
		System.out.println("getLocalPort: " + request.getLocalPort());
		System.out.println("getServerPort: " + request.getServerPort());
		System.out.println("getRequestURL: "+ request.getRequestURL());
		System.out.println("getRequestURI: "+ request.getRequestURI());
		System.out.println("getQueryString: "+ request.getQueryString());
		
		response.setContentType("text/html");
		@SuppressWarnings("unused")
		PrintWriter out = response.getWriter();
		String authorization = request.getHeader("Authorization");
		if (authorization == null) {
			askForPassword(response);
		} else {
			try {
			String userInfo = authorization.substring(6).trim();
			String nameAndPassword = new String(Base64.getDecoder().decode(userInfo));
			int index = nameAndPassword.indexOf(":");
			String username = nameAndPassword.substring(0, index);
			String password = nameAndPassword.substring(index + 1);
			AdminBean bean = new AdminBean();
			bean = model.Login(username);
			String realUsername = bean.getUsername();
			String realPassword = bean.getPassword();
			if ((realPassword != null) && (realPassword.equals(password) && (realUsername.equals(username)))) {
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/AdminView.jsp");
				dispatcher.forward(request, response);
			} else {
				askForPassword(response);
			}	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// If no Authorization header was supplied in the request.
	private void askForPassword(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setHeader("WWW-Authenticate", "BASIC realm=\"privileged-few\"");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}	
}
