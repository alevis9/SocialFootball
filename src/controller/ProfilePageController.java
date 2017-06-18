package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import exceptions.PersonaNonEsistenteException;
import model.Persona;
import service.*;

@WebServlet("/ProfilePageController")
public class ProfilePageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(beanName="SocialFootballFacade")
	private SocialFootballFacade facade;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			
		String email = request.getParameter("emailProfile");
		
		try {
			Persona persona = (Persona)facade.GetPersona(email);
			request.setAttribute("ProfileUser", persona);			
		} catch (PersonaNonEsistenteException e) {
		}
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(response.encodeURL("/ProfilePage.jsp"));
		rd.forward(request, response);		
	}
}
