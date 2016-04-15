package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ie.kevinmay.ticketingapp.model.Agent;

public interface AgentDAO {
	public List<Agent> list();
	public Agent getAgent(int id);
	public void createAgent(Agent agent);
	public Agent getAgentByAccount(int id);
	public void updateAgent(Agent agent);
} 
