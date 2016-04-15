package ie.kevinmay.ticketingapp.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ie.kevinmay.ticketingapp.model.Agent;
import junit.framework.Assert;

@ContextConfiguration(locations = "classpath:META-INF/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AgentDAOTest {

	     
	    @Autowired
	    private AgentDAO agentDAO;
	     
	     
	    @Test
	    @Transactional
	    @Rollback(false)// if set to true any changes to database will be rolled back.
	    public void testCreateAgent()
	    {
	        Agent agent = new Agent();
	        agent.setFName("UnitTestFName");
	        agent.setLName("UnitTestLName");
	        agent.setAccountId(0);// 0 is a test account already created in MySQL
	        
	        agentDAO.createAgent(agent);
	        agent = agentDAO.getAgentByAccount(0);
	        Assert.assertNotNull(agent.getFName());
	        Assert.assertEquals("UnitTestLName", agent.getLName());
	    }
}
