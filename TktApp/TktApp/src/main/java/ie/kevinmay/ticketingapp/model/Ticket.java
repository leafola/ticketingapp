package ie.kevinmay.ticketingapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TICKET")
public class Ticket {
	
	@Id 
	@GeneratedValue
	@Column(name = "ticket_id")
	private int id;
	
	@Column(name= "title")
	private String title;
	
	@Column (name="date_created")
	private Date dateCreated; 
	
	@Column(name= "customer_id")
	private int  customerId;
	
	@Column(name= "agent_id")
	private int  agentId;
	
	@Column (name="closed")
	private boolean closed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean getClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}
	public int getCustomerId() {
		return customerId;
	}
	
	public void setAgentId (int agentId) {
		this.agentId = agentId;
	}
	
	public int getAgentId (){
		return agentId;
	}

}
