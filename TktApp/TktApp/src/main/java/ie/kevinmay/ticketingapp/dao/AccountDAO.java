package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import ie.kevinmay.ticketingapp.model.Account;

/**
 * Defines DAO operations for the ticket model.
 */

public interface AccountDAO {
	
	public List<Account> listAccounts();
	//public Account getAccount(int id);

}
