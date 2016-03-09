package ie.kevinmay.ticketingapp.dao;

import java.util.List;


import ie.kevinmay.ticketingapp.model.Ticket;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * An implementation of the ContactDAO interface.
 */
@Repository("ticketDAO")
public class TicketDAOImpl implements TicketDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional // Instead of userTransaction. Defines the scope of a single database transaction. 
	// The database transaction happens inside the scope of a persistence context (EntityManager).
	@SuppressWarnings("unchecked")
	// Return a list of all tickets
	public List<Ticket> list() {

		List<Ticket> allTickets = sessionFactory.getCurrentSession().createCriteria(Ticket.class).list();
		return allTickets;
	}// end of list()


}
