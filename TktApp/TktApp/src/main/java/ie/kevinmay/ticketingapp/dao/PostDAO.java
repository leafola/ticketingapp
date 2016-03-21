package ie.kevinmay.ticketingapp.dao;

import java.util.List;

import ie.kevinmay.ticketingapp.model.Post;

/**
 * Defines DAO operations for the ticket model.
 */

public interface PostDAO {
	
	public List<Post> listPosts();
	public List<Post> byTicket(int id);
	public void createPost(Post post);

}
