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
import org.springframework.stereotype.Component;

import ie.kevinmay.ticketingapp.dao.CustomerDAO;
import ie.kevinmay.ticketingapp.model.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Component // declares CustomerService as a Spring bean.
@Path("/customerservice") // a JAX-RS annotation that declares CustomerService
							// as a "root" JAX-RS resource.
@Api( value = "customer")// could also use tags (tags = {"customers_info","customer_info"})
public class CustomerServiceImpl implements CustomerService {

	@Autowired // requests a reference to the TicketDAO, which Spring will
				// provide.
	private CustomerDAO customerDAO;

	@GET
	@Path("/customers")
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	@ApiOperation(value = "Finds All Customers",
    notes = "This is a test note for Customer",
    response = Customer.class,
    responseContainer = "List")
	public List<Customer> getAllCustomers() {
		List<Customer> listCustomer = customerDAO.listCustomers();
		return listCustomer;
	}

	@GET
	@Path("/customers/{customerid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get Customer by Id",
    notes = "This is a test note for Customer",
    response = Customer.class)
	public Customer getCustomer(@PathParam("customerid") int customerid) {
		return customerDAO.getCustomer(customerid);
	}

	@POST
	@Path("/customers/customer")
	@Consumes({MediaType.APPLICATION_JSON})
	@Override
	@ApiOperation(value = "Create new Customer",
    notes = "This is a test note for Customer")
	public void createCustomer(Customer customer) {
		customerDAO.createCustomer(customer);
		
	}

	@GET
	@Path("/customers/account/{accountid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get Customer by FK Account Id",
    notes = "This is a test note for Customer",
    response = Customer.class)
	public Customer getCustomerByAccount(@PathParam("accountid")int accountid) {
		return customerDAO.getCustomerByAccount(accountid);
		
	}

	@PUT
	@Path("/customers/customer")
	@Consumes({MediaType.APPLICATION_JSON})
	@Override
	@ApiOperation(value = "Update Customer",
    notes = "This is a test note for Customer")
	public void updateCustomer(Customer customer) {
		customerDAO.updateCustomer(customer);
		
	}
}
