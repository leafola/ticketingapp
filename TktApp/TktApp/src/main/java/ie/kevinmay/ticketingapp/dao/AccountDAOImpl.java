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

	@Override
	@Transactional
	public void createAccount(String username, String pword, String role) {
		Account account = new Account(username, pword, role);
		entityManager.persist(account);
		
	}

	@Override
	@Transactional
	public void updateAccount(Account account) {
		Account tempAcc = entityManager.find(Account.class, account.getId());
		tempAcc.setPword(account.getPword());
		tempAcc.setUsername(account.getUsername());
		tempAcc.setRole(account.getRole());
	}

	@Override
	public void getAccount(String username) {
		// TODO Auto-generated method stub
		
	}


}
