package ca.uqar.forum.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="sujet")
public class Sujet
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
	
	@Column(name = "titre", nullable = false, unique = true)
	private String	title;
	
	@Column(name = "date_creation", nullable = false)
	private Date	dateCreation;
	
	@Column(name = "date_derniere_modification")
	private Date	dateDerniereModification;
	
	/*
	###############################
	#                             #
	#         Relation            #
	#                             #
	###############################
	*/	
	@OneToMany(mappedBy="sujet", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<FilDiscussion> discussion;
	
	@OneToOne
	private Membre membre;
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
	public Membre getMembre() {
		return membre;
	}
	public String getTitle() {
		return title;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public Date getDateDerniereModification() {
		return dateDerniereModification;
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
	public void setMembre(Membre membre) {
		this.membre = membre;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public void setDateDerniereModification(Date dateDerniereModification) {
		this.dateDerniereModification = dateDerniereModification;
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
		
		message="id : '"+this.getId()+ 
				"' Date création : '"+this.getDateCreation()+
				"' date dernière modification : '"+this.getDateDerniereModification()+
				"' menbre id createur : "+this.getMembre().getId()+
				"' Title : "+this.getTitle();
		return (message);
	}
}
