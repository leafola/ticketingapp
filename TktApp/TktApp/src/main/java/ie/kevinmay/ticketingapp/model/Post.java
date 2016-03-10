package ie.kevinmay.ticketingapp.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn (name="ticket_id")
	public Ticket ticket; 

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

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicketId(Ticket ticket) {
		this.ticket = ticket;
	}
	
}
