package com.spotlight.Goodbuy.Parsers;

import com.spotlight.Goodbuy.Entities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser implements IParser{
	String xmlFile;
	DocumentBuilderFactory dbFactory;
	Document dom;
	public Parser() {
		this.xmlFile = "shops.xml";
		this.getBuilder();
		this.parseData(xmlFile);
	}
	public Parser(String xmlFile) {
		this.xmlFile = xmlFile;
		this.getBuilder();
		this.parseData(xmlFile);
		
	}

	public Object parseData(String xmlFile) {
		DocumentBuilder db = this.getBuilder();
		try
		{
			dom = db.parse(xmlFile);
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		catch(SAXException se)
		{
			se.printStackTrace();
		}
		return null;
	}
	
	public  DocumentBuilder getBuilder() {
		// get factory;
		dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try
		{
			db = dbFactory.newDocumentBuilder();
		}	
		catch(ParserConfigurationException pce)
		{
			pce.printStackTrace();
		}
		
		return db;
	}
	
	public Collection<Entity> populateCol()
	{
		ArrayList<Parser> arr = null;
		return null;
	}
	
	protected String getTextValue(Element el, String tagName)
	{
		String texVal = null;
		NodeList nl = el.getElementsByTagName(tagName);
		
		if(nl == null || nl.getLength()<=0) return null;
		
		Element childEl = (Element) nl.item(0);
		texVal = childEl.getFirstChild().getNodeValue();
		return texVal;
	}
	protected int getIntValue(Element el, String tagName)
	{
		Integer intVal = null;
		try {
			intVal = Integer.parseInt(getTextValue(el, tagName));
		}
		catch(NumberFormatException nfe)
		{
			nfe.printStackTrace();
		}
		return intVal;
	}
	protected boolean getBoolValue(Element el, String tagName) {
		String texVal = getTextValue(el, tagName);
		boolean boolVal = false;
		if(texVal.equals("True") || texVal.equals("1")) {
			boolVal = true;
		}
		else boolVal = false;
		return boolVal;
	}
}
