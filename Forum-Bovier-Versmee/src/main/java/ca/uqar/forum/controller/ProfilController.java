package ca.uqar.forum.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.uqar.forum.entities.Membre;
import ca.uqar.forum.services.IMembreService;

@Controller
@RequestMapping(value="/profil")
public class ProfilController
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
	public String displayProfil(ModelMap model, HttpSession session, HttpServletRequest request,final RedirectAttributes redirectAttributes)
	{
		Membre createur = (Membre) session.getAttribute("membreSession");
		if (createur == null){
			redirectAttributes.addFlashAttribute("INFORMATION_MESSAGE","Vous devez être connecté pour effectuer cette action.");
			return ("redirect:/connexion");
		}
		model.addAttribute("modifMembre", createur);
		return "profil";
	}
	/*
	###############################
	#                             #
	#           POST              #
	#                             #
	###############################
	*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String updateMembre(@Valid @ModelAttribute(value = "modifMembre") Membre membre, BindingResult result, ModelMap model, HttpSession session, final RedirectAttributes redirectAttributes)
	{
		/* General var needed by view */
		
		Membre courrant = (Membre) session.getAttribute("membreSession");
		
		membre.setPseudo(courrant.getPseudo());
		membre.setId(courrant.getId());
		membre.setPouvoir(courrant.getPouvoir());
		membre.setValide(courrant.getValide());
		logger.debug("Value in form = [{}]",membre.toString());
		
		if (result.hasErrors()) {
			logger.debug("ERROR ON SUBMIT - ModifMember (Number error :{}) MSG => '{}'",result.getErrorCount(), result.getAllErrors().get(0).toString());
			model.addAttribute("ERROR_MESSAGE","Le membre n'a pas ete modifié, une erreur est survenue.");
			return ("profil");
		}

		/* Sauvegarde la modification du profil en BDD */
		membreService.saveMembre(membre);
		session.removeAttribute("membreSession");
		session.setAttribute("membreSession", membre);
		
		redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE","La modification a bien ete effectuer.");
		return ("redirect:/profil");
	}
	
	@RequestMapping(value = "/suppr", method = RequestMethod.POST)
	public String suppressionCompte(@ModelAttribute(value = "supprMembre") Membre membre, ModelMap model, HttpSession session, final RedirectAttributes redirectAttributes)
	{		
		logger.debug("Value in form = [{}]",membre.toString());
		
		/* Sauvgarde la suppression du profil en BDD */
		membreService.supprMembre((Membre) session.getAttribute("membreSession"));
		session.removeAttribute("membreSession");
		redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE","La suppression de votre compte a bien ete effectuer.");
		return ("redirect:/");
	}
}
