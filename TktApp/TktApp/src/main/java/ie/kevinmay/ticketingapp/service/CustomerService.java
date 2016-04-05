package ie.kevinmay.ticketingapp.service;

import java.util.List;

import ie.kevinmay.ticketingapp.model.Customer;

public interface CustomerService {
	public List<Customer> getAllCustomers();
	public Customer getCustomer(int id);
	public void createCustomer(Customer customer);
	public Customer getCustomerByAccount (int id);
	public void updateCustomer(Customer customer);
}
