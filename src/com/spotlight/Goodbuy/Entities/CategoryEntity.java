package com.spotlight.Goodbuy.Entities;

public class CategoryEntity extends Entity{
	private int id;
	private String name;
	private String description;
	private int shopId;
	public CategoryEntity() {
		super();
	}
	public CategoryEntity(int id, String name, String description, int shopId) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.shopId = shopId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getShopId()
	{
		return this.shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	@Override
	public String toString() {
		return id + " " + name + " " + description + " " + shopId;
	}
	
}	
