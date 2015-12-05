package ca.uqar.forum.services;

import java.util.List;

import ca.uqar.forum.entities.FilDiscussion;
import ca.uqar.forum.entities.Membre;
import ca.uqar.forum.forms.addFildDicussionAndMessage;

public interface IDiscussionService
{
	public FilDiscussion findById(Long idDiscussion);
	
	public void saveDiscussion(FilDiscussion newDiscussion);

	public void saveDiscussionAndMessage(addFildDicussionAndMessage form, Membre createur);
	/*
	###############################
	#                             #
	#        CUSTOM QUERY         #
	#                             #
	###############################
	*/
	public List<FilDiscussion> readBySujet(Long idSujet);
	
	
}
