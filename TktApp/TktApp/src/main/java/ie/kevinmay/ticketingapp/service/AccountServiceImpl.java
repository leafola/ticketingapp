package ie.kevinmay.ticketingapp.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ie.kevinmay.ticketingapp.dao.AccountDAO;
import ie.kevinmay.ticketingapp.model.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Component // declares PostService as a Spring bean.
@Path("/accountservice") //a JAX-RS annotation that declares TicketService as a "root" JAX-RS resource.
@Api( value = "account")
public class AccountServiceImpl implements AccountService {

	@Autowired // requests a reference to the TicketDAO, which Spring will provide. 
	private AccountDAO accountDAO;

	@GET
	@Path("/accounts")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation( 
		    value = "List all accounts", 
		   response = Account.class, 
		   responseContainer = "List"
		)
	
	public List<Account> listAccounts() {
		List<Account> accounts = accountDAO.listAccounts();

		return accounts;
	}

	@Override
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Path ("/accounts/account")
	@ApiOperation(value = "Create new Account",
    notes = "This is a test note for Customer")
	public void createAccount(Account account) {
		accountDAO.createAccount(account.getUsername(), account.getPassword(), account.getRole());
	}

	@Override
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Path ("/accounts/account")
	@ApiOperation(value = "Update Account",
    notes = "Must be passed an Account object")
	public void updateAccount(Account account) {
		accountDAO.updateAccount(account);
		
	}

	@Override
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Path ("/accounts/account/{username}")
	public Account getAccountByUsername(@PathParam("username") String username) {
		return accountDAO.getAccountByUsername(username);
	}
	
	@Override
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path ("/accounts/account/loggedin")
	public Account getLoggedInAccount() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		Account account = accountDAO.getAccountByUsername(username);
		account.setPassword("Not Available");
        return account;
	}
	
	

}
