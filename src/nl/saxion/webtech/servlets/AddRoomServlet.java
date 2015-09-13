package nl.saxion.webtech.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.saxion.webtech.model.Model;
import nl.saxion.webtech.model.Room;
import nl.saxion.webtech.model.RoomOwner;

/**
 * Servlet implementation class AddRoomServlet
 */
@WebServlet("/AddRoomServlet")
public class AddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private Model model;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRoomServlet() {
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
		
		
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("username") == null) {
			response.sendRedirect("WebContent/login.html");
		}
		
		String username = (String) session.getAttribute("username");
		String city = request.getParameter("location");
		double maxPrice;
		double surface;
		
		try{
			maxPrice = Double.parseDouble(request.getParameter("maxPrice"));
			surface = Double.parseDouble(request.getParameter("surfaceInput"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		
		model.getRoomManager().addRoom(new Room(model.getUserManager().getUser(username, RoomOwner.class), surface, maxPrice, city));
		

		RequestDispatcher myDispatcher = request.getRequestDispatcher("WEB-INF/addRoom.html");
		myDispatcher.forward(request, response);
	}

}
