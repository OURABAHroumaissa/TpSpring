package aston.correction.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="typePersonne")
public abstract class Personne {
	@Id
	@GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "civility", length = 10)
	private Civilite civilite;
	@Column(name = "lastname", length = 255)
	private String nom;
	@Column(name = "firstname", length = 255)
	private String prenom;
	@Column(length = 255)
	private String email;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "adress_id")
	private Adresse adresse;

	public Personne() {
		super();
	}

	public Personne(Civilite civilite, String nom, String prenom, String email) {
		super();
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public Personne(Long id, Civilite civilite, String nom, String prenom, String email) {
		super();
		this.id = id;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

}
