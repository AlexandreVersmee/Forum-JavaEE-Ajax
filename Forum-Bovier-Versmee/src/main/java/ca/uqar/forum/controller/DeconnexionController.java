package ca.uqar.forum.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/deconnexion") 
public class DeconnexionController
{
	/* Debug */
	private final static Logger logger = LoggerFactory.getLogger(DeconnexionController.class);
		
	/*
	###############################
	#                             #
	#            GET              #
	#                             #
	###############################
	*/
	/*
	###############################
	#                             #
	#            POST             #
	#                             #
	###############################
	*/
    @RequestMapping(method = RequestMethod.POST)
	public String unlogUser(HttpSession session, final RedirectAttributes redirectAttributes)
	{
    	logger.debug("Delete userSession variable");
    	session.removeAttribute("membreSession");
    	redirectAttributes.addFlashAttribute("INFORMATION_MESSAGE","Vous etes desormais deconnecter d'HalloweenBlog !");
    	return ("redirect:/");
	}
}
