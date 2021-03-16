package com.infosys.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompositeKey implements Serializable {
	
	private static final long serialVersionUID=1L;
	
	@Column(name="orderid")
	int orderid;
	@Column(name="prodid")
	int prodid;
	
	
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
	
	
	
	
}
