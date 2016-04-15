package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ie.kevinmay.ticketingapp.model.Account;
import junit.framework.Assert;

@ContextConfiguration(locations = "classpath:META-INF/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountDAOTest {

	     
	    @Autowired
	    private AccountDAO accountDAO;
	     
	    @Test
	    @Transactional
	    @Rollback(false)// if set to true any changes to database will be rolled back.
	    public void testListAccounts()
	    {
	    	List<Account> accounts = accountDAO.listAccounts();
	        
	        Assert.assertNotNull(accounts);
	        Assert.assertEquals("TestAccount", accounts.get(0).getUsername());
	    }
	    
	    
	    @Test
	    @Transactional
	    @Rollback(true)// if set to true any changes to database will be rolled back.
	    public void testCreateAccount()
	    {
	        Account account = new Account();
	        account.setUsername("UnitTestUsername");
	        account.setPassword("UnitTestPassword");
	        account.setRole("ROLE_TEST");
	        
	        accountDAO.createAccount(account.getUsername(), account.getPassword(), account.getRole());
	        account = accountDAO.getAccountByUsername(account.getUsername());
	        Assert.assertNotNull(account.getRole());
	        Assert.assertEquals("UnitTestUsername", account.getUsername());
	    }
}
