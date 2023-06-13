package soen.eighteen.Entity;


import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="OrderDetails")
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 2375650290276602584L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderDetailID;
	
	@ManyToOne
	@JoinColumn(name = "orderID")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "bookID")
	private Book book;

	public OrderDetail() {
		super();
	}

	public OrderDetail(int orderDetailID, Order order, Book book) {
		super();
		this.orderDetailID = orderDetailID;
		this.order = order;
		this.book = book;
	}

	public int getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(int orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
