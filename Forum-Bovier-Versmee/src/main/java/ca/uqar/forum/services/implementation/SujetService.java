package ca.uqar.forum.services.implementation;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.uqar.forum.dao.SujetDAO;
import ca.uqar.forum.entities.Sujet;
import ca.uqar.forum.services.ISujetService;

@Service
@Transactional
public class SujetService implements ISujetService{
	/* Debug */
	private final static Logger logger = LoggerFactory.getLogger(SujetService.class);

	@Resource
	private SujetDAO	sujetDAO;
	
	public List<Sujet> findAll()
	{
		logger.debug("Appel de la methode findAll Sujet");
		
		List<Sujet>  list = sujetDAO.findAll();
		if (list != null)
			logger.debug("FindAll Sujet result liste size = {}",list.size());
		return list;
	}

	public Sujet findById(Long idSubject)
	{
		logger.debug("Appel de la methode findId sur "+idSubject);
	
		Sujet  subject = sujetDAO.findById(idSubject);
		return subject;
	}
		
	public void saveSujet(Sujet newSujet)
	{
		Date today = new Date();
		
		newSujet.setDateCreation(today);
		logger.debug("Appel de la methode saveSujet");
		logger.debug("Save du Sujet : ["+newSujet.toString()+"]");
		sujetDAO.save(newSujet);
	}

	public void delSujet(Sujet sujet)
	{
		logger.debug("Appel de la methode delSujet");
		sujetDAO.delete(sujet);
	}
}
