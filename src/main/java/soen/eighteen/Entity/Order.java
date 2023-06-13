package soen.eighteen.Entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name="Orders")
public class Order implements Serializable{

	private static final long serialVersionUID = 4940183620758803457L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orderID")
	private int orderID;
	
	@Column(name="orderDate")
	private Date orderDate;
	
	@Column(name="totalPrice")
	private int totalPrice;
	
	@Column(name ="status")
	private int status;
	//(fetch=FetchType.EAGER)
	@ManyToOne
	@JoinColumn(name="username")
	private Customer customer;
	
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<OrderDetail> oderDetails;

	public Order() {
		super();
	}

	public Order(int orderID, Date orderDate, int totalPrice, int status, Customer customer,
			List<OrderDetail> oderDetails) {
		super();
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.status = status;
		this.customer = customer;
		this.oderDetails = oderDetails;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<OrderDetail> getOderDetails() {
		return oderDetails;
	}

	public void setOderDetails(List<OrderDetail> oderDetails) {
		this.oderDetails = oderDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
