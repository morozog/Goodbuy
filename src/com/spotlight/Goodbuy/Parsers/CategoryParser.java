package com.spotlight.Goodbuy.Parsers;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.spotlight.Goodbuy.Entities.CategoryEntity;
import com.spotlight.Goodbuy.Entities.Entity;
import com.spotlight.Goodbuy.Entities.ProductEntity;

public class CategoryParser extends Parser {
	private String xmlFile;
	public String getXmlFile()
	{
		return this.xmlFile;
	}
	public void setXmlFile(String xmlFile)
	{
		this.xmlFile = xmlFile;
	}
	public CategoryParser() {
		super();
	}
	public CategoryParser(String xmlFile) {
		super(xmlFile);
	}
	@Override
	public Collection<Entity> populateCol() {
		if(this.dom == null) return null;
		ArrayList<Entity> prodList = new ArrayList<Entity>();
		Element docEle = dom.getDocumentElement();
		NodeList nl = docEle.getElementsByTagName("Category");
		
		if(nl == null || nl.getLength()<=0) return null;
		
		for(int i = 0; i<nl.getLength(); i++)
		{
			// get the shops element
			Element el =(Element) nl.item(i);
			CategoryEntity c = getCategory(el);
			prodList.add(c);
		}
		return prodList;
	}
	private CategoryEntity getCategory(Element el) {
		int id = super.getIntValue(el, "Id");
		String name = super.getTextValue(el, "Name");
		String description = super.getTextValue(el, "Description");
		int shopId = super.getIntValue(el, "ShopId");
		return new CategoryEntity(id, name, description, shopId);
	}
	
}
