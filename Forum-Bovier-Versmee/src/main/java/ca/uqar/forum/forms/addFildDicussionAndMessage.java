package ca.uqar.forum.forms;

import java.util.Date;

public class addFildDicussionAndMessage
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
	private String	title;
	private String	idSujet;
	private String	idFilDiscussion;
	private String	contenue;
	private Date	dateCreation;
	/*
	###############################
	#                             #
	#         Getter              #
	#                             #
	###############################
	*/
	public Date getDateCreation() {
		return dateCreation;
	}
	public String getTitle() {
		return title;
	}
	public String getIdSujet() {
		return idSujet;
	}
	public String getIdFilDiscussion() {
		return idFilDiscussion;
	}
	public String getContenue() {
		return contenue;
	}
	/*
	###############################
	#                             #
	#         Setter              #
	#                             #
	###############################
	*/
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public void setIdSujet(String idSujet) {
		this.idSujet = idSujet;
	}
	public void setIdFilDiscussion(String idFilDiscussion) {
		this.idFilDiscussion = idFilDiscussion;
	}
	public void setContenue(String contenue) {
		this.contenue = contenue;
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
		
		message="' Date création : '"+this.getDateCreation()+
				"' idsujet : "+this.getIdSujet()+
				"' idFildiscussion : "+this.getIdFilDiscussion()+
				"' Contenu : "+this.getContenue()+
				"' title du fil de discussion : "+this.getTitle();
		return (message);
	}

}
