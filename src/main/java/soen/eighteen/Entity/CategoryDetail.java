package soen.eighteen.Entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "CategoryDetails")


public class CategoryDetail implements Serializable {

	private static final long serialVersionUID = 3631811376762979200L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryDetailID")
	private int categoryDetailID;
	
	@ManyToOne
	@JoinColumn(name = "bookID")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "categoryID")
	private  Category category;

	public CategoryDetail() {
		super();
	}

	public CategoryDetail(int categoryDetailID, Book book, Category category) {
		super();
		this.categoryDetailID = categoryDetailID;
		this.book = book;
		this.category = category;
	}

	public int getCategoryDetailID() {
		return categoryDetailID;
	}

	public void setCategoryDetailID(int categoryDetailID) {
		this.categoryDetailID = categoryDetailID;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
