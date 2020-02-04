package com.novko.pdf;

import java.io.Serializable;

public class JasperReportModel implements Serializable{


	private static final long serialVersionUID = -8390026625772085574L;

	private Long id;
	private String productName;
	private String productcode;
	private Integer productaAmountDin;
	private Integer productAmountEuro;
	private Integer productQuantity;
	private Integer rabat;
	private Integer totalAmount;


	public JasperReportModel() {}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getProductaAmountDin() {
		return productaAmountDin;
	}

	public void setProductaAmountDin(Integer productaAmountDin) {
		this.productaAmountDin = productaAmountDin;
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

	public Integer getRabat() {
		return rabat;
	}

	public void setRabat(Integer rabat) {
		this.rabat = rabat;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "JasperReportModel{" +
				"id=" + id +
				", productName='" + productName + '\'' +
				", productcode='" + productcode + '\'' +
				", productaAmountDin=" + productaAmountDin +
				", productAmountEuro=" + productAmountEuro +
				", productQuantity=" + productQuantity +
				", rabat=" + rabat +
				", totalAmount=" + totalAmount +
				'}';
	}
}
