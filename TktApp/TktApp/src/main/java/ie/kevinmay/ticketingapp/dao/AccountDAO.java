package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import ie.kevinmay.ticketingapp.model.Account;

/**
 * Defines DAO operations for the ticket model.
 */

public interface AccountDAO {
	
	public List<Account> listAccounts();
	public void createAccount(String username, String pword, String role);
	public void updateAccount(Account account);
	public Account getAccountByUsername(String username);

}
