package com.spotlight.Goodbuy.Parsers;

import com.spotlight.Goodbuy.Entities.*;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ProductParser extends Parser{
	private String xmlFile;
	public String getXmlFile()
	{
		return this.xmlFile;
	}
	public void setXmlFile(String xmlFile)
	{
		this.xmlFile = xmlFile;
	}
	public ProductParser() {
		super();
	}
	public ProductParser(String xmlFile) {
		super(xmlFile);
	}
	
	@Override
	public Collection<Entity> populateCol() {
		
		if(this.dom == null) return null;
		
		ArrayList<Entity> prodList = new ArrayList<Entity>();
		Element docEle = dom.getDocumentElement();
		NodeList nl = docEle.getElementsByTagName("Product");
		
		if(nl == null || nl.getLength()<=0) return null;
		
		for(int i = 0; i<nl.getLength(); i++)
		{
			// get the shops element
			Element el =(Element) nl.item(i);
			ProductEntity p = getProduct(el);
			prodList.add(p);
		}
		return prodList;
	}
	private ProductEntity getProduct(Element el)
	{
		int id = super.getIntValue(el, "Id");
		//int shopId = super.getIntValue(el, "ShopId");
		String name = super.getTextValue(el, "Name");
		String description = super.getTextValue(el, "Description");
		String price = super.getTextValue(el, "Price");
		String image = super.getTextValue(el, "Image");
		boolean availability = super.getBoolValue(el, "Available");
		//int productCatId = super.getIntValue(el, "ProductCatId");
		// populate object
		ProductEntity p = new ProductEntity(id, 0, name, description, price, availability, 0, image);
		
		return p;
	}
}
