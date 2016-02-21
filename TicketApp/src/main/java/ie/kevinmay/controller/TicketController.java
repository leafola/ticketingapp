package ie.kevinmay.controller;

//import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;

import ie.kevinmay.dao.TicketDAO;
import ie.kevinmay.model.Ticket;

@Controller
public class TicketController {
	
	@Autowired
	private TicketDAO ticketDAO;
	
	@RequestMapping(value = "/list_tickets", method = RequestMethod.GET)
	public String listTickets(ModelMap model) {
		List<Ticket> listTicket = ticketDAO.list();
		model.addAttribute("listTicket", listTicket);// adds the list taken from the database to the model
		return "list_tickets"; // returns the view list_tickets
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
