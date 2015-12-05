package ca.uqar.forum.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.uqar.forum.entities.Sujet;
import ca.uqar.forum.services.ISujetService;

@Controller
@RequestMapping(value="/administration-sujets")
public class AdministrationSujetController
{	
	@Resource
	private ISujetService	sujetService;
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
	public String displaySubjectAdministration(ModelMap model, HttpSession session, HttpServletRequest request)
	{
		List<Sujet> liste = sujetService.findAll();
		
		model.addAttribute("sujetList", liste);
		return "administrationSujet";
	}
	/*
	###############################
	#                             #
	#           POST              #
	#                             #
	###############################
	*/
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
			return ("redirect:/administration-sujets");
		}

}
