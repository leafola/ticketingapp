package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import ie.kevinmay.ticketingapp.model.Agent;
import ie.kevinmay.ticketingapp.model.Post;

public interface AgentDAO {
	public List<Agent> list();
	public Agent getAgent(int id);
	public void createAgent(Agent agent);
} 
