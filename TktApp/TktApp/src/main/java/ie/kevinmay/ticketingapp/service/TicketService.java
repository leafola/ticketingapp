package ie.kevinmay.ticketingapp.service;

import java.util.List;

import javax.ws.rs.core.Response;

import ie.kevinmay.ticketingapp.model.Ticket;

public interface TicketService {
	public List<Ticket> getAllTickets();
	
	public void deleteTicket(int id);

	public List<Ticket> getByCustomer(int customerid);
	public List<Ticket> getByAgent(int agentid);
	public List<Ticket> getByCustomerUsername(String username);
	public List<Ticket> getByAgentUsername(String username);
	public Response createTicket(Ticket ticket);
	public void updateTicket(Ticket ticket);
	
}
