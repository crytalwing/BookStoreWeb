package soen.eighteen.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name = "Categories")

public class Category implements Serializable{

	private static final long serialVersionUID = 2564429180981511874L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryID")
	private int categoryID;
	
	@Column(name = "name",columnDefinition = "nvarchar(50)")
	private String name;
	
	@Column(name = "description",columnDefinition = "nvarchar(100)")
	private String description;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL )
	private List<CategoryDetail> categoryDetails;

	public Category() {
		super();
	}


	public Category(int categoryID, String name, String description, List<CategoryDetail> categoryDetails) {
		super();
		this.categoryID = categoryID;
		this.name = name;
		this.description = description;
		this.categoryDetails = categoryDetails;
	}


	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
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


	public List<CategoryDetail> getCategoryDetails() {
		return categoryDetails;
	}

	public void setCategoryDetails(List<CategoryDetail> categoryDetails) {
		this.categoryDetails = categoryDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
