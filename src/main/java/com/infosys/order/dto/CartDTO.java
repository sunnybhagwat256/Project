package com.infosys.order.dto;


public class CartDTO {

	Integer buyerid;
	Integer prodid;
	Integer quantity;

	public CartDTO() {
		super();
	}

	public CartDTO(Integer buyerid, Integer prodid, Integer quantity) {
		super();
		this.buyerid = buyerid;
		this.prodid = prodid;
		this.quantity = quantity;
	}

	public Integer getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(Integer buyerid) {
		this.buyerid = buyerid;
	}

	public Integer getProdid() {
		return prodid;
	}

	public void setProdid(Integer prodid) {
		this.prodid = prodid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartDTO [buyerid=" + buyerid + ", prodid=" + prodid + ", quantity=" + quantity + "]";
	}

}
