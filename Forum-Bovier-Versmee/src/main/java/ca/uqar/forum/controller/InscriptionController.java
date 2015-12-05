package ca.uqar.forum.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.uqar.forum.entities.Membre;
import ca.uqar.forum.services.IMembreService;

@Controller
@RequestMapping(value="/inscription")
public class InscriptionController
{
	/* Debug */
	private final static Logger logger = LoggerFactory.getLogger(ConnexionController.class);
	
	@Resource
	IMembreService 			membreService;
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
	public String displayInscription(ModelMap model, HttpSession session, HttpServletRequest request)
	{
		model.addAttribute("addMembre", new Membre());
		return "inscription";
	}
	/*
	###############################
	#                             #
	#           POST              #
	#                             #
	###############################
	*/
	public void validate(Object target, Errors errors) {
 		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pseudo",
 				"error.pseudo", "Ce champ ne peut pas Ãªtre vide.");
     }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addMembre(@Valid @ModelAttribute(value = "addMembre") Membre membre, BindingResult result, ModelMap model, HttpSession session, final RedirectAttributes redirectAttributes)
	{
		
		logger.debug("Value in form = [{}]",membre.toString());
		
		if (result.hasErrors()) {
			logger.debug("ERROR ON SUBMIT - AddMember (Number error :{})",result.getErrorCount());
			model.addAttribute("ERROR_MESSAGE","Le membre n'a pas ete ajoute en base de donné, une erreur est survenue.");
			return ("inscription");
		}

		/* création du membre dans BDD */
		try {
			membreService.saveMembre(membre);
		} catch (DataIntegrityViolationException e) {
			model.addAttribute("ERROR_MESSAGE","Un membre possède déjà ce pseudo.");
			return ("inscription");
		}
		
		redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE","L'ajout du membre c'est bien déroulé.");
		return ("redirect:/");
	}
}
