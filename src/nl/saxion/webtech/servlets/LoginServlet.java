package nl.saxion.webtech.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.saxion.webtech.verhuurobjecten.BasicUser;
import nl.saxion.webtech.verhuurobjecten.Model;
import nl.saxion.webtech.verhuurobjecten.RoomOwner;
import nl.saxion.webtech.verhuurobjecten.RoomTentant;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private Model model;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		model = (Model) getServletContext().getAttribute("myModel");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher myDispatcher = null;
		
		for (BasicUser user : model.getAllUsers()) {
			
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {

				HttpSession s = request.getSession();
			

				if (user instanceof RoomOwner) {
					myDispatcher = request.getRequestDispatcher("WEB-INF/addRoom.html");
				} else if (user instanceof RoomTentant) {
					myDispatcher = request.getRequestDispatcher("WEB-INF/huurder.html");
				}
				
				s.setAttribute("username", username);
				myDispatcher.forward(request, response);
				return;
			}
		}

		myDispatcher = request.getRequestDispatcher("WEB-INF/fouteInlog.html");
		myDispatcher.forward(request, response);
		

	}

}
