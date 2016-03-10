package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import ie.kevinmay.ticketingapp.model.Agent;

public interface AgentDAO {
	public List<Agent> list();
	public Agent getAgent(int id);
} 
