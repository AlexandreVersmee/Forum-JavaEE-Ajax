package ca.uqar.forum.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.uqar.forum.entities.FilDiscussion;
import ca.uqar.forum.entities.Membre;
import ca.uqar.forum.entities.Message;
import ca.uqar.forum.entities.ResponseAjax;
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
	|    		DEFAULT  		   |    
	|------------------------------|
	*/
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String displayEmptyView(@PathVariable("id") String idDiscussion,ModelMap model, HttpSession session, HttpServletRequest request)
	{
		FilDiscussion discussion = 		discussionService.findById(Long.parseLong(idDiscussion));
		
		model.addAttribute("messageListe", null);
		model.addAttribute("addMessage", new Message());
		model.addAttribute("answerMessage", new Message());
		model.addAttribute("editMessage", new Message());
		model.addAttribute("discussion", discussion);
		
		return "message";
	}
	
	/*
	|------------------------------|
	|     Get all Discussion       |    
	|------------------------------|
	*/
	@RequestMapping(value = "/{id}/ajax", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Message> displayMessagesOfDiscussion(@PathVariable("id") String idDiscussion, ModelMap model)
	{
		ArrayList<Message> messageListe =	(ArrayList<Message>) messageService.readByFilDiscussion(Long.parseLong(idDiscussion));
		FilDiscussion discussion = 		discussionService.findById(Long.parseLong(idDiscussion));

		model.addAttribute("discussion", discussion);
		model.addAttribute("editMessage", new Message());
		
		logger.debug("Display Layout");
		
		return messageListe;
	}
	/*
	|------------------------------|
	| GET When you edit message    |    
	|------------------------------|
	*/
	@RequestMapping(value = "/{idDiscussion}/message/{idMessage}", method = RequestMethod.GET)
	public @ResponseBody Message displayEditMessage(@PathVariable(value = "idDiscussion") String idDiscussion, @PathVariable(value = "idMessage") String idMessage, ModelMap model)
	{
		FilDiscussion discussion = 		discussionService.findById(Long.parseLong(idDiscussion));
		Message messageToEdit =			messageService.findById(Long.parseLong(idMessage));
		
		logger.debug("DANS MESSAGE TO EDIT - GET ! -> message to edit = [{}]", messageToEdit.getTexte());
				
		model.addAttribute("addMessage", new Message());
		model.addAttribute("answerMessage", new Message());
		model.addAttribute("editMessage", new Message());
		model.addAttribute("discussion", discussion);
		
		return messageToEdit;
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
	| POST When you add message    |    
	|------------------------------|
	*/
	@RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
	public @ResponseBody ResponseAjax addNewMessage(@PathVariable("id") String idDiscussion,
			@RequestBody Message form, ModelMap model, HttpServletRequest request,
			HttpSession session, final RedirectAttributes redirectAttributes)
	{
		ResponseAjax ret = new ResponseAjax();
		logger.debug("DANS MESSAGE TO ADD ! -> message to add = [{}]", form.getTexte());
		
		Message messageToAdd = new Message();
		messageToAdd.setTexte(form.getTexte());
		
		/* Define writer */
		Membre createur = (Membre) session.getAttribute("membreSession");
		if (createur == null)
		{
			ret.setMessage("You have to login before send message !");
			ret.setStatut("KO");
			return ret;
		}
		messageService.saveMessage(messageToAdd, idDiscussion, createur);

		ret.setMessage("It works");
		ret.setStatut("OK");
		return ret;
	}
	/*
	|------------------------------|
	| POST When you edit message   |    
	|------------------------------|
	*/
	@RequestMapping(value = "{discussionId}/edit/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseAjax updateMessage(@PathVariable("id") String idMessage,
			@PathVariable("discussionId") String idDiscussion,HttpServletRequest request,
			@RequestBody Message answerToAdd, ModelMap model, HttpSession session, 
			final RedirectAttributes redirectAttributes)
	{
		ResponseAjax ret = new ResponseAjax();
		
		logger.debug("DANS MESSAGE TO EDIT - POST ! -> message to edit = [{}]", answerToAdd.getTexte());
		
		messageService.updateAnswer(answerToAdd, Long.parseUnsignedLong(idMessage));

		ret.setMessage("It works");
		ret.setStatut("OK");
		return ret;
	}
	/*
	|------------------------------|
	| POST When you delete message |    
	|------------------------------|
	*/
	@RequestMapping(value = "{discussionId}/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseAjax deleteMessage(@PathVariable("id") String idParent, 
			@PathVariable("discussionId") String idDiscussion, ModelMap model, 
			HttpSession session, final RedirectAttributes redirectAttributes)
	{			
		ResponseAjax ret = new ResponseAjax();
		
		logger.debug("DANS MESSAGE TO DELETE - POST ! -> message to delete = [id={}]", idParent);
		
		/* Define writer */
		Membre createur = (Membre) session.getAttribute("membreSession");
		if (createur == null)
		{
			ret.setMessage("You have to login before delete message !");
			ret.setStatut("KO");
			return ret;
		}
		
		messageService.deleteMessage(idParent);
		ret.setMessage("It works");
		ret.setStatut("OK");
		return ret;
	}

}