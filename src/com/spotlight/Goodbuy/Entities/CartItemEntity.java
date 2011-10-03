package com.spotlight.Goodbuy.Entities;

public class CartItemEntity extends Entity{
	private int id;
	private String prodName;
	private int prodId;
	private int quantity;

	public CartItemEntity() {
		super();
	}
	public CartItemEntity(int id, String prodName, int prodId, int quantity) {
		this.setId(id);
		this.setProdName(prodName);
		this.setProdId(prodId);
		this.setQuantity(quantity);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getQuantity()
	{
		return this.quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getProdId()
	{
		return this.prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	@Override
	public String toString() {
		return id + " " + prodName + " " + Integer.toString(quantity) + " " + Integer.toString(prodId);
	}
}
