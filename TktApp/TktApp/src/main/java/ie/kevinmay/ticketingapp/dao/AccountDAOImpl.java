package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ie.kevinmay.ticketingapp.model.Account;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * An implementation of the ticketDAO interface.
 */
@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO {

	private static final String SELECT_QUERY = "select p from Account p";
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
	public List<Account> listAccounts() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		List<Account> accounts = (List<Account>) query.getResultList();
		return accounts;
	}


}
