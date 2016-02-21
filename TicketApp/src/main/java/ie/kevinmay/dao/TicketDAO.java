package ie.kevinmay.dao;

import java.util.List;
import ie.kevinmay.model.Ticket;

/**
 * Defines DAO operations for the ticket model.
 */

public interface TicketDAO {
	
	public List<Ticket> list();
}
