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

@WebServlet("/SearchRoomServlet")
public class SearchRoomServlet extends HttpServlet{
	/**
	 * automatic generated serial version
	 */
	private static final long serialVersionUID = 7156768235797726558L;
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
		
		try {
			@SuppressWarnings("unused")
			String username = (String) session.getAttribute("username");
			//TODO: add log.
		} catch (NullPointerException e) {
			resp.sendRedirect("login.html");
			return;
		}
		
		String location = req.getParameter("location");
		double maxPrice = 0;
		double surface = 0;
		
		try {
			maxPrice = Double.parseDouble(req.getParameter("maxPrice"));
			surface = Double.parseDouble(req.getParameter("surfaceInput"));
		} catch (NumberFormatException e) {
			e.getStackTrace();
			return;
		}
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Rooms</title>");
	    out.println("</head>");
	    out.println("<body bgcolor=\"white\">");
	    out.println("Rooms available by your specification: <br/>");
	    
	    //voeg de rooms to aan de html code
		for (Room room : model.getRoomManager().getSpecifiedRooms(location, maxPrice, surface)) {
			out.println(room.getId() + " : " + room.getCity() + "-" + room.getMaxRentPrice() + "$ -" + room.getSurface() + "m2");
			out.println("<br/>");
		}
		
		out.println("<a href='javascript:history.back()' >terug naar zoeken</a>");
	    out.println("</body>");
	    out.println("</html>");
	    out.close();
	}
}
