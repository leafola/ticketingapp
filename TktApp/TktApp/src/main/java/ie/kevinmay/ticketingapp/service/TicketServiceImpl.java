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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Component // declares TicketService as a Spring bean.
@Path("/ticketservice") //a JAX-RS annotation that declares TicketService as a "root" JAX-RS resource.
@Api( value = "/ticketservice", description = "Manage tickets" )
public class TicketServiceImpl implements TicketService {

	@Autowired // requests a reference to the TicketDAO, which Spring will provide. 
	private TicketDAO ticketDAO;

	@GET
	@Path("/tickets")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation( 
		    value = "List all people", 
		    notes = "List all people using paging" 
		   // response = User.class, 
		   // responseContainer = "List"
		)
	public List<Ticket> getAllTickets() {
		List<Ticket> listTicket = ticketDAO.list();

		return listTicket;
	}

}
