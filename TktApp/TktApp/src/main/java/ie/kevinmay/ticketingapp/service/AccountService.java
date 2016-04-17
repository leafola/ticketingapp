package ie.kevinmay.ticketingapp.service;

import java.util.List;


import ie.kevinmay.ticketingapp.model.Account;

public interface AccountService {
	public List<Account> listAccounts();
	public void createAccount(Account account);
	public void updateAccount(Account account);
	public Account getAccountByUsername (String username);
	//public Account getAccount(int id);
	public Account getLoggedInAccount();
}
