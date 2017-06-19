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
import javax.servlet.http.HttpSession;

import exceptions.*;
import model.*;
import service.*;

@WebServlet("/GiocatoreSignUpController")
public class GiocatoreSignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB(beanName="SocialStartupFacade")
	private SocialFootballFacade facade;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		String nextPage = null;
				
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String avatar = request.getParameter("urlImg");
		String annoDiNascita = request.getParameter("annoDiNascita");
		String sito = request.getParameter("sito");
		String facebook = request.getParameter("facebook");
		String twitter = request.getParameter("twitter");
		String linkedIn = request.getParameter("linkedIn");
		ServletContext application = getServletContext();
		try {
			Giocatore g = new Giocatore(nome,cognome,email,password,avatar, annoDiNascita, sito,facebook,twitter,linkedIn);
			facade.aggiungiPersona(g);
			HttpSession session=request.getSession();
			session.setAttribute("CurrentPersona", g);
			application.setAttribute("Societa", facade.GetSocieta());
			nextPage="/HomePage.jsp";
		} catch (NotNullableFieldException e) {
			request.setAttribute("StartupSignUpError","completare tutti i campi contrassegnati da *");
			nextPage = "/SignUp.jsp";
		} catch (PersonaGiaEsistenteException e) {
			
			request.setAttribute("StartupSignUpError","l'email inserita e gia stata utilizzata");
			nextPage = "/SignUp.jsp";
		}
		
		RequestDispatcher rd = application.getRequestDispatcher(response.encodeURL(nextPage));
		rd.forward(request, response);
		return;
	}
}
