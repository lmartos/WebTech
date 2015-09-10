package nl.saxion.webtech.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.saxion.webtech.verhuurobjecten.Admin;
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
				}else if(user instanceof Admin){
					
					Cookie[] cookies = request.getCookies();
					
					if(cookies != null){
						for(Cookie cookie : request.getCookies()){
							if(cookie.getName().equals("timestamp")){
								model.setLastVisited(cookie.getValue());
							}
						}
					}
					
					Timestamp currentTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
					Cookie myCookie = new Cookie("timestamp", "" + currentTime);
					myCookie.setMaxAge(-1);
					response.addCookie(myCookie);
					
					model.incrementTimesVisited();
					
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					
					out.println("<html>");
				    out.println("<head>");
				    out.println("<title>Rooms</title>");
				    out.println("</head>");
				    out.println("<body bgcolor=\"white\">");
				    
				    out.println("times visited: " + model.getTimesVisited());
				    out.println("last visited: " + model.getLastVisited());
				    out.println("<br>");
				    out.println("<strong> Users: </strong>");
				    out.println("<br>");
				    
				    for(BasicUser client: model.getAllUsers()){
				    	out.println(client.getUsername() + " " + client.getClass().getSimpleName());
				    	out.println("<br>");
				    }
				    out.println("</body>");
				    out.println("</html>");
				    out.close();
				    return;
				    
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
