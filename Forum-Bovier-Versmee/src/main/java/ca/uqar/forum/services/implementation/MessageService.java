package ca.uqar.forum.services.implementation;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.uqar.forum.dao.MessageDAO;
import ca.uqar.forum.entities.FilDiscussion;
import ca.uqar.forum.entities.Membre;
import ca.uqar.forum.entities.Message;
import ca.uqar.forum.services.IDiscussionService;
import ca.uqar.forum.services.IMessageService;

@Service
@Transactional
public class MessageService implements IMessageService
{
	/* Debug */
	private final static Logger logger = LoggerFactory.getLogger(MessageService.class);

	@Resource
	private MessageDAO		messageDAO;
	
	@Resource
	IDiscussionService		discussionService;
	
	@Resource
	IMessageService			messageService;
	
	@PersistenceContext
	private EntityManager	entityManager;
	
	
	public List<Message> findByAll()
	{
		logger.debug("Appel de la méthode findByAll");	
		return messageDAO.findAll();
	}
	
	public Message findById(Long id)
	{
		logger.debug("Appel de la méthode findById");
		return (messageDAO.findById(id));
	}
	
	public void saveMessage(Message messageToSave, String idDiscussion, Membre createur)
	{
		logger.debug("Appel de la méthode saveMessage");
		Date today = new Date();
		
		/* Get discussion parent */
		FilDiscussion  discussion = discussionService.findById(Long.parseLong(idDiscussion));
		messageToSave.setMembre(createur);
		messageToSave.setFildiscussion(discussion);
		messageToSave.setDateCreation(today);
		messageToSave.setDateDerniereModification(today);
		messageDAO.save(messageToSave);
		messageService.updateParentId();
	}
	
	public void saveAnswer(Message answerToSave, String idDiscussion, String idParent, Membre createur)
	{
		logger.debug("Appel de la méthode saveAnswer");
		Date today = new Date();
		
		/* Get discussion parent */
		FilDiscussion  discussion = discussionService.findById(Long.parseUnsignedLong(idDiscussion));
		answerToSave.setMembre(createur);
		answerToSave.setFildiscussion(discussion);
		answerToSave.setParentId(Long.parseUnsignedLong(idParent));
		answerToSave.setDateCreation(today);
		answerToSave.setDateDerniereModification(today);
		messageDAO.save(answerToSave);
	}
	
	public int updateAnswer(Message newContent, Long idMessage)
	{
		logger.debug("Appel de la méthode upadteAnswer");
		Date today = new Date();

		/* Find Message */
		Message  answerToUpdate = messageService.findById(idMessage);
		String old = answerToUpdate.getTexte();
		
		answerToUpdate.setTexte(newContent.getTexte());
		answerToUpdate.setDateDerniereModification(today);
		messageDAO.save(answerToUpdate);
		logger.debug("new =[{}], old = [{}]",newContent.getTexte(),answerToUpdate.getTexte());
		if (old.equals(newContent.getTexte())){
			return (1);
		}
		return(0);
	}
	
	public void deleteMessage(String idParent)
	{
		logger.debug("Appel de la méthode deleteMessage sur id = "+idParent);
		
		/* Find Message */
		Message  messageToDelete = messageDAO.findById(Long.parseUnsignedLong(idParent));
		logger.debug("Message = "+messageToDelete.toString());
		messageDAO.delete(messageToDelete);
	}
	/*
	###############################
	#                             #
	#        CUSTOM QUERY JPQL    #
	#                             #
	###############################
	*/

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> readByFilDiscussion(Long idDiscussion)
	{
		logger.debug("Appel de la méthode readByFilDiscussion");
		logger.debug("SELECT m FROM Message m WHERE m.fildiscussion.id = "+idDiscussion+" ORDER BY m.parentId, m.dateCreation");
		
		TypedQuery<Message> query =  (TypedQuery<Message>) entityManager.createQuery("SELECT m FROM Message m WHERE m.fildiscussion.id = "+idDiscussion+" ORDER BY m.parentId, m.dateCreation");
		List<Message> messageListe = query.getResultList();
		return (messageListe);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void updateParentId()
	{
		TypedQuery<Message> query =  (TypedQuery<Message>) entityManager.createQuery("SELECT m FROM Message m WHERE m.parentId IS NULL");
	
		List<Message> messageListe = query.getResultList();
		logger.debug("size = "+messageListe.size());
		for (int i = 0; i < messageListe.size(); i++)
		{
			messageListe.get(i).setParentId(messageListe.get(i).getId());
			messageDAO.save(messageListe.get(i));
		}
	}
}
