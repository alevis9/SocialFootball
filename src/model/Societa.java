package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import exceptions.*;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "findAllUtenti", query = "SELECT u FROM Utente u"),
		@NamedQuery(name = "findAllAttivita", query = "SELECT a FROM Attivita a") })
public class Societa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String nome;

	@OneToMany(cascade = { CascadeType.ALL }, fetch= FetchType.EAGER)
	@JoinColumn(name = "comunita_id")
	private List<Persona> persone;

	@OneToMany(cascade = { CascadeType.ALL }, fetch= FetchType.EAGER)
	@JoinColumn(name = "comunita_id")
	private List<Partita> attivita;

	public Societa() {
		this.persone = new ArrayList<Persona>();
	}

	private Persona personaByEmail(String email) {
		Persona res = null;
		for (Persona u : this.persone)
			if (u.getEmail().equalsIgnoreCase(email)) {
				res = u;
				break;
			}
		return res;
	}

	public Persona GetUser(String email) throws PersonaNonEsistenteException {
		Persona res = personaByEmail(email);
		if (res == null)
			throw new PersonaNonEsistenteException();
		return res;
	}

	public void aggiungiUtente(Persona nuovoUtente)
			throws PersonaGiaEsistenteException {
		if(this.persone==null)
			this.persone = new ArrayList<Persona>();
		if (personaByEmail(nuovoUtente.getEmail()) != null)
			throw new PersonaGiaEsistenteException();
		else
			this.persone.add(nuovoUtente);
	}

	public void addAttivita(Partita a) {
		if (this.attivita == null)
			this.attivita = new ArrayList<Partita>();
		this.attivita.add(a);
	}

	public List<Partita> getAttivita() {
		return attivita;
	}

	public List<Giocatore> getGiocatori() {
		List<Giocatore> giocatori = new ArrayList<Giocatore>();
		for (Persona p : persone) {
			if (p instanceof Giocatore)
				giocatori.add((Giocatore) p);
		}
		return giocatori;
	}

	public List<Presidente> getPresidenti() {
		List<Presidente> presidenti = new ArrayList<Presidente>();
		for (Presidente p : presidenti) {
			if (p instanceof Presidente)
				presidenti.add((Presidente) p);
		}
		return presidenti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Societa other = (Societa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
