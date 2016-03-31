package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import ie.kevinmay.ticketingapp.model.Customer;

public interface CustomerDAO {
	public List<Customer> listCustomers();
	public Customer getCustomer(int id);
	public void createCustomer(Customer customer);
	public Customer getCustomerByAccount(int accountid);
} 
