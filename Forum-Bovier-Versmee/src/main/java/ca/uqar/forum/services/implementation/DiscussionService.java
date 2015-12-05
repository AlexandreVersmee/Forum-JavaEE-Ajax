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

import ca.uqar.forum.dao.DiscussionDAO;
import ca.uqar.forum.dao.MessageDAO;
import ca.uqar.forum.entities.FilDiscussion;
import ca.uqar.forum.entities.Membre;
import ca.uqar.forum.entities.Message;
import ca.uqar.forum.entities.Sujet;
import ca.uqar.forum.services.IDiscussionService;
import ca.uqar.forum.services.IMessageService;
import ca.uqar.forum.services.ISujetService;
import ca.uqar.forum.forms.addFildDicussionAndMessage;

@Service
@Transactional
public class DiscussionService  implements IDiscussionService
{
	/* Debug */
	private final static Logger logger = LoggerFactory.getLogger(DiscussionService.class);

	@Resource
	private DiscussionDAO		discussionDAO;
	
	@Resource
	private MessageDAO			messageDAO;
	
	@Resource
	ISujetService				sujetService;
	@Resource
	IMessageService				messageService;
	
	@PersistenceContext
	private EntityManager 		entityManager;
	
	public FilDiscussion findById(Long idDiscussion)
	{
		logger.debug("Appel de la methode findById");
		
		FilDiscussion discussion = discussionDAO.findById(idDiscussion);
		return (discussion);
	}
	
	public void saveDiscussionAndMessage(addFildDicussionAndMessage form, Membre createur)
	{
		FilDiscussion discussionToAdd 	= new FilDiscussion();
		Message messageTodadd 			= new Message();
		Date today 						= new Date();

		/* Set informations of new Discussion */
		discussionToAdd.setMembre(createur);
		/* Seek subject parent of discussion*/
		Sujet subjectParent =  sujetService.findById(Long.parseLong(form.getIdSujet()));
		discussionToAdd.setSujet(subjectParent);
		discussionToAdd.setDateCreation(today);
		discussionToAdd.setDateDerniereModification(today);
		discussionToAdd.setTitle(form.getTitle());
		
		/* Set informations of new Message */
		messageTodadd.setMembre(createur);
		messageTodadd.setDateCreation(today);
		messageTodadd.setDateDerniereModification(today);
		messageTodadd.setTexte(form.getContenue());
		
		FilDiscussion newItem = discussionDAO.save(discussionToAdd);
		
		/* Get discussion object */
		FilDiscussion fil = discussionDAO.findById(newItem.getId());
		
		messageTodadd.setFildiscussion(fil);
		
		messageDAO.save(messageTodadd);
		messageService.updateParentId();
	}
	
	public void saveDiscussion(FilDiscussion newDiscussion)
	{
		Date today = new Date();
		
		newDiscussion.setDateCreation(today);
		newDiscussion.setDateDerniereModification(today);
		logger.debug("Appel de la methode saveDiscussion");
		logger.debug("Save de la discussion : ["+newDiscussion.toString()+"]");
		discussionDAO.save(newDiscussion);
	}
	/*
	###############################
	#                             #
	#        CUSTOM QUERY JPQL    #
	#                             #
	###############################
	*/
	@Override
	public List<FilDiscussion> readBySujet(Long idSujet) {
		logger.debug("Appel de la méthode readBySujetId");

		logger.debug("SELECT f FROM FilDiscussion f WHERE f.sujet_id = "+idSujet);

		@SuppressWarnings("unchecked")
		TypedQuery<FilDiscussion> query =  (TypedQuery<FilDiscussion>) entityManager.createQuery("SELECT f FROM FilDiscussion f WHERE f.sujet.id = "+idSujet);
		List<FilDiscussion> FilDiscussionList = query.getResultList();
		logger.debug("Nombre d'elements dans la liste:[{}])", FilDiscussionList.size());
		return (FilDiscussionList);
	}
}
