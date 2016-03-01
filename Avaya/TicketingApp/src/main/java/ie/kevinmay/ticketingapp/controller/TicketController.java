package ie.kevinmay.ticketingapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import ie.kevinmay.ticketingapp.model.Ticket;

@Controller
public class TicketController {

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/list_tickets", method = RequestMethod.GET)
	public ModelAndView listTickets() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest/ticketservice/getall";
		List<Ticket> tickets = restTemplate.getForObject(url, List.class);
		return new ModelAndView("listTickets", "tickets", tickets);
	}
}
