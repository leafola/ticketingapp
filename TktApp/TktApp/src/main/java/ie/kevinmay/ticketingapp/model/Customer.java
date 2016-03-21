package ie.kevinmay.ticketingapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "CUSTOMER")
@ApiModel( value = "Customer", description = "Customer resource representation" )
public class Customer {

	@Id 
	@GeneratedValue
	@Column(name = "customer_id")
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

	public String getFName() {
		return fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
		
	
}
