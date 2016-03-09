package ie.kevinmay.ticketingapp.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ie.kevinmay.ticketingapp.dao.CustomerDAO;
import ie.kevinmay.ticketingapp.model.Customer;

@Component // declares CustomerService as a Spring bean.
@Path("/customerservice") // a JAX-RS annotation that declares CustomerService
							// as a "root" JAX-RS resource.
public class CustomerServiceImpl implements CustomerService {

	@Autowired // requests a reference to the TicketDAO, which Spring will
				// provide.
	private CustomerDAO customerDAO;

	@GET
	@Path("/customers")
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> listCustomer = customerDAO.listCustomers();
		return listCustomer;
	}

	@GET
	@Path("/customers/{customerid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("customerid") int customerid) {
		return customerDAO.getCustomer(customerid);
	}
}
