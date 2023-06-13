package soen.eighteen.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Employees")

public class Employee implements Serializable {

	private static final long serialVersionUID = -855038637130694610L;
	
	@Id
	private String username;

	@Column(name="fullName",columnDefinition = "nvarchar(50)")
	private String fullName;
	
	@Column(name="phoneNumber",columnDefinition = "nvarchar(10)")
	private String phoneNumber;
	
	@Column(name="birthDate")
	private Date birthDate;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name="username")
	@MapsId
	private Account account;
	

	public Employee() {
		super();
	}

	public Employee(String username, String fullName, String phoneNumber, Date birthDate, Account account) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.account = account;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
