package soen.eighteen.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Books")
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
@NamedQuery(name = "Book.GetBestBookByRate", query = "SELECT b FROM Book b order by b.rating")
@NamedQuery(name = "Book.GetLastBook", query = "SELECT b FROM Book b")
public class Book implements Serializable{

	private static final long serialVersionUID = -2981884999935798032L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookID")
	private int bookID;
	
	@Column(name = "name",columnDefinition = "nvarchar(50)")
	private String name;
	
	@Column(name = "description",columnDefinition = "nvarchar(100)")
	private String description;
	
	
	@Column(name="price")
	private int price;
	
	@Column(name="author",columnDefinition = "nvarchar(50)")
	private String author;
	
	@Column(name="language",columnDefinition = "nvarchar(50)")
	private String language;
	
	
	@Column(name="rating")
	private int rating;
	
	@Column(name="images")
	private String images;
	
	@ManyToOne
	@JoinColumn(name="publisherID")
	private Publisher publisher;
	
	@OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
	private List<CategoryDetail> categoryDetails;

	public Book() {
		super();
	}

	
	public Book(int bookID, String name, String description, int price, String author, String language,
			int rating, String images, Publisher publisher, List<OrderDetail> orderDetails,
			List<CategoryDetail> categoryDetails) {
		super();
		this.bookID = bookID;
		this.name = name;
		this.description = description;
		this.price = price;
		this.author = author;
		this.language = language;
		this.rating = rating;
		this.images = images;
		this.publisher = publisher;
		this.orderDetails = orderDetails;
		this.categoryDetails = categoryDetails;
	}


	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
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


	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public List<CategoryDetail> getCategoryDetails() {
		return categoryDetails;
	}

	public void setCategoryDetails(List<CategoryDetail> categoryDetails) {
		this.categoryDetails = categoryDetails;
	}


}
