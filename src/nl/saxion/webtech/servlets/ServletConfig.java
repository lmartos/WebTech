package nl.saxion.webtech.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import nl.saxion.webtech.verhuurobjecten.Model;

public class ServletConfig implements ServletContextListener {
	
	public ServletConfig(){
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		   arg0.getServletContext().setAttribute("myModel", new Model());
		
	}

}
