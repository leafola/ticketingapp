package ie.kevinmay.ticketingapp.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ie.kevinmay.ticketingapp.model.Account;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)  
	 public ModelAndView getLoginForm(@ModelAttribute Account account,  
	   @RequestParam(value = "error", required = false) String error,  
	   @RequestParam(value = "logout", required = false) String logout) {  
	  
	  String message = "";  
	  if (error != null) {  
	   message = "Incorrect username or password !";  
	  } else if (logout != null) {  
	   message = "Logout successful !";  
	  }  
	  return new ModelAndView("login", "message", message);  
	 }
	
	@RequestMapping(value = "/customer/home", method = RequestMethod.GET)
    public ModelAndView getCustomerHome() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
        return new ModelAndView ("customerHome", "username", username);
    }
	
	@RequestMapping(value = "/agent/home", method = RequestMethod.GET)
    public ModelAndView getAgentHome() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
        return new ModelAndView ("agentHome", "username", username);
    }
	
	@RequestMapping(value = "getLoggedInUser", method = RequestMethod.GET)
	@ResponseBody
	public String getLoggedInUser() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
        return username;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {
        return "register";
    }
	
	
}
