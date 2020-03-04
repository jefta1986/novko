package com.novko.pdf;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "T_TRANSACTIONS")
public class JasperReportModel implements Serializable{


	private static final long serialVersionUID = -8390026625772085574L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "seq_products_gen", sequenceName = "seq_products", allocationSize = 1, initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_products_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Long id;


	@Column(name = "ORDER_ID")
	private Long orderId;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_CODE")
	private String productcode;

	@Column(name = "PRODUCT_AMOUNT_DIN")
	private Integer productAmountDin;

	@Column(name = "PRODUCT_AMOUNT_EURO")
	private Integer productAmountEuro;

	@Column(name = "PRODUCT_QUANTITY")
	private Integer productQuantity;

	@Column(name = "RABAT")
	private Double rabat;

	@Column(name = "TOTAL_AMOUNT_DIN")
	private Integer totalAmountDin;

	@Column(name = "TOTAL_AMOUNT_EURO")
	private Integer totalAmountEuro;



	public JasperReportModel() {}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	public Integer getProductAmountDin() {
		return productAmountDin;
	}

	public void setProductAmountDin(Integer productAmountDin) {
		this.productAmountDin = productAmountDin;
	}

	public Integer getProductAmountEuro() {
		return productAmountEuro;
	}

	public void setProductAmountEuro(Integer productAmountEuro) {
		this.productAmountEuro = productAmountEuro;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Double getRabat() {
		return rabat;
	}

	public void setRabat(Double rabat) {
		this.rabat = rabat;
	}





	public Integer getTotalAmountDin() {
		return totalAmountDin;
	}

	public void setTotalAmountDin(Integer totalAmountDin) {
		this.totalAmountDin = totalAmountDin;
	}

	public Integer getTotalAmountEuro() {
		return totalAmountEuro;
	}

	public void setTotalAmountEuro(Integer totalAmountEuro) {
		this.totalAmountEuro = totalAmountEuro;
	}


	@Override
	public String toString() {
		return "JasperReportModel{" +
				"id=" + id +
				", orderId=" + orderId +
				", productName='" + productName + '\'' +
				", productcode='" + productcode + '\'' +
				", productAmountDin=" + productAmountDin +
				", productAmountEuro=" + productAmountEuro +
				", productQuantity=" + productQuantity +
				", rabat=" + rabat +
				", totalAmountDin=" + totalAmountDin +
				", totalAmountEuro=" + totalAmountEuro +
				'}';
	}
}
