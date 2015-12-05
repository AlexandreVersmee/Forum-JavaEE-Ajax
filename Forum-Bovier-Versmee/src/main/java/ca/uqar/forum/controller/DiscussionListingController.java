package ca.uqar.forum.controller;

import java.util.List;

import javax.annotation.Resource;
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

import ca.uqar.forum.entities.FilDiscussion;
import ca.uqar.forum.entities.Membre;
import ca.uqar.forum.forms.addFildDicussionAndMessage;
import ca.uqar.forum.services.IDiscussionService;
import ca.uqar.forum.services.ISujetService;

@Controller
@RequestMapping(value="/sujet")
public class DiscussionListingController
{	
	@Resource
	IDiscussionService			discussuionService;
	
	@Resource
	ISujetService				sujetService;
	
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
	/*
	|------------------------------|
	|     Get all Discussion       |    
	|------------------------------|
	*/
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String displayDiscussion(@PathVariable("id") String idSubject, ModelMap model, HttpSession session, final RedirectAttributes redirectAttributes)
	{
		List<FilDiscussion> listeFil = discussuionService.readBySujet(Long.parseLong(idSubject));
		
		model.addAttribute("addFilDiscussionAndMessage", new addFildDicussionAndMessage());
		model.addAttribute("listeFilDiscussion", listeFil);
		model.addAttribute("idSujetParent", idSubject);
		return "discussionListing";
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
	| POST When you add Discussion |    
	|------------------------------|
	*/
	@RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
	public String addNewDiscussion(@Valid @ModelAttribute(value = "addFilDiscussionAndMessage") addFildDicussionAndMessage form,
			@PathVariable("id") String idSubject, ModelMap model, HttpSession session, final RedirectAttributes redirectAttributes)
	{
		if (form.getTitle().isEmpty() || form.getContenue().isEmpty())
    	{
    		redirectAttributes.addFlashAttribute("ERROR_MESSAGE","Ces champs ne peuvent pas être vides.");
    		return "redirect:/sujet/"+idSubject;
    	}
		
		/* Set writer */
		Membre createur = (Membre) session.getAttribute("membreSession");
		if (createur == null){
			redirectAttributes.addFlashAttribute("INFORMATION_MESSAGE","Vous devez être connecté pour effectuer cette action.");
			return ("redirect:/connexion");
		}		
		
		
		/* Save discussion in database */
		try {
			discussuionService.saveDiscussionAndMessage(form, createur);
		} catch (DataIntegrityViolationException e) {
			model.addAttribute("ERROR_MESSAGE","Un sujet possède déjà ce nom !");
		}
		
		return "redirect:/sujet/"+idSubject;
	}
}
