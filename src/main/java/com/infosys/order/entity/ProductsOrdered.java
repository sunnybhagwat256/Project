package com.infosys.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="productsordered")
@IdClass(CompositeKey.class)
public class ProductsOrdered implements Serializable{
private static final long serialVersionUID=1L;

	@Id
	@Column(name="orderid")
	int orderid;
	@Id
	@Column(name="prodid")
	int prodid;
	@Column(nullable = false)
	int sellerid;
	@Column(nullable = false)
	int quantity;
	@Column(nullable = false, length = 60)
	String status;
	@Column(precision=10, scale=2)
	double price;
	
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public int getSellerid() {
		return sellerid;
	}
	public void setSellerid(int sellerId) {
		this.sellerid = sellerId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public ProductsOrdered() {
		super();
	}
	
}
