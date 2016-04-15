package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ie.kevinmay.ticketingapp.model.Account;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * An implementation of the ticketDAO interface.
 */
@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO {

	private static final String SELECT_QUERY = "select a from Account a";
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
	public List<Account> listAccounts() {
		TypedQuery<Account> query = entityManager.createQuery(SELECT_QUERY, Account.class);
		List<Account> accounts = query.getResultList();
		return accounts;
	}

	@Override
	@Transactional
	public void createAccount(String username, String pword, String role){
		Account account = new Account(username, pword, role);
			entityManager.persist(account);
		
	}

	@Override
	@Transactional
	public void updateAccount(Account account) {
		Account tempAcc = entityManager.find(Account.class, account.getId());
		tempAcc.setPassword(account.getPassword());
		tempAcc.setUsername(account.getUsername());
		tempAcc.setRole(account.getRole());
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Account getAccountByUsername(String username) {
		Query query = entityManager.createQuery(SELECT_QUERY);
		List<Account> accounts = (List<Account>) query.getResultList();
		Account account = new Account();
		for (Account acc : accounts){
			if (acc.getUsername().equals(username)) {
				account = acc;
			}
		}
		return account;
	}


}
