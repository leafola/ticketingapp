package ie.kevinmay.ticketingapp.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
@Api( value = "ticket")
public class TicketServiceImpl implements TicketService {

	@Autowired // requests a reference to the TicketDAO, which Spring will provide. 
	private TicketDAO ticketDAO;

	@GET
	@Path("/tickets")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation( 
		    value = "List all tickets", 
		   response = Ticket.class, 
		   responseContainer = "List"
		)
	public List<Ticket> getAllTickets() {
		List<Ticket> listTicket = ticketDAO.list();

		return listTicket;
	}
	
	@GET
	@Path("/tickets/{ticketid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( 
		    value = "Find Ticket by Id",
		    notes = "This is where I can add implementation notes"
		)
	public Ticket getTicket(@PathParam("ticketid") int ticketid) {
		return ticketDAO.getTicket(ticketid);
	}
	
	@GET
	@Override
	@Path("/tickets/customer/{customerid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( 
		    value = "Find Ticket by Customer Id",
		    notes = "This is where I can add implementation notes",
		    response = Ticket.class, 
			responseContainer = "List"
		)
	public List<Ticket> getByCustomer(@PathParam("customerid") int customerid) {
		return ticketDAO.listByCustomer(customerid);
	}
	
	@GET
	@Override
	@Path("/tickets/agent/{agentid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( 
		    value = "Find Ticket by Agent Id",
		    notes = "This is where I can add implementation notes",
		    response = Ticket.class, 
			responseContainer = "List"
		)
	public List<Ticket> getByAgent(@PathParam("agentid") int agentid) {
		return ticketDAO.listByAgent(agentid);
	}

	@Override
	@DELETE
	@Path("/tickets/{ticketid}")
	@ApiOperation( 
		    value = "Delete Ticket by Id",
		    notes = "This is where I can add implementation notes",
		    response = Ticket.class
			)
	public void deleteTicket(@PathParam("ticketid") int id) {
		ticketDAO.deleteTicket(id);
		
	}

	@GET
	@Override
	@Path("/tickets/customer/username/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( 
		    value = "Find Ticket by Customer Username",
		    notes = "This is where I can add implementation notes",
		    response = Ticket.class, 
			responseContainer = "List"
		)
	public List<Ticket> getByCustomerUsername(@PathParam("username") String username) {
		return ticketDAO.listByCustomerUsername(username);
	}
	
	@GET
	@Override
	@Path("/tickets/agent/username/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( 
		    value = "Find Ticket by Agent Username",
		    notes = "This is where I can add implementation notes",
		    response = Ticket.class, 
			responseContainer = "List"
		)
	public List<Ticket> getByAgentUsername(@PathParam("username") String username) {
		return ticketDAO.listByAgentUsername(username);
	}

	@POST
	@Path("/tickets/ticket")
	@Consumes({MediaType.APPLICATION_JSON})
	@Override
	@ApiOperation(value = "Create new Ticket",
    notes = "This is a test note for Ticket")
	public void createTicket(Ticket ticket) {
		ticketDAO.createTicket(ticket);
		
	}

}
