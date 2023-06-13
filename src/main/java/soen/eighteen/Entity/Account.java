package soen.eighteen.Entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name= "Accounts")
public class Account implements Serializable {
	private static final long serialVersionUID = 6382099345978986565L;

	@Id 
//    @GeneratedValue(strategy = GenerationType.AUTO)
	private String username;
	
	@Column(name="password")
	private String password;

	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
	private Employee employee;

	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private Customer customer;

	public Account() {
		super();
	}


	public Account(String username, String password, Employee employee) {
		super();
		this.username = username;
		this.password = password;
		this.employee = employee;
	}
	public Account(String username, String password, Customer customer) {
		super();
		this.username = username;
		this.password = password;
		this.customer = customer;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
