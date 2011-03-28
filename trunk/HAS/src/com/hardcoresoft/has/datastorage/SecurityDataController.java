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
 * Function: public class SecurityDataController
 * Description: Performs read and writes to the Security XML file.
 */
public class SecurityDataController {
	
	//Member variables
	Document oSecurityDomRead;
	Document oSecurityDomWrite;
	SecurityData oSecurityData;
	
	//Constructors
	public SecurityDataController(){
		oSecurityData = new SecurityData();
		//Read in the security XML file.
		readSecurityData();
	}
	
	//Private functions
	/**
	 * Function: private void parseSecurityXmlFile(String filepath)
	 * Parameters: String filepath: filepath of the XML file.
	 * Description: Parses the XML file to a DOM file, oSecurityDomRead.
	 */
	private void parseSecurityXmlFile(String filepath){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			//parse using builder to get DOM representation of the XML file
			oSecurityDomRead = db.parse(filepath);
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Function: private void parseSecurityDocument()
	 * Parameters: N/A
	 * Description: Parses the DOM file, oSecurityDom, into the oSecurityData storage class.
	 */
	private void parseSecurityDocument(){
		try{
			//get the root elememt
			Element docEle = oSecurityDomRead.getDocumentElement();		
				
			oSecurityData.setbConnected(Boolean.parseBoolean(Utils.getTextValue(docEle,"tns:connected")));
			oSecurityData.setsMsgQueueName(Utils.getTextValue(docEle,"tns:msgQueueName"));
			oSecurityData.setsIpAddress(Utils.getTextValue(docEle,"tns:ipAddress"));
			oSecurityData.setnPin(Utils.getIntValue(docEle, "tns:pin"));
			oSecurityData.setnPort(Utils.getIntValue(docEle, "tns:port"));
			oSecurityData.setoStatus(convertIntToSecurityStatus(Utils.getIntValue(docEle, "tns:status")));
			//get a nodelist of schedules
			NodeList nl = docEle.getElementsByTagName("tns:action");
			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {
					//get the element
					Element el = (Element)nl.item(i);
					SecuritySchedule oTempSchedule = oSecurityData.getoSchedule();
					//Add to schedule
					oTempSchedule.addScheduledAction(getScheduleNode(el));
					oSecurityData.setoSchedule(oTempSchedule);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Function: SecurityScheduleNode getScheduleNode(Element schdEl)
	 * Parameters: Element schdEl: schedule element.
	 * Description: Parses the schedule elements and returns a populated SecurityScheduleNode.
	 */
	private SecurityScheduleNode getScheduleNode(Element schdEl){

		DateFormat oDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		try
		{
			Date oParseDate = oDf.parse(Utils.getTextValue(schdEl, "tns:date"));
			SecurityScheduleNode oSchedule = new SecurityScheduleNode(oParseDate,convertIntToSecurityStatus(Utils.getIntValue(schdEl, "tns:mode")));
			return oSchedule;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Function: private void createSecurityDocument()
	 * Parameters: N/A
	 * Description: Using JAXP in implementation independent manner create a document object
	 * using which we create a xml tree in memory as oSecurityDomWrite.
	 */
	private void createSecurityDocument() {

		//get an instance of factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
		//get an instance of builder
		DocumentBuilder db = dbf.newDocumentBuilder();

		//create an instance of DOM
		oSecurityDomWrite = db.newDocument();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Function: private void createSecurityDOMTree()
	 * Parameters: N/A
	 * Description: The real workhorse which creates the XML structure.
	 */
	private void createSecurityDOMTree(){
		try{
			//create the root element 
			Element rootEle = oSecurityDomWrite.createElement("tns:security");
			rootEle.setAttribute("xmlns:tns", "http://www.example.org/security");
			rootEle.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			rootEle.setAttribute("xsi:schemaLocation", "http://www.example.org/security security.xsd ");
			oSecurityDomWrite.appendChild(rootEle);
			//Create the connected element
			Element connectedEle = oSecurityDomWrite.createElement("tns:connected");
			Text connectedText = oSecurityDomWrite.createTextNode(oSecurityData.getbConnected().toString());
			connectedEle.appendChild(connectedText);
			rootEle.appendChild(connectedEle);
			//Create the ipAddress element
			Element ipAddrEle = oSecurityDomWrite.createElement("tns:ipAddress");
			Text ipAddrText = oSecurityDomWrite.createTextNode(oSecurityData.getsIpAddress());
			ipAddrEle.appendChild(ipAddrText);
			rootEle.appendChild(ipAddrEle);
			//Create the port element.
			Element portEle = oSecurityDomWrite.createElement("tns:port");
			Text portText = oSecurityDomWrite.createTextNode(Integer.toString(oSecurityData.getnPort()));
			portEle.appendChild(portText);
			rootEle.appendChild(portEle);
			//Create the msgQueue element.
			Element msgQueueEle = oSecurityDomWrite.createElement("tns:msgQueueName");
			Text msgQueueText = oSecurityDomWrite.createTextNode(oSecurityData.getsMsgQueueName());
			msgQueueEle.appendChild(msgQueueText);
			rootEle.appendChild(msgQueueEle);
			//Create the pin element.
			Element pinEle = oSecurityDomWrite.createElement("tns:pin");
			Text pinText = oSecurityDomWrite.createTextNode(Integer.toString(oSecurityData.getnPin()));
			pinEle.appendChild(pinText);
			rootEle.appendChild(pinEle);
			//Add the status
			Element statusEle = oSecurityDomWrite.createElement("tns:status");
			Text statusText = oSecurityDomWrite.createTextNode(Integer.toString(oSecurityData.getoStatus().ordinal()));
			statusEle.appendChild(statusText);
			rootEle.appendChild(statusEle);
			//Generate the schedule aspect using a helper function
			rootEle.appendChild(createSecurityScheduleElement());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Function: private Element createSecurityScheduleElement()
	 * Parameters: N/A
	 * Description: Returns an Element which is populated with Security schedule data. Based on
	 * the data in oSecurityDomWrite.
	 */
	private Element createSecurityScheduleElement(){
		try{
			SecuritySchedule scheduleData = oSecurityData.getoSchedule();
			List<SecurityScheduleNode> scheduleList = scheduleData.getoSchedule();
			Element schedEle = oSecurityDomWrite.createElement("tns:schedule");
			DateFormat oDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			
			ListIterator<SecurityScheduleNode> itr = scheduleList.listIterator();
			while(itr.hasNext()){
				SecurityScheduleNode oItr = itr.next();
				Element actionEle = oSecurityDomWrite.createElement("tns:action");
				//Create the data sub element.
				Element dateEle = oSecurityDomWrite.createElement("tns:date");
				StringBuilder formattedDate = new StringBuilder( oDf.format(oItr.getDate()));
				Text dateText = oSecurityDomWrite.createTextNode(formattedDate.toString());
				dateEle.appendChild(dateText);
				//Create the desired temp sub element.
				Element modeEle = oSecurityDomWrite.createElement("tns:mode");
				Text modeText = oSecurityDomWrite.createTextNode(Integer.toString(oItr.getSecurityStatus().ordinal()));
				modeEle.appendChild(modeText);
				//Add both date and temp sub elements to the action element.
				actionEle.appendChild(dateEle);
				actionEle.appendChild(modeEle);
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
	 * Function: private void printSecurityXML(String filepath)
	 * Parameters: String filepath: file path of the output XML file.
	 * Description: Writes a new XML based on oSecurityDomWrite data to filepath. 
	 */
	private void printSecurityXML(String filepath){

		try
		{
			//print
			OutputFormat format = new OutputFormat(oSecurityDomWrite);
			format.setIndenting(true);

			//to generate output to console use this serializer
			//XMLSerializer serializer = new XMLSerializer(System.out, format);
			
			//to generate a file output use fileoutputstream instead of system.out
			//Change this directory to something more generic!!
			XMLSerializer serializer = new XMLSerializer(
			new FileOutputStream(new File(filepath)), format);

			serializer.serialize(oSecurityDomWrite);

		} catch(IOException ie) {
		    ie.printStackTrace();
		}
	}
	
	//Public functions
	/**
	 * Function: public void readSecurityData()
	 * Parameters: N/A.
	 * Description: Wrapper function for reading in security XML data.
	 */
	public void readSecurityData()
	{
		parseSecurityXmlFile(System.getProperty("catalina.home") + "/webapps/HAS/security.xml");
		parseSecurityDocument();
	}
	
	/**
	 * Function: public void writeSecurityData()
	 * Parameters: N/A.
	 * Description: Wrapper function for writing Security XML data.
	 */
	public void writeSecurityData()
	{
		createSecurityDocument();
		createSecurityDOMTree();
		printSecurityXML(System.getProperty("catalina.home") + "/webapps/HAS/security.xml");
	}
	
	public SecurityData getoSecurityData() {
		return oSecurityData;
	}

	public void setoSecurityData(SecurityData oSecurityData) {
		this.oSecurityData = oSecurityData;
	}

	/**
	 * Function: public static SecurityMode convertIntToSecurityStatus(int value))
	 * Parameters: int value - integer to convert.
	 * Description: Converts an integer to an SecurityMode enum. 
	 */
	public static SecurityMode convertIntToSecurityStatus(int value)
	{
		return SecurityMode.class.getEnumConstants()[value];
	}

	
	
}
