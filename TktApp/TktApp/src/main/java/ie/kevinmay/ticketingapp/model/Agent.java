package ie.kevinmay.ticketingapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AGENT")
public class Agent {

	@Id 
	@GeneratedValue
	@Column(name = "agent_id")
	private int id;
	
	@Column(name= "f_name")
	private String fName;
	
	@Column(name= "l_name")
	private String lName;
	
	@Column (name="account_id")
	private int accountId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
}
