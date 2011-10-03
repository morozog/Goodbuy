package com.spotlight.Goodbuy.Parsers;

import com.spotlight.Goodbuy.Entities.*;

import java.util.*;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ShopParser extends Parser {
	
	private String xmlFile;
	public String getXmlFile()
	{
		return this.xmlFile;
	}
	public void setXmlFile(String xmlFile)
	{
		this.xmlFile = xmlFile;
	}
	public ShopParser() {
		super();
	}
	public ShopParser(String xmlFile)
	{
		super(xmlFile);
	}
	
	@Override
	public Collection<Entity> populateCol() {
		if(this.dom == null) return null;
		
		ArrayList<Entity> shopList = new ArrayList<Entity>();
		Element docEle = dom.getDocumentElement();
		NodeList nl = docEle.getElementsByTagName("Shop");
		
		if(nl == null || nl.getLength()<=0) return null;
		
		for(int i = 0; i<nl.getLength(); i++)
		{
			// get the shops element
			Element el =(Element) nl.item(i);
			ShopEntity s = getShop(el);
			shopList.add(s);
		}
		return shopList;
	}
	
	private ShopEntity getShop(Element el) {
		int id = getIntValue(el, "Id");
		String name = super.getTextValue(el, "Name");
		String description = super.getTextValue(el, "Description");
		String address = super.getTextValue(el, "Address");
		String telephone = super.getTextValue(el, "Telephone");
		String image = super.getTextValue(el, "Image");
		ShopEntity s = new ShopEntity(id, name, description, address, telephone, image);
		return s;
	}
}
