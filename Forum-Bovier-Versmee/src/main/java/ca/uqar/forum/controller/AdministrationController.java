package ca.uqar.forum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/administration")
public class AdministrationController
{
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
	public String homeAdministration(ModelMap model, HttpSession session, HttpServletRequest request)
	{
		return "administration";
	}
	/*
	###############################
	#                             #
	#           POST              #
	#                             #
	###############################
	*/
}
