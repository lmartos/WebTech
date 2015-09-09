package nl.saxion.webtech.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.saxion.webtech.verhuurobjecten.Model;
import nl.saxion.webtech.verhuurobjecten.Room;

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
        // TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession s = request.getSession(false);
		
		if (s == null) {
			response.sendRedirect("WebContent/login.html");
		}
		
		String username = (String) s.getAttribute("username");
		String city = request.getParameter("location");
		double maxPrice;
		double surface;
		
		try{
			maxPrice = Double.parseDouble(request.getParameter("maxPrice"));
			surface = Double.parseDouble(request.getParameter("surface"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			model.AddRoom(new Room(model.getOwner(username),surface, maxPrice, city));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
