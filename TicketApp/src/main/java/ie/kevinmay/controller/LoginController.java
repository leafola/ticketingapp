package ie.kevinmay.controller;


import ie.kevinmay.dao.UsersDAO;
import ie.kevinmay.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller routes accesses to the application to the appropriate
 * handler methods. 
 */
@Controller
public class LoginController {

	@Autowired
	private UsersDAO usersDAO;
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Users user = new Users();
		model.addObject("user", user);
		model.setViewName("register");
		return model;
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Users user) {
		usersDAO.saveOrUpdate(user);		
		return new ModelAndView("redirect:/admin");
	}
	
	@RequestMapping(value = {"/login**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Login to Ticketing App");
		model.addObject("message", "Login Page for Ticketing App");
		model.setViewName("login");
		return model;

	}
	
	
}
