package ie.kevinmay.ticketingapp.service;

import java.util.List;

import ie.kevinmay.ticketingapp.model.Agent;

public interface AgentService {
	public List<Agent> getAllAgents();
	public Agent getAgent(int id);
}
