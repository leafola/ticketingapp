package ie.kevinmay.ticketingapp.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TicketController {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView listTickets() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest/ticketservice/getall";
		List<LinkedHashMap> tickets = restTemplate.getForObject(url, List.class);
		return new ModelAndView("listTickets", "tickets", tickets);
	}
}
