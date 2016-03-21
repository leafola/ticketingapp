package ie.kevinmay.ticketingapp.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ie.kevinmay.ticketingapp.dao.AgentDAO;
import ie.kevinmay.ticketingapp.model.Agent;
import ie.kevinmay.ticketingapp.model.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Component // declares AgentService as a Spring bean.
@Path("/agentservice") // a JAX-RS annotation that declares AgentService
							// as a "root" JAX-RS resource.
@Api( value = "agent")
public class AgentServiceImpl implements AgentService {

	@Autowired // requests a reference to the AgentDAO, which Spring will
				// provide.
	private AgentDAO agentDAO;

	@GET
	@Path("/agents")
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	@ApiOperation( 
		    value = "List all Agents",
		    notes = "This is where I can add implementation notes",
		    response = Agent.class, 
		   responseContainer = "List"
		)
	public List<Agent> getAllAgents() {
		List<Agent> listAgent = agentDAO.list();
		return listAgent;
	}

	@GET
	@Path("/agents/{agentid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation( 
		    value = "Find Agent by Id",
		    notes = "This is where I can add implementation notes"
		)
	public Agent getAgent(@PathParam("agentid") int agentid) {
		return agentDAO.getAgent(agentid);
	}

	@POST
	@Path("/agents/agent")
	@Consumes({MediaType.APPLICATION_JSON})
	@Override
	@ApiOperation(value = "Create new Agent",
    notes = "This is a test note for Customer")
	public void createAgent(Agent agent) {
		agentDAO.createAgent(agent);
		
	}
}
