package controller;

import java.io.IOException;

import model.*;
import service.*;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.*;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(beanName="SocialStartupFacade")
	private SocialFootballFacade facade;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {			
		String email = request.getParameter("email");
		String password = request.getParameter("password");		
		String nextPage=null;
		ServletContext application = getServletContext();
		try {
			Persona p= facade.GetPersona(email, password);
			HttpSession session=request.getSession();
			session.setAttribute("CurrentPersona", p);
			application.setAttribute("Societa", facade.GetSocieta());
			nextPage ="/HomePage.jsp";			
		} 
		catch (PersonaNonEsistenteException e) {			
			request.setAttribute("loginError", "Persona non esistente");
			nextPage = "/LoginPage.jsp";
		} 
		catch (PasswordNonValidaException e) {
			request.setAttribute("loginError", "password non valida");
			nextPage = "/LoginPage.jsp";
		}		
		RequestDispatcher rd = application.getRequestDispatcher(response.encodeURL(nextPage));
		rd.forward(request, response);
	}
}
