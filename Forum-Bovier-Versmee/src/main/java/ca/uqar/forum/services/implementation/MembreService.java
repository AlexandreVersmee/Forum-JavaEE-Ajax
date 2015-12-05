package ca.uqar.forum.services.implementation;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.uqar.forum.dao.MembreDAO;
import ca.uqar.forum.entities.Membre;
import ca.uqar.forum.services.IMembreService;

@Service
@Transactional
public class MembreService implements IMembreService
{
	/* Debug */
	private final static Logger logger = LoggerFactory.getLogger(MembreService.class);

	@Resource
	private MembreDAO	membreDAO;
	
	public Membre findByPseudo(String membrePseudo) {
		logger.debug("Appel de la methode findByPseudo sur "+ membrePseudo);
		Membre membreFind = membreDAO.findByPseudo(membrePseudo);
		return membreFind;
	}

	public Membre findById(long parseLong)
	{
		logger.debug("Appel de la methode findById Membre");

		Membre membre = membreDAO.findById(parseLong);
		return membre;
	}
	
	public List<Membre> findByValideIsNull()
	{
		logger.debug("Appel de la methode findAll Membre");
		
		List<Membre>  list = membreDAO.findByValideIsNull();
		logger.debug("FindAll Membre result liste size = {}",list.size());
		return list;
	}

	public List<Membre> findByValideIsNotNullAndDeleted(Object object)	
	{
		logger.debug("Appel de la methode findValidAndDeleted");
		
		List<Membre>  list = membreDAO.findByValideIsNotNullAndDeleted(object);
		logger.debug("FindAll Membre result liste size = {}",list.size());
		return list;
	}
	
	public void saveMembre(Membre membre)
	{
		Date today = new Date();
		
		logger.debug("Appel de la methode saveMembre");
		membre.setDateCreation(today);
		membreDAO.save(membre);
	}

	public void updateMembre(Membre membre)
	{
		Date today = new Date();
		
		logger.debug("Appel de la methode updateMembre");
		membre.setDateDerniereAuthentification(today);
		membreDAO.save(membre);
	}
	
	public void supprMembre(Membre membre) {
		logger.debug("Appel de la methode supprMembre");
		membre.setDeleted(new Date());
		membreDAO.save(membre);
	}

}
