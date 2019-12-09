package com.novko.internal.customers;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_CUSTOMERS")
public class Customer implements Serializable{

	private static final long serialVersionUID = -6053636638896252258L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "seq_customers_gen", sequenceName = "seq_customers", allocationSize = 1, initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_customers_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Long id;

	
	@Column(name = "EMAIL", unique = true)
	private String email;
	
	@Column(name = "NAME")
	private String name;
	
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	
	
//	@Embedded
//	@AttributeOverride(name = "value", column = @Column(name = "PERCENTAGE"))
//	private Percentage percentage = Percentage.oneHundred();
//	
//	@Embedded
//	@AttributeOverride(name = "value", column = @Column(name = "AMOUNT"))
//	private MonetaryAmount amount = MonetaryAmount.zero();

	public Customer() {
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	@Override
	public String toString() {
		return "Customers [id=" + id + ", email=" + email + ", name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}

	

	
	
	
}
