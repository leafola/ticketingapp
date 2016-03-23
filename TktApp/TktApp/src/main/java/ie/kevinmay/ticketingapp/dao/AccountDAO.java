package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import ie.kevinmay.ticketingapp.model.Account;

/**
 * Defines DAO operations for the ticket model.
 */

public interface AccountDAO {
	
	public List<Account> listAccounts();
	public void createAccount(String username, String pword, String role);
	public void updateAccount(Account account);
	public void getAccount(String username);

}
