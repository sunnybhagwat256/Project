package com.infosys.order.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderdetails")
public class OrderDetails {
	
	@Id
	@Column(name = "orderid",nullable = false)
	int orderid;
	
	@Column(name = "buyerid",nullable = false)
	int buyerid;
	
	@Column(name = "amount",nullable = false,precision=10, scale=2)
	float amount;
	
	@Column(name = "date")
	Date date;
	
	@Column(nullable = false, length = 100)
	String address;
	
	@Column(nullable = false, length = 60)
	String status;
	
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public OrderDetails() {
		super();
	}

}
