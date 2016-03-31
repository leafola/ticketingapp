package ie.kevinmay.ticketingapp.service;

import java.util.List;

import ie.kevinmay.ticketingapp.model.Ticket;

public interface TicketService {
	public List<Ticket> getAllTickets();
	
	public void deleteTicket(int id);

	public List<Ticket> getByCustomer(int customerid);
	public List<Ticket> getByCustomerUsername(String username);
	public void createTicket(Ticket ticket);
	
}
