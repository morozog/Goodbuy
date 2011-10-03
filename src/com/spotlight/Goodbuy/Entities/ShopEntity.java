package com.spotlight.Goodbuy.Entities;

public class ShopEntity extends Entity {
	private int id;
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	private String name;
	public String getName()
	{
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String description;
	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String address;
	public String getAddress()
	{
		return this.address;
	}
	public void setAddress(String address)
	{
		this.address = address;
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
	private String telephone;
	public String getTelephone()
	{
		return this.telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public ShopEntity()
	{}
	public ShopEntity(int id, String name, String description, 
						String address, String telephone, String image) {
		this.id = id; 
		this.name = name;
		this.description = description;
		this.address = address;
		this.telephone = telephone;
		this.image = image;
	}
}
