package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ie.kevinmay.ticketingapp.model.Agent;

@Repository("agentDAO")
public class AgentDAOImpl implements AgentDAO {
	private static final String SELECT_QUERY = "select a from Agent a";
	
	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Agent> list() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		List<Agent> agents = (List<Agent>) query.getResultList();
		return agents;
	}

	@Override
	@Transactional
	public Agent getAgent(int id) {
		Agent agent = entityManager.find(Agent.class, id);
		return agent;
	}

	@Override
	@Transactional
	public void createAgent(Agent agent) {
		Agent newAgent = new Agent();
		newAgent.setFName(agent.getFName());
		newAgent.setLName(agent.getLName());
		newAgent.setAccountId(agent.getAccountId());
		entityManager.persist(newAgent);
		
	}

	@Override
	public Agent getAgentByAccount(int id) {
		Agent agent = new Agent();
		TypedQuery<Agent> query = entityManager.createQuery(SELECT_QUERY, Agent.class);
		List<Agent> agents = query.getResultList();
		for (Agent gnt : agents){
			if (gnt.getAccountId() == id){
				agent = gnt;
			}
		}
		return agent;
	}

	@Override
	@Transactional
	public void updateAgent(Agent agent) {
		Agent gnt = getAgent(agent.getId());
		gnt.setFName(agent.getFName());
		gnt.setLName(agent.getLName());
	}

}
