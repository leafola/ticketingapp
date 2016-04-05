package ie.kevinmay.ticketingapp.service;

import java.util.List;

import ie.kevinmay.ticketingapp.model.Agent;
import ie.kevinmay.ticketingapp.model.Customer;

public interface AgentService {
	public List<Agent> getAllAgents();
	public Agent getAgent(int id);
	public void createAgent(Agent agent);
	public void updateAgent(Agent agent);
	public Agent getAgentByAccount (int id);
}
