package service;


import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import exceptions.*;
import model.*;
//importare il seguente quando si deve chiamare la facade
//@EJB(beanName="SocialFootballFacade")
//private SocialFootballFacade facade;


@Stateless(name = "SocialFootballFacade")
public class SocialFootballFacade {


	public SocialFootballFacade() {
	}


	@PersistenceContext(unitName = "SocialFootball")
	private EntityManager em;


	// viene chiamato solo la prima volta dagli amministratori di sistema
	public void initSocialFootball() {
		Societa s = new Societa();
		s.setNome("SocialFootballSocieta");
		CreaDatiFinti(s);
		em.persist(s);		
	}


	public Societa GetSocieta() {
		Query q = em.createQuery("SELECT s FROM Societa s");
		return  (Societa) q.getSingleResult();
	}


	public Persona GetPersona(String email) throws PersonaNonEsistenteException {
		Societa s = em.find(Societa.class, 1);
		Persona p = s.GetPersona(email);
		return p;
	}


	public Persona GetPersona(String email, String password)
			throws PasswordNonValidaException, PersonaNonEsistenteException {
		Societa s = em.find(Societa.class, 1);
		Persona u = s.GetPersona(email);
		u.checkPassword(password);
		return u;
	}
	
	@SuppressWarnings("unchecked")
	public List<Partita> GetTop90Attivita()
	{
		//Query q = em.createQuery("LIMIT 90 SELECT a FROM Attivita a");
		Query q = em.createQuery("SELECT p FROM Partita p");
		return (List<Partita>)q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Partita> GetAllPartitaPersona(String emailPersona)
	{
		Query q = em.createQuery("SELECT p FROM Partita p, Persona pe WHERE p.persona==p.id && p.email=="+emailPersona);
		return (List<Partita>)q.getResultList();
	}
	public void aggiungiPersona(Persona nuovaPersona)
			throws PersonaGiaEsistenteException {
		Societa s = GetSocieta();
		s.aggiungiPersona(nuovaPersona);
		 em.persist(nuovaPersona);
		 em.merge(s);
	}


	public void aggiungiPartita(Partita p, Persona pe) {
		Societa s = GetSocieta();
		s.addPartita(p);


		em.persist(p);
		em.merge(s);
		em.merge(pe);
	}


	public void aggiungiFollowingCalciatore(Persona p, String emailPersonaDaSeguire) {
		try {
			Giocatore c = (Giocatore) p;
			c.addGiocatoreAmico((Giocatore) GetPersona(emailPersonaDaSeguire));
			em.merge(p);
		} catch (Exception e) {
		}
	}


	public void aggiungiFollowingPresidente(Persona p, String emailPresidenteDaSeguire) {
		try {
			p.addPresidente((Presidente) GetPersona(emailPresidenteDaSeguire));
			em.merge(p);
		} catch (Exception e) {
		}
	}


	public void modificaGiocatore(Giocatore g, String avatar, String biografia,
			String annoDiNascita, String sitoWeb, String facebook,
			String twitter, String linkedIn) {
		g.setAvatar(avatar);
		g.setBiografia(biografia);
		g.setFacebook(facebook);
		g.setLinkedIn(linkedIn);
		g.setSitoWeb(sitoWeb);
		g.setTwitter(twitter);
		g.setAnnoDiNascita(annoDiNascita);
		em.merge(g);
	}


	public void modificaPresidente(Presidente p, String dataCreazione,
			String descrizioneBreve, String descrizione, String avatar,
			String motto, String sitoWeb, String facebook, String twitter,
			String linkedIn) {
		p.setAvatar(avatar);
		p.setDataCreazione(dataCreazione);
		p.setDescrizione(descrizione);
		p.setDescrizioneBreve(descrizioneBreve);
		p.setMotto(motto);
		p.setSitoWeb(sitoWeb);
		p.setFacebook(facebook);
		p.setTwitter(twitter);
		p.setLinkedIn(linkedIn);
		em.merge(p);
	}


	private void CreaDatiFinti(Societa soc) {
		try {
			Presidente Sensi = new Presidente(
					"Franco Sensi",
					"FrancoSensi@asRoma.com",
					"Franco",
					"ASROMA",
					"",
					"",
					"", "",
					"https://www.facebook.com/asroma", "https://twitter.com/asroma", "");


			
			soc.aggiungiPersona(Sensi);
			
			Partita romalazio = new Partita("derby Roma-Lazie",
					"Oggi 60.000 spettatori", null,
					"",
					"",
					"");

			
			soc.addPartita(romalazio);


		} catch (Exception e) {
		}
	}
}
