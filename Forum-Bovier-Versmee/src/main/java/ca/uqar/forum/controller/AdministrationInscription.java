package ca.uqar.forum.controller;

import java.util.Date;
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
@RequestMapping(value="/administration-inscriptions")
public class AdministrationInscription
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
	public String displayMembers(ModelMap model, HttpSession session, HttpServletRequest request)
	{
		List<Membre> liste = membreService.findByValideIsNull();
		
		model.addAttribute("membreList", liste);
		return "administrationInscription";
	}
	/*
	###############################
	#                             #
	#           POST              #
	#                             #
	###############################
	*/
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String inscriptionMembreForm(ModelMap model, HttpSession session,final RedirectAttributes redirectAttributes,
									@PathVariable("id") String idMembreToValidate, HttpServletRequest request)
	{
		Membre membre = membreService.findById(Long.parseLong(idMembreToValidate));
		Date today = new Date();
		
		logger.debug("Je suis la après findById");
		membre.setValide(today);
		membreService.saveMembre(membre);
		return ("redirect:/administration-inscriptions");		
	}
}
