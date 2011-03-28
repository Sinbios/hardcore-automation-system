package com.hardcoresoft.has.datastorage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * Function: public class LightingDataController
 * Description: Performs read and writes to the Lighting XML file.
 */
public class LightingDataController {
	
	//Member variables
	Document oLightingDomRead;
	Document oLightingDomWrite;
	LightingData oLightingData;
	
	

	//Constructors
	public LightingDataController(){
		oLightingData = new LightingData();
		//Read in the lighting XML file.
		readLightingData();
	}
	
	//Private functions
	
	/**
	 * Function: private void parseLightingXmlFile(String filepath)
	 * Parameters: String filepath: filepath of the XML file.
	 * Description: Parses the XML file to a DOM file, oLightingDomRead.
	 */
	private void parseLightingXmlFile(String filepath){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			//parse using builder to get DOM representation of the XML file
			oLightingDomRead = db.parse(filepath);
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Function: private void parseLightingDocument()
	 * Parameters: N/A
	 * Description: Parses the DOM file, oLightingDomRead, into the oLightingData storage class.
	 */
	private void parseLightingDocument(){
		try{
			//get the root elememt
			Element docEle = oLightingDomRead.getDocumentElement();		
				
			oLightingData.setbConnected(Boolean.parseBoolean(Utils.getTextValue(docEle,"tns:connected")));
			oLightingData.setsMsgQueueName(Utils.getTextValue(docEle,"tns:msgQueueName"));
			oLightingData.setsIpAddress(Utils.getTextValue(docEle,"tns:ipAddress"));
			oLightingData.setnPort(Utils.getIntValue(docEle, "tns:port"));
			oLightingData.setnBrightness(Utils.getIntValue(docEle, "tns:brightness"));
			oLightingData.setnColourTemp(Utils.getIntValue(docEle,"tns:colourTemp"));
			oLightingData.setbOperationalStatus(Boolean.parseBoolean(Utils.getTextValue(docEle, "tns:status")));
			
			//get a nodelist of schedules
			NodeList nl = docEle.getElementsByTagName("tns:action");
			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {
					//get the element
					Element el = (Element)nl.item(i);
					LightingSchedule oTempSchedule = oLightingData.getoSchedule();
					//Add to schedule
					oTempSchedule.addScheduledAction(getScheduleNode(el));
					oLightingData.setoSchedule(oTempSchedule);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Function: LightingScheduleNode getScheduleNode(Element schdEl)
	 * Parameters: Element schdEl: schedule element.
	 * Description: Parses the schedule elements and returns a populated LightingScheduleNode.
	 */
	private LightingScheduleNode getScheduleNode(Element schdEl){

		DateFormat oDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		try
		{
			Date oParseDate = oDf.parse(Utils.getTextValue(schdEl, "tns:date"));
			LightingScheduleNode oSchedule = new LightingScheduleNode(oParseDate,Boolean.parseBoolean(Utils.getTextValue(schdEl, "tns:status")),Utils.getIntValue(schdEl, "tns:brightness"), Utils.getIntValue(schdEl, "tns:colourTemp"));
			return oSchedule;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * Function: private void createLightingDocument()
	 * Parameters: N/A
	 * Description: Using JAXP in implementation independent manner create a document object
	 * using which we create a xml tree in memory as oLightingDomWrite.
	 */
	private void createLightingDocument() {

		//get an instance of factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
		//get an instance of builder
		DocumentBuilder db = dbf.newDocumentBuilder();

		//create an instance of DOM
		oLightingDomWrite = db.newDocument();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Function: private void createLightingDOMTree()
	 * Parameters: N/A
	 * Description: The real workhorse which creates the XML structure.
	 */
	private void createLightingDOMTree(){
		try{
			//create the root element 
			Element rootEle = oLightingDomWrite.createElement("tns:lighting");
			rootEle.setAttribute("xmlns:tns", "http://www.example.org/lighting");
			rootEle.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			rootEle.setAttribute("xsi:schemaLocation", "http://www.example.org/lighting lighting.xsd ");
			oLightingDomWrite.appendChild(rootEle);
			//Create the connected element
			Element connectedEle = oLightingDomWrite.createElement("tns:connected");
			Text connectedText = oLightingDomWrite.createTextNode(oLightingData.getbConnected().toString());
			connectedEle.appendChild(connectedText);
			rootEle.appendChild(connectedEle);
			//Create the ipAddress element
			Element ipAddrEle = oLightingDomWrite.createElement("tns:ipAddress");
			Text ipAddrText = oLightingDomWrite.createTextNode(oLightingData.getsIpAddress());
			ipAddrEle.appendChild(ipAddrText);
			rootEle.appendChild(ipAddrEle);
			//Create the port element.
			Element portEle = oLightingDomWrite.createElement("tns:port");
			Text portText = oLightingDomWrite.createTextNode(Integer.toString(oLightingData.getnPort()));
			portEle.appendChild(portText);
			rootEle.appendChild(portEle);
			//Create the msgQueue element.
			Element msgQueueEle = oLightingDomWrite.createElement("tns:msgQueueName");
			Text msgQueueText = oLightingDomWrite.createTextNode(oLightingData.getsMsgQueueName());
			msgQueueEle.appendChild(msgQueueText);
			rootEle.appendChild(msgQueueEle);
			//Create the brightness element.
			Element brightnessEle = oLightingDomWrite.createElement("tns:brightness");
			Text brightnessText = oLightingDomWrite.createTextNode(Integer.toString(oLightingData.getnBrightness()));
			brightnessEle.appendChild(brightnessText);
			rootEle.appendChild(brightnessEle);
			//Create the colour temperature element.
			Element colourTempEle = oLightingDomWrite.createElement("tns:colourTemp");
			Text colourTempText = oLightingDomWrite.createTextNode(Integer.toString(oLightingData.getnColourTemp()));
			colourTempEle.appendChild(colourTempText);
			rootEle.appendChild(colourTempEle);
			//Add the status
			Element statusEle = oLightingDomWrite.createElement("tns:status");
			Text statusText = oLightingDomWrite.createTextNode(Boolean.toString(oLightingData.getbOperationalStatus()));
			statusEle.appendChild(statusText);
			rootEle.appendChild(statusEle);
			//Generate the schedule aspect using a helper function
			rootEle.appendChild(createLightingScheduleElement());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Function: private Element createLightingScheduleElement()
	 * Parameters: N/A
	 * Description: Returns an Element which is populated with Lighting schedule data. Based on
	 * the data in oLightingDomWrite.
	 */
	private Element createLightingScheduleElement(){
		try{
			LightingSchedule scheduleData = oLightingData.getoSchedule();
			List<LightingScheduleNode> scheduleList = scheduleData.getoSchedule();
			Element schedEle = oLightingDomWrite.createElement("tns:schedule");
			DateFormat oDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			
			ListIterator<LightingScheduleNode> itr = scheduleList.listIterator();
			while(itr.hasNext()){
				LightingScheduleNode oItr = itr.next();
				Element actionEle = oLightingDomWrite.createElement("tns:action");
				//Create the date sub element.
				Element dateEle = oLightingDomWrite.createElement("tns:date");
				StringBuilder formattedDate = new StringBuilder( oDf.format(oItr.getDate()));
				Text dateText = oLightingDomWrite.createTextNode(formattedDate.toString());
				dateEle.appendChild(dateText);
				//Create the status sub element.
				Element statusEle = oLightingDomWrite.createElement("tns:status");
				Text statusText = oLightingDomWrite.createTextNode(Boolean.toString(oItr.getbStatus()));
				statusEle.appendChild(statusText);
				//Create the brightness sub element.
				Element brightnessEle = oLightingDomWrite.createElement("tns:brightness");
				Text brightnessText = oLightingDomWrite.createTextNode(Integer.toString(oItr.getnBrightness()));
				brightnessEle.appendChild(brightnessText);
				//Create the colour temp sub element.
				Element colourTempEle = oLightingDomWrite.createElement("tns:colourTemp");
				Text colourTempText = oLightingDomWrite.createTextNode(Integer.toString(oItr.getnColourTemp()));
				colourTempEle.appendChild(colourTempText);
				//Add both date and temp sub elements to the action element.
				actionEle.appendChild(dateEle);
				actionEle.appendChild(statusEle);
				actionEle.appendChild(brightnessEle);
				actionEle.appendChild(colourTempEle);
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
	 * Function: private void printLightingXML(String filepath)
	 * Parameters: String filepath: file path of the output XML file.
	 * Description: Writes a new XML based on oLightingDomWrite data to filepath. 
	 */
	private void printLightingXML(String filepath){

		try
		{
			//print
			OutputFormat format = new OutputFormat(oLightingDomWrite);
			format.setIndenting(true);

			//to generate output to console use this serializer
			//XMLSerializer serializer = new XMLSerializer(System.out, format);
			
			//to generate a file output use fileoutputstream instead of system.out
			//Change this directory to something more generic!!
			XMLSerializer serializer = new XMLSerializer(
			new FileOutputStream(new File(filepath)), format);

			serializer.serialize(oLightingDomWrite);

		} catch(IOException ie) {
		    ie.printStackTrace();
		}
	}
	
	//Public functions
	/**
	 * Function: public void readLightingData()
	 * Parameters: N/A.
	 * Description: Wrapper function for reading in Lighting XML data.
	 */
	public void readLightingData()
	{
		parseLightingXmlFile(System.getProperty("catalina.home") + "/webapps/HAS/lighting.xml");
		parseLightingDocument();
	}
	
	/**
	 * Function: public void writeLightingData()
	 * Parameters: N/A.
	 * Description: Wrapper function for writing Lighting XML data.
	 */
	public void writeLightingData()
	{
		createLightingDocument();
		createLightingDOMTree();
		printLightingXML(System.getProperty("catalina.home") + "/webapps/HAS/lighting.xml");
	}
	
	public LightingData getoLightingData() {
		return oLightingData;
	}

	public void setoLightingData(LightingData oLightingData) {
		this.oLightingData = oLightingData;
	}

}
