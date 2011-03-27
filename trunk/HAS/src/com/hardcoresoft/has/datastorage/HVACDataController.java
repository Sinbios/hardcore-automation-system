package com.hardcoresoft.has.datastorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.io.File;
import java.io.FileOutputStream;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;



public class HVACDataController {

	//Member variables
	Document oHVACDomRead;
	Document oHVACDomWrite;
	HVACData oHVACData;
	
	//Constructors
	public HVACDataController(){
		oHVACData = new HVACData();
		//Read in the HVAC XML file.
		readHVACData();
	}
	
	//Private functions
	private void parseHVACXmlFile(String filepath){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//parse using builder to get DOM representation of the XML file
			oHVACDomRead = db.parse(filepath);
			

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void parseHVACDocument(){
		//get the root elememt
		Element docEle = oHVACDomRead.getDocumentElement();		
			
		oHVACData.setbConnected(Boolean.parseBoolean(Utils.getTextValue(docEle,"tns:connected")));
		oHVACData.setsIpAddress(Utils.getTextValue(docEle,"tns:ipAddress"));
		oHVACData.setnCurrentTemperature(Utils.getDoubleValue(docEle, "tns:currentTemperature"));
		oHVACData.setnDesiredTemperature(Utils.getDoubleValue(docEle, "tns:desiredTemperature"));
		oHVACData.setnPort(Utils.getIntValue(docEle, "tns:port"));
		oHVACData.setoStatus(convertIntToHVACStatus(Utils.getIntValue(docEle, "tns:status")));
		//get a nodelist of schedules
		NodeList nl = docEle.getElementsByTagName("tns:action");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				//get the element
				Element el = (Element)nl.item(i);
				HVACSchedule oTempSchedule = oHVACData.getoSchedule();
				//Add to schedule
				oTempSchedule.addScheduledAction(getScheduleNode(el));
				oHVACData.setoSchedule(oTempSchedule);
			}
		}
	}
	
	private HVACScheduleNode getScheduleNode(Element schdEl){

		DateFormat oDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		try
		{
			Date oParseDate = oDf.parse(Utils.getTextValue(schdEl, "tns:date"));
			HVACScheduleNode oSchedule = new HVACScheduleNode(oParseDate,Double.parseDouble(Utils.getTextValue(schdEl, "tns:desiredTemperature")));
			return oSchedule;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * Using JAXP in implementation independent manner create a document object
	 * using which we create a xml tree in memory
	 */
	private void createHVACDocument() {

		//get an instance of factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
		//get an instance of builder
		DocumentBuilder db = dbf.newDocumentBuilder();

		//create an instance of DOM
		oHVACDomWrite = db.newDocument();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The real workhorse which creates the XML structure
	 */
	private void createHVACDOMTree(){
		try{
			//create the root element 
			Element rootEle = oHVACDomWrite.createElement("tns:hvac");
			rootEle.setAttribute("xmlns:tns", "http://www.example.org/hvac");
			rootEle.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			rootEle.setAttribute("xsi:schemaLocation", "http://www.example.org/hvac hvac.xsd ");
			oHVACDomWrite.appendChild(rootEle);
			//Create the connected element
			Element connectedEle = oHVACDomWrite.createElement("tns:connected");
			Text connectedText = oHVACDomWrite.createTextNode(oHVACData.getbConnected().toString());
			connectedEle.appendChild(connectedText);
			rootEle.appendChild(connectedEle);
			//Create the ipAddress element
			Element ipAddrEle = oHVACDomWrite.createElement("tns:ipAddress");
			Text ipAddrText = oHVACDomWrite.createTextNode(oHVACData.getsIpAddress());
			ipAddrEle.appendChild(ipAddrText);
			rootEle.appendChild(ipAddrEle);
			//Create the current temp element.
			Element currTempEle = oHVACDomWrite.createElement("tns:currentTemperature");
			Text currTempText = oHVACDomWrite.createTextNode(Double.toString(oHVACData.getnCurrentTemperature()));
			currTempEle.appendChild(currTempText);
			rootEle.appendChild(currTempEle);
			//Create the desired temp element.
			Element desiredTempEle = oHVACDomWrite.createElement("tns:desiredTemperature");
			Text desiredTempText = oHVACDomWrite.createTextNode(Double.toString(oHVACData.getnDesiredTemperature()));
			desiredTempEle.appendChild(desiredTempText);
			rootEle.appendChild(desiredTempEle);
			//Create the port element.
			Element portEle = oHVACDomWrite.createElement("tns:port");
			Text portText = oHVACDomWrite.createTextNode(Integer.toString(oHVACData.getnPort()));
			portEle.appendChild(portText);
			rootEle.appendChild(portEle);
			//Generate the schedule aspect using a helper function
			rootEle.appendChild(createHVACScheduleElement());
			//Add the status
			Element statusEle = oHVACDomWrite.createElement("tns:status");
			Text statusText = oHVACDomWrite.createTextNode(Integer.toString(oHVACData.getoStatus().ordinal()));
			statusEle.appendChild(statusText);
			rootEle.appendChild(statusEle);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Creates the HVAC schedule elements.
	 */
	private Element createHVACScheduleElement(){
		try{
			HVACSchedule scheduleData = oHVACData.getoSchedule();
			List<HVACScheduleNode> scheduleList = scheduleData.getoSchedule();
			Element schedEle = oHVACDomWrite.createElement("tns:schedule");
			DateFormat oDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			
			ListIterator<HVACScheduleNode> itr = scheduleList.listIterator();
			while(itr.hasNext()){
				HVACScheduleNode oItr = itr.next();
				Element actionEle = oHVACDomWrite.createElement("tns:action");
				//Create the data sub element.
				Element dateEle = oHVACDomWrite.createElement("tns:date");
				StringBuilder formattedDate = new StringBuilder( oDf.format(oItr.getDate()));
				Text dateText = oHVACDomWrite.createTextNode(formattedDate.toString());
				dateEle.appendChild(dateText);
				//Create the desired temp sub element.
				Element tempEle = oHVACDomWrite.createElement("tns:desiredTemperature");
				Text tempText = oHVACDomWrite.createTextNode(Double.toString(oItr.getDesiredTemp()));
				tempEle.appendChild(tempText);
				//Add both date and temp sub elements to the action element.
				actionEle.appendChild(dateEle);
				actionEle.appendChild(tempEle);
				schedEle.appendChild(actionEle);
			}
			return schedEle;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	
	/*
	 * This method uses Xerces specific classes
	 * prints the HVAC XML document to file.
     */
	private void printHVACXML(String filepath){

		try
		{
			//print
			OutputFormat format = new OutputFormat(oHVACDomWrite);
			format.setIndenting(true);

			//to generate output to console use this serializer
			//XMLSerializer serializer = new XMLSerializer(System.out, format);
			
			//to generate a file output use fileoutputstream instead of system.out
			//Change this directory to something more generic!!
			XMLSerializer serializer = new XMLSerializer(
			new FileOutputStream(new File(filepath)), format);

			serializer.serialize(oHVACDomWrite);

		} catch(IOException ie) {
		    ie.printStackTrace();
		}
	}
	
	//Public functions
	public void readHVACData()
	{
		parseHVACXmlFile("L:\\ece355_proj\\HAS\\src\\com\\hardcoresoft\\has\\datastorage\\hvac.xml");
		parseHVACDocument();
	}
	
	public void writeHVACData()
	{
		createHVACDocument();
		createHVACDOMTree();
		printHVACXML("L:\\ece355_proj\\HAS\\src\\com\\hardcoresoft\\has\\datastorage\\hvac_test.xml");
	}
	
	public static HVACStatus convertIntToHVACStatus(int value)
	{
		return HVACStatus.class.getEnumConstants()[value];
	}
}
