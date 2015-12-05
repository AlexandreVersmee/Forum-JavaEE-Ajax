package ca.uqar.forum.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.uqar.forum.entities.Membre;
import ca.uqar.forum.services.IMembreService;

@Controller
@RequestMapping(value="/administration-pouvoirs")
public class AdministrationPouvoirController
{
	/* Debug */
	private final static Logger logger = LoggerFactory.getLogger(AdministrationInscription.class);

	@Resource
	private IMembreService	membreService;
	/*
	###############################
	#                             #
	#       General Methods       #
	#                             #
	###############################
	*/
	/*
	###############################
	#                             #
	#            GET              #
	#                             #
	###############################
	*/
	@RequestMapping(method = RequestMethod.GET)
	public String displayPowerAdministration(ModelMap model, HttpSession session, HttpServletRequest request)
	{
		List<Membre> liste = membreService.findByValideIsNotNullAndDeleted(null);
		
		model.addAttribute("membreList", liste);
		return "administrationPouvoir";
	}
	/*
	###############################
	#                             #
	#           POST              #
	#                             #
	###############################
	*/
	
	@RequestMapping(value = "utilisateur/{id}", method = RequestMethod.POST)
	public String giveUtilisateurPouvoir(ModelMap model, HttpSession session,final RedirectAttributes redirectAttributes,
									@PathVariable("id") String idMembreToValidate, HttpServletRequest request)
	{
		Membre membre = membreService.findById(Long.parseLong(idMembreToValidate));
		
		logger.debug("Je suis la après findById");
		membre.setPouvoir(0);
		membreService.saveMembre(membre);
		return ("redirect:/administration-pouvoirs");		
	}
	
	@RequestMapping(value = "moderateur/{id}", method = RequestMethod.POST)
	public String giveModerateurPouvoir(ModelMap model, HttpSession session,final RedirectAttributes redirectAttributes,
									@PathVariable("id") String idMembreToValidate, HttpServletRequest request)
	{
		Membre membre = membreService.findById(Long.parseLong(idMembreToValidate));
		
		logger.debug("Je suis la après findById");
		membre.setPouvoir(1);
		membreService.saveMembre(membre);
		return ("redirect:/administration-pouvoirs");		
	}
	
	@RequestMapping(value = "administrateur/{id}", method = RequestMethod.POST)
	public String giveAdministrateurPouvoir(ModelMap model, HttpSession session,final RedirectAttributes redirectAttributes,
									@PathVariable("id") String idMembreToValidate, HttpServletRequest request)
	{
		Membre membre = membreService.findById(Long.parseLong(idMembreToValidate));
		
		logger.debug("Je suis la après findById");
		membre.setPouvoir(2);
		membreService.saveMembre(membre);
		return ("redirect:/administration-pouvoirs");		
	}

}
