package ie.kevinmay.ticketingapp.model;

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
	
	@Column(name= "customer")
	private String  customer;

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
	
	public void setCustomer(String customer){
		this.customer = customer;
	}
	public String getCustomer() {
		return customer;
	}

}
