package ie.kevinmay.model;

public class Ticket {
	private int id;
	private String title;

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

}
