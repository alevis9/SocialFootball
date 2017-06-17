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
import model.Persona;
import service.SocialFootballFacade;

@WebServlet("/FollowStartupController")
public class FollowPresidenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(beanName = "SocialStartupFacade")
	private SocialFootballFacade facade;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String emailStartupDaSeguire = request.getParameter("startupDaSeguire");

		HttpSession session = request.getSession();
		Persona p = (Persona) session.getAttribute("CurrentUser");
		facade.aggiungiFollowingPresidente(p, emailStartupDaSeguire);

		ServletContext application = getServletContext();

		RequestDispatcher rd = application.getRequestDispatcher(response
				.encodeURL("/HomePage.jsp"));
		rd.forward(request, response);
	}
}
