package ie.kevinmay.controller;

//import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;

import ie.kevinmay.dao.TicketDAO;
import ie.kevinmay.model.Ticket;

@Controller
public class TicketController {
	
	@Autowired
	private TicketDAO ticketDAO;
	
	@RequestMapping(value = "/list_tickets", method = RequestMethod.GET)
	public String listTickets(ModelMap model) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername(); //get logged in username
			
		List<Ticket> listTicket = ticketDAO.list(name);
		model.addAttribute("listTicket", listTicket);// adds the list taken from the database to the model
		return "list_tickets"; // returns the view list_tickets
	}
	
	@RequestMapping(value = "/newTicket", method = RequestMethod.GET)
	public ModelAndView newTicket(ModelAndView model) {
		Ticket ticket = new Ticket();
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    ticket.setCustomer(user.getUsername());
		model.addObject("ticket", ticket);
		model.setViewName("ticket_form");
		return model;
	}
	
	@RequestMapping(value = "/saveTicket", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Ticket ticket) {
		ticketDAO.saveTicket(ticket);		
		return new ModelAndView("redirect:/list_tickets");
	}
	
	//Alternative Method which passes both model and view instead of just view.
	/*@RequestMapping(value="/list_tickets")
	public ModelAndView listTicket(ModelAndView model) throws IOException{
		List<Ticket> listTicket = ticketDAO.list();
		model.addObject("listTicket", listTicket);
		model.setViewName("list_tickets");
		
		return model;
	}*/

}
