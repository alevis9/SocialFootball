package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import exceptions.NotNullableFieldException;

@Entity
public class Giocatore extends Persona {

	@Column(nullable = true)
	private String cognome;

	@Column(nullable = true)
	private String annoDiNascita;

	@Column(nullable = true, length = 10000)
	private String biografia;
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<Giocatore> amici;

	public Giocatore(String nome, String cognome, String email, String password, String avatar, String annoDiNascita,
			String sitoWeb, String facebook, String twitter, String linkedIn) throws NotNullableFieldException {
		super(nome, email, password, avatar, sitoWeb, facebook, twitter, linkedIn);
		this.annoDiNascita = annoDiNascita;
		this.cognome = cognome;

	}

	public Giocatore(String nome, String email, String password) throws NotNullableFieldException {
		super(nome, email, password);

	}

	public Giocatore() {

	}

	public void addGiocatoreAmico(Giocatore m) {
		if (this.amici == null)
			this.amici = new ArrayList<Giocatore>();
		this.amici.add(m);
	}

	public List<Giocatore> getAmici() {
		if (this.amici == null)
			this.amici = new ArrayList<Giocatore>();
		return this.amici;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getAnnoDiNascita() {
		return annoDiNascita;
	}

	public void setAnnoDiNascita(String annoDiNascita) {
		this.annoDiNascita = annoDiNascita;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((annoDiNascita == null) ? 0 : annoDiNascita.hashCode());
		result = prime * result + ((biografia == null) ? 0 : biografia.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Giocatore other = (Giocatore) obj;
		if (annoDiNascita == null) {
			if (other.annoDiNascita != null)
				return false;
		} else if (!annoDiNascita.equals(other.annoDiNascita))
			return false;
		if (biografia == null) {
			if (other.biografia != null)
				return false;
		} else if (!biografia.equals(other.biografia))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		return true;
	}

}
