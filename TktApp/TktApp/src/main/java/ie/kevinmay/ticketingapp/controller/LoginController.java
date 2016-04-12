package ie.kevinmay.ticketingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ie.kevinmay.ticketingapp.dao.AccountDAO;
import ie.kevinmay.ticketingapp.dao.CustomerDAO;
import ie.kevinmay.ticketingapp.model.Account;
import ie.kevinmay.ticketingapp.model.Customer;

@Controller
public class LoginController {

	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private CustomerDAO customerDAO;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginForm(@ModelAttribute Account account,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		String message = "";
		if (error != null) {
			message = "Incorrect username or password!";
		} else if (logout != null) {
			message = "Logout successful !";
		}
		return new ModelAndView("login", "message", message);
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView getRegisterPage(@ModelAttribute Account account,
			@RequestParam(value = "message", required = false) String message) {
		// String message = "Well?";
		return new ModelAndView("register", "message", message);
	}

	@RequestMapping(value = "/validateRegistration", method = RequestMethod.POST)
	public ModelAndView postValidatePage(@ModelAttribute Account account) {
		Account tempAcc = accountDAO.getAccountByUsername(account.getUsername());
		String message = "It didn't work";
		if (tempAcc.getUsername() == null) {
			accountDAO.createAccount(account.getUsername(), account.getPassword(), "ROLE_USER");
			tempAcc = accountDAO.getAccountByUsername(account.getUsername());
			Customer customer = new Customer();
			customer.setFName(" ");
			customer.setLName(" ");
			customer.setAccountId(tempAcc.getId());
			customerDAO.createCustomer(customer);
			message = "Account successfully registered! Please Login.";
			account.setUsername(null);
			account.setPassword(null);
			return new ModelAndView("login", "message", message);
		} else {
			message = "The username " + tempAcc.getUsername() + "already exists!";
		}
		return new ModelAndView("redirect:/web/register", "message", message);
	}

	@RequestMapping(value = "/customer/home", method = RequestMethod.GET)
	public ModelAndView getCustomerHome() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return new ModelAndView("customerHome", "username", username);
	}

	@RequestMapping(value = "/agent/home", method = RequestMethod.GET)
	public ModelAndView getAgentHome() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return new ModelAndView("agentHome", "username", username);
	}
	
	@RequestMapping(value = "/super/home", method = RequestMethod.GET)
	public ModelAndView getSuperHome() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return new ModelAndView("superHome", "username", username);
	}

	@RequestMapping(value = "getLoggedInUser", method = RequestMethod.GET)
	@ResponseBody
	public String getLoggedInUser() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

}
