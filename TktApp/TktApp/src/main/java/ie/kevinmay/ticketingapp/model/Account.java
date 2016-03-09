package ie.kevinmay.ticketingapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account {
	
	@Id 
	@GeneratedValue
	@Column(name = "account_id")
	private int id;
	
	@Column(name= "username")
	private String userame;
	
	@Column(name= "pword")
	private String pword;
	
	@Column(name= "role")
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserame() {
		return userame;
	}

	public void setUserame(String userame) {
		this.userame = userame;
	}

	public String getPword() {
		return pword;
	}

	public void setPword(String pword) {
		this.pword = pword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
