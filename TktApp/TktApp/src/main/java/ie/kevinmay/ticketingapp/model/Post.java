package ie.kevinmay.ticketingapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "POST")
public class Post {

	@Id 
	@GeneratedValue
	@Column(name = "post_id")
	private int id;
	
	@Column(name= "body")
	private String body;
	
	@Column(name="author")
	private int author;
	
	@Column(name="author_name")
	private String authorName;
	
	@Column (name="date_created")
	@Temporal(TemporalType.DATE)
	private Date dateCreated;
	
	@Column (name="ticket_id")
	private int ticketId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public String getAuthorName(){
		return authorName;
	}
	
}
