package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ie.kevinmay.ticketingapp.model.Ticket;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * An implementation of the ticketDAO interface.
 */
@Repository("ticketDAO")
public class TicketDAOImpl implements TicketDAO {

	private static final String SELECT_QUERY = "select t from Ticket t";
	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Ticket> list() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		List<Ticket> tickets = (List<Ticket>) query.getResultList();
		return tickets;
	}


}
