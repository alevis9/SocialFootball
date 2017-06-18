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

@WebServlet("/StartupPresidenteController")
public class PresidenteSignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(beanName="SocialFootballFacade")
	private SocialFootballFacade facade;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String nextPage = null;
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String descBreve = request.getParameter("descBreve");
		String desc = request.getParameter("desc");
		String avatar = request.getParameter("urlImg");
		String annoFondazione = request.getParameter("annoFondazione");
		String motto = request.getParameter("motto");
		String sito = request.getParameter("sito");
		String facebook = request.getParameter("facebook");
		String twitter = request.getParameter("twitter");
		String linkedIn = request.getParameter("linkedIn");
		ServletContext application = getServletContext();
		try {
			Presidente p = new Presidente(nome,email,password,descBreve,desc,avatar,motto,sito,facebook,twitter,linkedIn);
			p.setDataCreazione(annoFondazione);
			facade.aggiungiPersona(p);
			HttpSession session=request.getSession();
			session.setAttribute("CurrentPersona", p);
			application.setAttribute("Societa", facade.GetSocieta());
			nextPage="/HomePage.jsp";
		} catch (NotNullableFieldException e) {
			request.setAttribute("PresidenteSignUpError","completare tutti i campi contrassegnati da *");
			nextPage = "/SignUp.jsp";
		} catch (PersonaGiaEsistenteException e) {
			request.setAttribute("PresidenteSignUpError","l'email inserita e gia stata utilizzata");
			nextPage = "/SignUp.jsp";
		}
		RequestDispatcher rd = application.getRequestDispatcher(response.encodeURL(nextPage));
		rd.forward(request, response);
	}
}
