package ca.uqar.forum.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.uqar.forum.entities.Membre;
import ca.uqar.forum.entities.Sujet;
import ca.uqar.forum.services.ISujetService;

@Controller
@RequestMapping(value="/sujets")
public class SujetListingController
{	
	@Resource
	ISujetService			sujetService;
	
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
	public String displaySubjectList(ModelMap model, HttpSession session, HttpServletRequest request)
	{
		List<Sujet> sujetListe	= sujetService.findAll();
		
		model.addAttribute("addSubject", new Sujet());
		model.addAttribute("sujetListe", sujetListe);
		
		return "sujetListing";
	}
	/*
	###############################
	#                             #
	#           POST              #
	#                             #
	###############################
	*/
	/*
	|------------------------------|
	|  POST When you add Subject   |    
	|------------------------------|
	*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String updateRowByPage(@Valid @ModelAttribute(value = "addSubject") Sujet subjectToAdd, ModelMap model, HttpSession session, final RedirectAttributes redirectAttributes)
	{
		/* Define writer */
		Membre createur = (Membre) session.getAttribute("membreSession");
		if (createur == null){
			redirectAttributes.addFlashAttribute("INFORMATION_MESSAGE","Vous devez être connecté pour effectuer cette action.");
			return ("redirect:/connexion");
		}else{
			subjectToAdd.setMembre(createur);
		}		
		
		/* Save subject in database */
		try {
			sujetService.saveSujet(subjectToAdd);
		} catch (DataIntegrityViolationException e) {
			model.addAttribute("ERROR_MESSAGE","Un sujet possède déjà ce nom !");
		}
		return ("redirect:/sujets");
	}
	/*
	|---------------------------------|
	|  POST When you delete Subject   |    
	|---------------------------------|
	*/
	 @RequestMapping(value = "suppression/{id}", method = RequestMethod.POST)
		public String supprSujet(ModelMap model, HttpSession session,final RedirectAttributes redirectAttributes,
				@PathVariable("id") String idSujetToDelete, HttpServletRequest request)
		{
			Sujet sujet = sujetService.findById(Long.parseLong(idSujetToDelete));
		 	sujetService.delSujet(sujet);
		 	redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE","Le sujet "+sujet.getTitle()+" a bien été supprimé.");
			return ("redirect:/sujets");
		}
}
