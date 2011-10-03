package com.spotlight.Goodbuy.Entities;

public class ProductEntity extends Entity{
	private int id;
	public int getId()
	{
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int shopId;
	public int getShopId()
	{
		return this.shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	private String name;
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String description;
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String price;
	public String getPrice() {
		return this.price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	private boolean availability;
	public boolean getAvail() {
		return this.availability;
	}
	public void setAvail(boolean availability) {
		this.availability = availability;
	}
	private int productCatId;
	public int getProductCatId() {
		return this.productCatId;
	}
	public void setProductCatId(int productCatId) {
		this.productCatId = productCatId;
	}
	private String image;
	public String getImage()
	{
		return this.image;
	}
	public void setImage(String image)
	{
		this.image = image;
	}
	public ProductEntity() {}
	public ProductEntity(int id, int shopId, String name, String description,
						 String price, boolean availability, int productCatId, String image) {
		this.id = id;
		this.shopId = shopId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.availability = availability;
		this.productCatId = productCatId;
		this.image = image;
	}
}
