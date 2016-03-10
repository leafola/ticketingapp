package ie.kevinmay.ticketingapp.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ie.kevinmay.ticketingapp.dao.AccountDAO;
import ie.kevinmay.ticketingapp.model.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Component // declares PostService as a Spring bean.
@Path("/accountservice") //a JAX-RS annotation that declares TicketService as a "root" JAX-RS resource.
@Api( value = "/accountservice")
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

}
