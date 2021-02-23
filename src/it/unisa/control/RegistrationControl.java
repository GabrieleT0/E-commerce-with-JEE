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
import it.unisa.model.ClientModel;
import it.unisa.model.ClientBean;
import it.unisa.model.ClientDM;



@WebServlet("/RegistrationControl")
public class RegistrationControl extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public RegistrationControl() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String isDriverManager = request.getParameter("driver");
		if(isDriverManager == null || isDriverManager.equals("")) {
			isDriverManager = "datasource";
		}
		
		ClientModel model = null;
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		model = new ClientDM(ds);
		
		
		String action = request.getParameter("action");
		try {
			if(action!=null) {
				if(action.equals("registration")) {
					String email = request.getParameter("email");
					String password = request.getParameter("password");
					String nome = request.getParameter("nome");
					String cognome = request.getParameter("cognome");
					String città = request.getParameter("citta");
					String provincia = request.getParameter("provincia");
					String via = request.getParameter("via");
					String numeroCivico = request.getParameter("numeroCivico");
					String CAP = request.getParameter("cap");
					String numeroTelefono = request.getParameter("numeroTelefono");
					String hashedPsw="";
					ClientBean bean = new ClientBean();
					
					ClientBean checkMail=model.doRetrieveByMail(email);
					if(checkMail.getEmail().equals(email)) {
						RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/RegistrationFail.jsp");
						dispatcher.forward(request, response);
					} else {
					
					if(email!=null && email!="")
						bean.setEmail(email);
					
					if(password!=null && password.length()>=8)
						hashedPsw=bean.hashPassword(password);
						bean.setPassword(hashedPsw);
					
					if(nome!=null && nome!="")
						bean.setNome(nome);
					
					if(cognome!=null && cognome!="")	
						bean.setCognome(cognome);
					
					if(città!=null && città!="")
						bean.setCitta(città);
					
					if(provincia!=null && provincia!="")
						bean.setProvincia(provincia);
					
					if(via!=null && via!="")
						bean.setVia(via);
					
					if(numeroCivico!=null && numeroCivico!="")
						bean.setNumerocivico(numeroCivico);
					
					if(CAP!=null && CAP!="")
					bean.setcap(CAP);
					
					if(numeroTelefono!=null && numeroTelefono!="")
						bean.setNumeroTelefono(numeroTelefono);
					
					model.Registration(bean);
					request.setAttribute("account", model.doRetrieveByMailPsw(email, password));
					request.getSession().setAttribute("account", bean);
					RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Vetrina.jsp");
					dispatcher.forward(request, response);
					
					response.getWriter().append("Served at: ").append(request.getContextPath());
					}	
					
				}else if(action.equals("login")) {
					String email = request.getParameter("email");
					String password = request.getParameter("password");
					request.removeAttribute("email");
					request.removeAttribute("password");
					if(email!=null && email!="" && password!=null && password!="" && password.length()>=8) {
						ClientBean cliente = new ClientBean();
						cliente=model.doRetrieveByMail(email);
						if(cliente.getEmail()==null||cliente.getEmail()==""||cliente.checkPass(password, cliente.getPassword())==false) {
							RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/LoginFail.jsp");
							dispatcher.forward(request, response);
					} else {
					
						request.setAttribute("account", model.doRetrieveByMailPsw(email, password));
						request.getSession().setAttribute("account", cliente);
						RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Vetrina.jsp");
						dispatcher.forward(request, response);
					}
						
					} else {
						RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/LoginFail.jsp");
						dispatcher.forward(request, response);
					}
					
			} else if(action.equals("updateInd")) {
				ClientBean cliente = (ClientBean) request.getSession().getAttribute("account");
				String email = cliente.getEmail();
				String nome = cliente.getNome();
				String cognome = cliente.getCognome();
				String città = request.getParameter("citta");
				String provincia = request.getParameter("provincia");
				String via = request.getParameter("via");
				String numeroCivico = request.getParameter("numeroCivico");
				String cap = request.getParameter("cap");
				ClientBean bean = new ClientBean();
				
				if(email!=null && email!="")
				bean.setEmail(email);
				
				if(nome!=null && nome!="")
				bean.setNome(nome);
				
				if(cognome!=null && cognome!="")
				bean.setCognome(cognome);
				
				if(città!=null && città!="")
				bean.setCitta(città);
				
				if(provincia!=null && provincia!="")
				bean.setProvincia(provincia);
				
				if(via!=null && via!="")
				bean.setVia(via);
				
				if(numeroCivico!=null && numeroCivico!="")
				bean.setNumerocivico(numeroCivico);
				
				if(cap!=null && cap!="")
				bean.setcap(cap);
				
				model.doUpdate(bean);
				request.setAttribute("account", bean);
				request.getSession().setAttribute("account", bean);
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/AreaPersonale.jsp");
				dispatcher.forward(request, response);
				
			} else if(action.equals("logout")) {
				request.getSession().removeAttribute("account");
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Login.jsp");
				dispatcher.forward(request, response);
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
