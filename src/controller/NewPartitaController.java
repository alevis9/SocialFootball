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
import exceptions.NotNullableFieldException;
import model.*;
import service.*;

@WebServlet("/NewPartitaController")
public class NewPartitaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(beanName="SocialFootballFacade")
	private SocialFootballFacade facade;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nextPage = null;
		String titolo = request.getParameter("titolo");
		String testo = request.getParameter("testo");
		String url = request.getParameter("url");
		String urlImg = request.getParameter("imageUrl");
		String urlVideo = request.getParameter("videoUrl");
		
		HttpSession session = request.getSession();
		Persona p = (Persona) session.getAttribute("CurrentUser");

		try {
			Partita partita = new Partita(titolo, testo, p, url, urlImg,urlVideo);
			facade.aggiungiPartita(partita, p);
			nextPage = "/HomePage.jsp";
		} catch (NotNullableFieldException e) {
			request.setAttribute("newPartitaError","completare i campi titolo e testo");
			nextPage = "/NewPartita.jsp";
		}

		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(response.encodeURL(nextPage));
		rd.forward(request, response);
	}

}
