package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ie.kevinmay.ticketingapp.model.Customer;
import junit.framework.Assert;

@ContextConfiguration(locations = "classpath:META-INF/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerDAOTest {
	
	@Autowired
    private CustomerDAO customerDAO;
	
	@Test
    @Transactional
    @Rollback(false)// if set to true any changes to database will be rolled back.
    public void testListCustomers() {
		List<Customer> customers = customerDAO.listCustomers();
        
        Assert.assertNotNull(customers);
	}
}
