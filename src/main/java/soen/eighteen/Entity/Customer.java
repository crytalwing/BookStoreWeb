package soen.eighteen.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name="Customers")
public class Customer implements Serializable {
	
	private static final long serialVersionUID = -5189938221676295167L;

	@Id
	private String username;
	
	@Column(name = "fullName",columnDefinition = "nvarchar(50)")
	private String fullName;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(name = "address",columnDefinition = "nvarchar(100)")
	private String address;
	
	@Column(name = "balance")
	private int balance;

	@Column(name = "point")
	private int point;
	

	@OneToOne
    @MapsId
    @JoinColumn(name = "username")
	private Account account;
	
	//,cascade = CascadeType.ALL
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;




	public Customer() {
		super();
	}




	public Customer(String username, String fullName, String phoneNumber, String address, int balance, int point,
			Account account, List<Order> orders) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.balance = balance;
		this.point = point;
		this.account = account;
		this.orders = orders;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getFullName() {
		return fullName;
	}




	public void setFullName(String fullName) {
		this.fullName = fullName;
	}




	public String getPhoneNumber() {
		return phoneNumber;
	}




	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public int getBalance() {
		return balance;
	}




	public void setBalance(int balance) {
		this.balance = balance;
	}




	public int getPoint() {
		return point;
	}




	public void setPoint(int point) {
		this.point = point;
	}




	public Account getAccount() {
		return account;
	}




	public void setAccount(Account account) {
		this.account = account;
	}



	public List<Order> getOrders() {
		return orders;
	}




	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}




	
}

