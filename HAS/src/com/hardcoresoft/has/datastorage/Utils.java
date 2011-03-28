package com.hardcoresoft.has.datastorage;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Utils {
	
	//Public static functions
	/**
	 * Function: public static String getTextValue(Element ele, String tagName)
	 * Parameters: Element ele - element to analyze. String tagName - tag to extract..
	 * Description: Returns a string of the value stored in a tag. 
	 */
	public static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		try
		{
			NodeList nl = ele.getElementsByTagName(tagName);
			if(nl != null && nl.getLength() > 0) {
				Element el = (Element)nl.item(0);
				textVal = el.getFirstChild().getNodeValue();
			}
			return textVal;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Function: public static int getIntValue(Element ele, String tagName)
	 * Parameters: Element ele - element to analyze. String tagName - tag to extract..
	 * Description: Returns a integer of the value stored in a tag. 
	 */
	public static int getIntValue(Element ele, String tagName) {
		try
		{
			return Integer.parseInt(getTextValue(ele,tagName));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Function: public static double getDoubleValue(Element ele, String tagName)
	 * Parameters: Element ele - element to analyze. String tagName - tag to extract..
	 * Description: Returns a double of the value stored in a tag. 
	 */
	public static double getDoubleValue(Element ele, String tagName){
		try
		{
			return Double.parseDouble(getTextValue(ele,tagName));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}


}
