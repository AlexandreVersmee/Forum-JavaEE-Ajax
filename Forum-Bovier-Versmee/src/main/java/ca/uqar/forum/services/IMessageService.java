package ca.uqar.forum.services;

import java.util.List;

import ca.uqar.forum.entities.Membre;
import ca.uqar.forum.entities.Message;

public interface IMessageService
{
	public List<Message> findByAll();
	
	public Message findById(Long id);
	
	public void saveMessage(Message messageToSave, String idDiscussion, Membre createur);
	
	public void saveAnswer(Message answerToSave, String idDiscussion, String idParent, Membre createur);
	
	public int updateAnswer(Message answerToUpdate, Long idMessage);
	
	public void deleteMessage( String idParent);
	/*
	###############################
	#                             #
	#        CUSTOM QUERY         #
	#                             #
	###############################
	*/
	public List<Message> readByFilDiscussion(Long idDiscussion);
	
	public void updateParentId();
}
