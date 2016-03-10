package ie.kevinmay.ticketingapp.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ie.kevinmay.ticketingapp.dao.PostDAO;
import ie.kevinmay.ticketingapp.model.Post;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Component // declares PostService as a Spring bean.
@Path("/postservice") //a JAX-RS annotation that declares TicketService as a "root" JAX-RS resource.
@Api( value = "post")
public class PostServiceImpl implements PostService {

	@Autowired // requests a reference to the TicketDAO, which Spring will provide. 
	private PostDAO postDAO;

	@GET
	@Path("/posts")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation( 
		    value = "List all Posts", 
		    response = Post.class, 
		   responseContainer = "List"
		)
	public List<Post> listPosts() {
		List<Post> posts = postDAO.listPosts();

		return posts;
	}

}
