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

import nl.saxion.webtech.model.Admin;
import nl.saxion.webtech.model.Model;
import nl.saxion.webtech.model.RoomOwner;
import nl.saxion.webtech.model.RoomTennant;
import nl.saxion.webtech.model.User;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher dispatcher;

		HttpSession session = request.getSession();
		
		if (!model.getUserManager().verifyAccount(username, password)) {
			dispatcher = request.getRequestDispatcher("WEB-INF/fouteInlog.html");
			dispatcher.forward(request, response);
		}
		
		User user = model.getUserManager().getUser(username, User.class);

		if (user instanceof Admin) {
			Cookie[] cookies = request.getCookies();

			Cookie timestampCookie = getTimestampCookie(cookies);
			response.addCookie(timestampCookie);

			model.setLastVisited(timestampCookie.getValue());
			model.incrementTimesVisited();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			printAdminPage(out);
		} else {
			dispatcher = getUserDispatcher(user, request);
			dispatcher.forward(request, response);
		}

		session.setAttribute("username", username);

	}

	/**
	 * @param user
	 *            to specify the dispatcher
	 * @param request
	 * @return a dispatcher specified for the user, if the user is unkown error
	 *         page is loaded.
	 */
	private RequestDispatcher getUserDispatcher(User user, HttpServletRequest request) {
		if (user instanceof RoomOwner) {
			return request.getRequestDispatcher("WEB-INF/addRoom.html");
		} else if (user instanceof RoomTennant) {
			return request.getRequestDispatcher("WEB-INF/tentant.html");
		} else {
			return request.getRequestDispatcher("WEB-INF/fouteInlog.html");
		}
	}

	/**
	 * @param out
	 * @throws IOException
	 */
	private void printAdminPage(PrintWriter out) throws IOException {

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

		for (User client : model.getUserManager().getUsers()) {
			out.println(client.getUsername() + " " + client.getClass().getSimpleName());
			out.println("<br>");
		}

		out.println("</body>");
		out.println("</html>");
		out.close();
	}

	/**
	 * Set the Timestamp cookie value in a model, if it doesn't exist a cookie
	 * will be created
	 * 
	 * @param request
	 * @param response
	 * @param cookies
	 */
	private Cookie getTimestampCookie(Cookie[] cookies) {
		if (cookies == null || !hasTimestampCookie(cookies)) {
			return createTimestampCookie();
		} else {
			return retreiveTimestampCookie(cookies);
		}
	}

	/**
	 * Loop all given cookies and checks if Timestamp cookie exists.
	 * 
	 * @param request
	 * @return true if timeStamp cookie exists, else return false.
	 */
	private boolean hasTimestampCookie(Cookie[] cookies) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("timestamp")) {
				return true;
			}
		}
		return false;
	}

	private Cookie retreiveTimestampCookie(Cookie[] cookies) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("timestamp")) {
				return cookie;
			}
		}
		return null;
	}

	/**
	 * @return a cooke created with a Timestamp of the current time.
	 */
	private Cookie createTimestampCookie() {
		Timestamp currentTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
		Cookie myCookie = new Cookie("timestamp", "" + currentTime);
		myCookie.setMaxAge(-1);
		return myCookie;
	}
}
