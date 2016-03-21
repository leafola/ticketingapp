package ie.kevinmay.ticketingapp.service;

import java.util.List;

import ie.kevinmay.ticketingapp.model.Post;

public interface PostService {
	public List<Post> listPosts();
	public void createPost(Post post);
	public List<Post> byTicket(int id);
}
