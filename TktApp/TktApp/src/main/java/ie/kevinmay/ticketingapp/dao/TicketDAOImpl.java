package ie.kevinmay.ticketingapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ie.kevinmay.ticketingapp.model.Account;
import ie.kevinmay.ticketingapp.model.Agent;
import ie.kevinmay.ticketingapp.model.Customer;
import ie.kevinmay.ticketingapp.model.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * An implementation of the ticketDAO interface.
 */
@Repository("ticketDAO")
public class TicketDAOImpl implements TicketDAO {
	@Autowired // requests a reference to the TicketDAO, which Spring will
				// provide.
	private AccountDAO accountDAO;

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private AgentDAO agentDAO;

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
	@SuppressWarnings("unchecked")
	public List<Ticket> list() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		List<Ticket> tickets = (List<Ticket>) query.getResultList();
		return tickets;
	}

	@Override
	public Ticket getTicket(int id) {
		Ticket ticket = entityManager.find(Ticket.class, id);
		return ticket;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Ticket> listByCustomer(int id) {
		Query query = entityManager.createQuery(SELECT_QUERY + " where t.customerId = :id").setParameter("id", id);
		List<Ticket> tickets = (List<Ticket>) query.getResultList();
		return tickets;
	}
	
	@Override
	public List<Ticket> listByAgent(int id) {
		TypedQuery<Ticket> query = entityManager.createQuery(SELECT_QUERY + " where t.agentId = :id", Ticket.class).setParameter("id", id);
		List<Ticket> tickets = query.getResultList();
		return tickets;
	}

	@Override
	public List<Ticket> listByCustomerUsername(String username) {
		Account account = accountDAO.getAccountByUsername(username);
		Customer customer = customerDAO.getCustomerByAccount(account.getId());
		return listByCustomer(customer.getId());
	}
	
	@Override
	public List<Ticket> listByAgentUsername(String username) {
		Account account = accountDAO.getAccountByUsername(username);
		Agent agent = agentDAO.getAgentByAccount(account.getId());
		return listByAgent(agent.getId());
	}

	@Override
	@Transactional
	public void deleteTicket(int id) {
		Ticket ticket = entityManager.find(Ticket.class, id);
		entityManager.remove(ticket);
	}

	@Override
	@Transactional
	public void createTicket(Ticket ticket) {
		Ticket newTicket = new Ticket();
		newTicket.setCustomerId(ticket.getCustomerId());
		newTicket.setTitle(ticket.getTitle());
		newTicket.setAgentId(assignAgent());
		entityManager.persist(newTicket);
	}

	public int assignAgent() {
		List<Agent> agents = agentDAO.list();
		List<Integer> ids = new ArrayList<Integer>();
		
		for (Agent agent : agents){
			ids.add(agent.getId());
		}
		
		for (Integer num : ids){
			System.out.println(num.intValue());
		}
		Random r = new Random();
		int randomId = ids.get(r.nextInt(ids.size()));
		
		System.out.println("Random num: " + randomId);
		return randomId;

	}

	@Override
	@Transactional
	public void updateTicket(Ticket ticket) {
		Ticket tkt = getTicket(ticket.getId());
		tkt.setClosed(ticket.getClosed());
		
	}

}
