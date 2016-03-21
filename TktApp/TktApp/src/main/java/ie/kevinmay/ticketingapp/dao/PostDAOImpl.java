package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ie.kevinmay.ticketingapp.model.Post;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * An implementation of the ticketDAO interface.
 */
@Repository("postDAO")
public class PostDAOImpl implements PostDAO {

	private static final String SELECT_QUERY = "select p from Post p";
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
	public List<Post> listPosts() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		List<Post> posts = (List<Post>) query.getResultList();
		return posts;
	}

	@Override
	@Transactional
	public void createPost(Post post) {
		Post pst = new Post();
		pst.setBody(post.getBody());
		pst.setTicketId(post.getTicketId());
		pst.setAuthor(post.getAuthor());
		pst.setDateCreated(post.getDateCreated());
		entityManager.persist(pst);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Post> byTicket(int id) {
		Query query = entityManager.createQuery(SELECT_QUERY + " where p.ticketId = :id").setParameter("id", id);
		List<Post> posts = (List<Post>) query.getResultList();
		return posts;
	}


}
