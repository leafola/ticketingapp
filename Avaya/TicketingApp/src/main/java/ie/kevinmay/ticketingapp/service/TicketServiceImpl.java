package ie.kevinmay.ticketingapp.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ie.kevinmay.ticketingapp.dao.TicketDAO;
import ie.kevinmay.ticketingapp.model.Ticket;

@Component
@Path("/ticketservice")
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDAO ticketDAO;

	@GET
	@Path("/getall")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Ticket> getAllTickets() {
		List<Ticket> listTicket = ticketDAO.list();

		return listTicket;
	}

}
