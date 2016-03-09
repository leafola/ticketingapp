package ie.kevinmay.ticketingapp.dao;

import java.util.List;
import ie.kevinmay.ticketingapp.model.Ticket;

/**
 * Defines DAO operations for the ticket model.
 */

public interface TicketDAO {
	
	public List<Ticket> list();

}
