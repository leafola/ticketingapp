package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ie.kevinmay.ticketingapp.model.Customer;

@Repository("customerDAO")
//@Transactional(propagation = Propagation.REQUIRED)
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
	public List<Customer> list() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		List<Customer> customers = (List<Customer>) query.getResultList();
		return customers;
	}

}
