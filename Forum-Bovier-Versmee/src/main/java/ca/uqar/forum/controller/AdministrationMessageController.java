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

import ca.uqar.forum.entities.Membre;
import ca.uqar.forum.entities.Message;
import ca.uqar.forum.services.IMessageService;

@Controller
@RequestMapping(value="/administration-messages")
public class AdministrationMessageController
{	
	@Resource
	private IMessageService	messageService;
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
	public String displayMessageAdministration(ModelMap model, HttpSession session, HttpServletRequest request)
	{
		List<Message> liste = messageService.findByAll();
		
		model.addAttribute("messageList", liste);
		return "administrationMessage";
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
	| POST When you delete message |    
	|------------------------------|
	*/
	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public String deleteMessage(@PathVariable("id") String idMessage, ModelMap model, HttpSession session, final RedirectAttributes redirectAttributes)
	{				
		/* Define writer */
		Membre createur = (Membre) session.getAttribute("membreSession");
		if (createur == null){
			redirectAttributes.addFlashAttribute("INFORMATION_MESSAGE","Vous devez être connecté pour effectuer cette action.");
			return ("redirect:/connexion");
		}		
		messageService.deleteMessage(idMessage);
		return "redirect:/administration-messages";
	}
}
