package nl.saxion.webtech.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.saxion.webtech.model.Model;
import nl.saxion.webtech.model.RoomOwner;
import nl.saxion.webtech.model.RoomTennant;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Model model;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	model = (Model) getServletContext().getAttribute("myModel");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		
		if(!password.equals(passwordCheck)){
			response.sendRedirect("WebContent/register.html");
		}
		
		String username = request.getParameter("username");
		String type = request.getParameter("group1");
		
		if (!createUser(type, username, passwordCheck)) {
			response.sendRedirect("WEB_INF/fouteInlog");
			System.out.println("Something went wrong, neither of the types were used.");
			return;
		}
		
		System.out.println("account created");
		response.sendRedirect("login.html");
	}
	
	private boolean createUser(String type, String username, String password) {
		boolean created = false;
		
		if(type.equals("verhuurder")){
			model.getUserManager().addUser(new RoomOwner(username, password));
			created = true;
		}else if(type.equals("huurder")){
			model.getUserManager().addUser(new RoomTennant(username, password));
			created = true;
		} 
		
		return created;
	}

}
