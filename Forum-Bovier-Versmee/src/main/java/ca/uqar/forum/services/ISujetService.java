package ca.uqar.forum.services;

import java.util.List;

import ca.uqar.forum.entities.Sujet;

public interface ISujetService {
	
	public List<Sujet> findAll();
		
	public Sujet findById(Long idSubject);
	
	public void saveSujet(Sujet newSujet);

	public void delSujet(Sujet sujet);
}
