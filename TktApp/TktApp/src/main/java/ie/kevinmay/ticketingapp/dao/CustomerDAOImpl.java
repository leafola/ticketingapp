package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ie.kevinmay.ticketingapp.model.Customer;

@Repository("customerDAO")
public class CustomerDAOImpl implements CustomerDAO {
	private static final String SELECT_QUERY = "select c from Customer c";
	
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
	public List<Customer> listCustomers() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		List<Customer> customers = (List<Customer>) query.getResultList();
		return customers;
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		Customer customer = entityManager.find(Customer.class, id);
		return customer;
	}

	@Override
	@Transactional
	public void createCustomer(Customer customer) {
		Customer newCustomer = new Customer();
		newCustomer.setFName(customer.getFName());
		newCustomer.setLName(customer.getLName());
		newCustomer.setAccountId(customer.getAccountId());
		entityManager.persist(newCustomer);
		
	}

}
