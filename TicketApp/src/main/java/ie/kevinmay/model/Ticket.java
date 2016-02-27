package ie.kevinmay.model;

public class Ticket {
	private int id;
	private String title;
	private String  customer;

	public Ticket() {
	}

	public Ticket(String title) {
		this.title = title;
	}

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
