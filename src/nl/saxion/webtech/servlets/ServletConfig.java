package nl.saxion.webtech.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import nl.saxion.webtech.model.Model;

@WebListener("Context listener")
public class ServletConfig implements ServletContextListener {
	
	public ServletConfig(){
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		servletContextEvent.getServletContext().setAttribute("myModel", new Model());
	}

}
