package ie.kevinmay.ticketingapp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Column (name="date_created")
	private Timestamp dateCreated;
	
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

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
}
