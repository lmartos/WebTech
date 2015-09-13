package nl.saxion.webtech.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.saxion.webtech.model.Model;
import nl.saxion.webtech.model.Room;
import nl.saxion.webtech.model.RoomOwner;

@WebServlet("/MyRoomsServlet")
public class MyRoomsServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6014787821777537891L;
	private Model model;
	
	@Override
	public void init() throws ServletException {
		super.init();
		model = (Model) getServletContext().getAttribute("myModel");
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		if (session.getAttribute("username") == null) {
			resp.sendRedirect("WebContent/login.html");
			//TODO: add Log
		}
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Rooms</title>");
	    out.println("</head>");
	    out.println("<body bgcolor=\"white\">");
	    out.println("Your rooms: <br/>");
	    
	    //voeg de rooms toe aan de html code
		for (Room room : model.getRoomManager().getRooms(model.getUserManager().getUser((String)session.getAttribute("username"), RoomOwner.class))) {
			out.println(room.getId() + " : " + room.getCity() + "-" + room.getMaxRentPrice() + "$ -" + room.getSurface() + "m2");
			out.println("<br/>");
		}
		
		out.println("<a href='javascript:history.back()' >terug naar toevoegen</a>");
	    out.println("</body>");
	    out.println("</html>");
	    out.close();
	}
}
