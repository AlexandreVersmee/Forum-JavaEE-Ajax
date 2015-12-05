package ca.uqar.forum.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




import ca.uqar.forum.entities.FilDiscussion;
import ca.uqar.forum.entities.Membre;
import ca.uqar.forum.entities.Message;
import ca.uqar.forum.services.IDiscussionService;
import ca.uqar.forum.services.IMessageService;

@Controller
@RequestMapping(value="/discussion")
public class MessageController
{
	/* Debug */
	private final static Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Resource
	IMessageService			messageService;
	
	@Resource
	IDiscussionService		discussionService;
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
	| POST When you edit message   |    
	|------------------------------|
	*/
	@RequestMapping(value = "/{idDiscussion}/message/{idMessage}", method = RequestMethod.GET)
	public String displayEditMessage(@PathVariable(value = "idDiscussion") String idDiscussion,
			@PathVariable(value = "idMessage") String idMessage, ModelMap model, HttpServletRequest request)
	{
		List<Message> messageListe =	messageService.readByFilDiscussion(Long.parseLong(idDiscussion));
		FilDiscussion discussion = 		discussionService.findById(Long.parseLong(idDiscussion));
		Message messageToEdit =			messageService.findById(Long.parseLong(idMessage));
		
		model.addAttribute("messageListe", messageListe);
		model.addAttribute("addMessage", new Message());
		model.addAttribute("answerMessage", new Message());
		model.addAttribute("editMessage", messageToEdit);
		model.addAttribute("discussion", discussion);
		return "message";
	}
	/*
	|------------------------------|
	|     Get all Discussion       |    
	|------------------------------|
	*/
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String displayMessagesOfDiscussion(@PathVariable("id") String idDiscussion,ModelMap model, HttpSession session, HttpServletRequest request)
	{
		List<Message> messageListe =	messageService.readByFilDiscussion(Long.parseLong(idDiscussion));
		FilDiscussion discussion = 		discussionService.findById(Long.parseLong(idDiscussion));
		
		model.addAttribute("messageListe", messageListe);
		model.addAttribute("addMessage", new Message());
		model.addAttribute("answerMessage", new Message());
		model.addAttribute("editMessage", new Message());
		model.addAttribute("discussion", discussion);
		return "message";
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
	| POST When you answer message |    
	|------------------------------|
	*/
	@RequestMapping(value = "{discussionId}/answer/{id}", method = RequestMethod.POST)
	public String answerMessage(@PathVariable("id") String idParent, @PathVariable("discussionId") String idDiscussion,
			@Valid @ModelAttribute(value = "answerMessage") Message answerToAdd, ModelMap model, HttpSession session, final RedirectAttributes redirectAttributes)
	{
		Message answer = new Message();
		
		answer.setTexte(answerToAdd.getTexte());
		
		logger.debug("Message = "+answer.getId());
		/* Define writer */
		Membre createur = (Membre) session.getAttribute("membreSession");
		if (createur == null){
			redirectAttributes.addFlashAttribute("INFORMATION_MESSAGE","Vous devez être connecté pour effectuer cette action.");
			return ("redirect:/connexion");
		}		
		messageService.saveAnswer(answer, idDiscussion, idParent, createur);
		return "redirect:/discussion/"+idDiscussion;
	}
	/*
	|------------------------------|
	| POST When you add message    |    
	|------------------------------|
	*/
	@RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
	public String addNewMessage(@PathVariable("id") String idDiscussion, @Valid @ModelAttribute(value = "addMessage") Message form, ModelMap model, HttpSession session, final RedirectAttributes redirectAttributes)
	{
		Message messageToAdd = new Message();
		messageToAdd.setTexte(form.getTexte());
		
		/* Define writer */
		Membre createur = (Membre) session.getAttribute("membreSession");
		if (createur == null){
			redirectAttributes.addFlashAttribute("INFORMATION_MESSAGE","Vous devez être connecté pour effectuer cette action.");
			return ("redirect:/connexion");
		}	
		messageService.saveMessage(messageToAdd, idDiscussion, createur);

		return "redirect:/discussion/"+idDiscussion;
	}
	/*
	|------------------------------|
	| POST When you edit message   |    
	|------------------------------|
	*/
	@RequestMapping(value = "{discussionId}/edit/{id}", method = RequestMethod.POST)
	public String updateMessage(@PathVariable("id") String idMessage,
			@PathVariable("discussionId") String idDiscussion,
			@Valid @ModelAttribute(value = "answerMessage") Message answerToAdd, ModelMap model, HttpSession session, final RedirectAttributes redirectAttributes)
	{
		/* Define writer */
		Membre createur = (Membre) session.getAttribute("membreSession");
		if (createur == null){
			redirectAttributes.addFlashAttribute("INFORMATION_MESSAGE","Vous devez être connecté pour effectuer cette action.");
			return ("redirect:/connexion");
		}		
		int ret = messageService.updateAnswer(answerToAdd, Long.parseUnsignedLong(idMessage));
		if (ret == 0)
			redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE","Le message a bien été édité.");
		else
			redirectAttributes.addFlashAttribute("INFORMATION_MESSAGE","Aucune modification effectuée.");
		return "redirect:/discussion/"+idDiscussion;
	}
	/*
	|------------------------------|
	| POST When you delete message |    
	|------------------------------|
	*/
	@RequestMapping(value = "{discussionId}/delete/{id}", method = RequestMethod.POST)
	public String deleteMessage(@PathVariable("id") String idParent, @PathVariable("discussionId") String idDiscussion, ModelMap model, HttpSession session, final RedirectAttributes redirectAttributes)
	{			
		/* Define writer */
		Membre createur = (Membre) session.getAttribute("membreSession");
		if (createur == null){
			redirectAttributes.addFlashAttribute("INFORMATION_MESSAGE","Vous devez être connecté pour effectuer cette action.");
			return ("redirect:/connexion");
		}		
		messageService.deleteMessage(idParent);
		return "redirect:/discussion/"+idDiscussion;
	}
}