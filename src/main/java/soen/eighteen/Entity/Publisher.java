package soen.eighteen.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
@Table(name="Publishers")

public class Publisher implements Serializable{

	private static final long serialVersionUID = -2929638480396479322L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "publisherID")
	private int publisherID;
	
	@Column(name = "name",columnDefinition = "nvarchar(50)")
	private String name;
	
	@Column(name = "description",columnDefinition = "nvarchar(100)")
	private String description;
	
	
	@Column(name="address",columnDefinition = "nvarchar(MAX)")
	private String address;
	
	@Column(name="country",columnDefinition = "nvarchar(50)")
	private String country;
	
	@OneToMany (mappedBy = "publisher",cascade = CascadeType.ALL)
	private List<Book> books  = new ArrayList<>();

	public Publisher() {
		super();
	}



	public Publisher(int publisherID, String name, String description, String address, String country,
			List<Book> books) {
		super();
		this.publisherID = publisherID;
		this.name = name;
		this.description = description;
		this.address = address;
		this.country = country;
		this.books = books;
	}



	public int getPublisherID() {
		return publisherID;
	}

	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
