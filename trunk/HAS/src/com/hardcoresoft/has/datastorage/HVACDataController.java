package com.hardcoresoft.has.datastorage;

import java.io.IOException;
import java.util.Date;
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
import java.io.File;
import java.io.FileOutputStream;


import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;


/**
 * Function: public class HVACDataController
 * Description: Performs read and writes to the HVAC XML file.
 */
public class HVACDataController {

	//Member variables
	Document oHVACDomRead;
	Document oHVACDomWrite;
	HVACData oHVACData;
	
	public HVACData getoHVACData() {
		return oHVACData;
	}

	public void setoHVACData(HVACData oHVACData) {
		this.oHVACData = oHVACData;
	}

	//Constructors
	public HVACDataController(){
		oHVACData = new HVACData();
		//Read in the HVAC XML file.
		readHVACData();
	}
	
	//Private functions
	/**
	 * Function: private void parseHVACXmlFile(String filepath)
	 * Parameters: String filepath: filepath of the XML file.
	 * Description: Parses the XML file to a DOM file, oHVACDomRead.
	 */
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
	
	/**
	 * Function: private void parseHVACDocument()
	 * Parameters: N/A
	 * Description: Parses the DOM file, oHVACDomRead, into the oHVACData storage class.
	 */
	private void parseHVACDocument(){
		try{
			//get the root elememt
			Element docEle = oHVACDomRead.getDocumentElement();		
				
			oHVACData.setbConnected(Boolean.parseBoolean(Utils.getTextValue(docEle,"tns:connected")));
			oHVACData.setsMsgQueueName(Utils.getTextValue(docEle,"tns:msgQueueName"));
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
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Function: HVACScheduleNode getScheduleNode(Element schdEl)
	 * Parameters: Element schdEl: schedule element.
	 * Description: Parses the schedule elements and returns a populated HVACScheduleNode.
	 */
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
	 * Function: private void createHVACDocument()
	 * Parameters: N/A
	 * Description: Using JAXP in implementation independent manner create a document object
	 * using which we create a xml tree in memory as oHVACDomWrite.
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
	 * Function: private void createHVACDOMTree()
	 * Parameters: N/A
	 * Description: The real workhorse which creates the XML structure.
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
			//Create the port element.
			Element portEle = oHVACDomWrite.createElement("tns:port");
			Text portText = oHVACDomWrite.createTextNode(Integer.toString(oHVACData.getnPort()));
			portEle.appendChild(portText);
			rootEle.appendChild(portEle);
			//Create the msgQueue element.
			Element msgQueueEle = oHVACDomWrite.createElement("tns:msgQueueName");
			Text msgQueueText = oHVACDomWrite.createTextNode(oHVACData.getsMsgQueueName());
			msgQueueEle.appendChild(msgQueueText);
			rootEle.appendChild(msgQueueEle);
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
			//Add the status
			Element statusEle = oHVACDomWrite.createElement("tns:status");
			Text statusText = oHVACDomWrite.createTextNode(Integer.toString(oHVACData.getoStatus().ordinal()));
			statusEle.appendChild(statusText);
			rootEle.appendChild(statusEle);
			//Generate the schedule aspect using a helper function
			rootEle.appendChild(createHVACScheduleElement());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Function: private Element createHVACScheduleElement()
	 * Parameters: N/A
	 * Description: Returns an Element which is populated with HVAC schedule data. Based on
	 * the data in oHVACDomWrite.
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
	
	/**
	 * Function: private void printHVACXML(String filepath)
	 * Parameters: String filepath: file path of the output XML file.
	 * Description: Writes a new XML based on oHVACDomWrite data to filepath. 
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
	
	/**
	 * Function: public void readHVACData()
	 * Parameters: N/A.
	 * Description: Wrapper function for reading in HVAC XML data.
	 */
	public void readHVACData()
	{
		
		parseHVACXmlFile(System.getProperty("catalina.home") + "/webapps/HAS/hvac.xml");
		parseHVACDocument();
	}
	
	/**
	 * Function: public void writeHVACData()
	 * Parameters: N/A.
	 * Description: Wrapper function for writing HVAC XML data.
	 */
	public void writeHVACData()
	{
		createHVACDocument();
		createHVACDOMTree();
		printHVACXML(System.getProperty("catalina.home") + "/webapps/HAS/hvac.xml");
	}
	
	/**
	 * Function: public static HVACStatus convertIntToHVACStatus(int value)
	 * Parameters: int value - integer to convert.
	 * Description: Converts an integer to an HVACStatus enum. 
	 */
	public static HVACStatus convertIntToHVACStatus(int value)
	{
		return HVACStatus.class.getEnumConstants()[value];
	}
}
