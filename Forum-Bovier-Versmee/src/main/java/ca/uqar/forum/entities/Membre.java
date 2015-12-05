package ca.uqar.forum.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="membre")
public class Membre
{
	/*
	###############################
	#                             #
	# Constructeur / Destructeur  #
	#                             #
	###############################
	*/
	/*
	###############################
	#                             #
	#         Attribut            #
	#                             #
	###############################
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long	id;

	@NotEmpty
	@Column(name = "identifiant", nullable = false, unique = true)
	private String	pseudo;
	
	@NotEmpty
	@Column(name = "nom", nullable = false)
	private String	name;
	
	@NotEmpty
	@Column(name = "courriel", nullable = false)
	private String	email;
	
	@NotEmpty
	@Column(name = "internet_protocol", nullable = false)
	private String	ip;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "date_creation", nullable = false)
	private Date	dateCreation;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "date_derniere_authentification")
	private Date	dateDerniereAuthentification = null;
	
	@Column(name = "validation")
	private Date	valide = null;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "supprimer")
	private Date	deleted = null;
	
	@NotEmpty
	@Column(name = "mot_de_passe", nullable = false)
	private String	password;
	
	@Column(name = "pouvoir", nullable = false)
	private int		pouvoir = 0;
	
	/*
	###############################
	#                             #
	#         Relation            #
	#                             #
	###############################
	*/
	/*
	###############################
	#                             #
	#         Getter              #
	#                             #
	###############################
	*/
	public long getId() {
		return id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getIp() {
		return ip;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public Date getDateDerniereAuthentification() {
		return dateDerniereAuthentification;
	}
	public Date getValide() {
		return valide;
	}
	public Date getDeleted() {
		return deleted;
	}
	public String getPassword() {
		return password;
	}
	public int getPouvoir() {
		return pouvoir;
	}
	/*
	###############################
	#                             #
	#         Setter              #
	#                             #
	###############################
	*/
	public void setId(long id) {
		this.id = id;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public void setDateDerniereAuthentification(Date dateDerniereAuthentification) {
		this.dateDerniereAuthentification = dateDerniereAuthentification;
	}
	public void setValide(Date valide) {
		this.valide = valide;
	}
	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPouvoir(int pouvoir) {
		this.pouvoir = pouvoir;
	}
	/*
	###############################
	#                             #
	#           Methods           #
	#                             #
	###############################
	*/
	@Override
	public String toString()
	{
		String message;
		
		message="name : '"+this.getName()+ 
				"' email : '"+this.getEmail()+
				"' pseudo : '"+this.getPseudo()+
				"' id : "+this.getId();
		return (message);
	}
}
