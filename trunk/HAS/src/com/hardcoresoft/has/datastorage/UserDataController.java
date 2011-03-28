package com.hardcoresoft.has.datastorage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
 * Function: public class UserDataController
 * Description: Performs read and writes to the User XML file.
 */
public class UserDataController {
	
	//Member variables
	Document oUserDomRead;
	Document oUserDomWrite;
	UserData oUserData;
	
	//Constructors
	public UserDataController(){
		oUserData = new UserData();
		//Read in the User XML file.
		readUserData();
	}
	
	//Private functions
	/**
	 * Function: private void parseUserXmlFile(String filepath)
	 * Parameters: String filepath: filepath of the XML file.
	 * Description: Parses the XML file to a DOM file, oUserDomRead.
	 */
	private void parseUserXmlFile(String filepath){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			//parse using builder to get DOM representation of the XML file
			oUserDomRead = db.parse(filepath);
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Function: private void parseUserDocument()
	 * Parameters: N/A
	 * Description: Parses the DOM file, oUserDomRead, into the oUserData storage class.
	 */
	private void parseUserDocument(){
		try{
			//get the root elememt
			Element docEle = oUserDomRead.getDocumentElement();		
			
			//get a nodelist of users
			NodeList nl = docEle.getElementsByTagName("tns:user");
			
			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {
					//get the element
					Element el = (Element)nl.item(i);
					//Add to list
					oUserData.addNewUser(getUserNode(el));				
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Function: private UserDataNode getUserNode(Element schdEl)
	 * Parameters: Element schdEl: User element.
	 * Description: Parses the user element and returns a populated UserDataNode.
	 */
	private UserDataNode getUserNode(Element schdEl){
		try
		{
			UserDataNode oUser = new UserDataNode(Utils.getTextValue(schdEl, "tns:firstName"), Utils.getTextValue(schdEl, "tns:lastName"),
					Utils.getTextValue(schdEl, "tns:userName"), Utils.getTextValue(schdEl, "tns:password"),convertIntToUserPermission(Utils.getIntValue(schdEl, "tns:permission")));
			return oUser;
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
	 * using which we create a xml tree in memory as oUserDomWrite.
	 */
	private void createUserDocument() {

		//get an instance of factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
		//get an instance of builder
		DocumentBuilder db = dbf.newDocumentBuilder();

		//create an instance of DOM
		oUserDomWrite = db.newDocument();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Function: private void createUserDOMTree()
	 * Parameters: N/A
	 * Description: The real workhorse which creates the XML structure.
	 */
	private void createUserDOMTree(){
		try{
			//create the root element 
			Element rootEle = oUserDomWrite.createElement("tns:users");
			rootEle.setAttribute("xmlns:tns", "http://www.example.org/user");
			rootEle.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			rootEle.setAttribute("xsi:schemaLocation", "http://www.example.org/user user.xsd ");
			oUserDomWrite.appendChild(rootEle);
			List<UserDataNode> userList = oUserData.getoUsers();
			
			ListIterator<UserDataNode> itr = userList.listIterator();
			
			while(itr.hasNext()){
				UserDataNode oItr = itr.next();
				Element userEle = oUserDomWrite.createElement("tns:user");
				//Create the sub elements.
				//first name
				Element firstNameEle = oUserDomWrite.createElement("tns:firstName");
				Text firstNameText = oUserDomWrite.createTextNode(oItr.getsFirstName());
				firstNameEle.appendChild(firstNameText);
				//last name
				Element lastNameEle = oUserDomWrite.createElement("tns:lastName");
				Text lastNameText = oUserDomWrite.createTextNode(oItr.getsLastName());
				lastNameEle.appendChild(lastNameText);
				//user name
				Element userNameEle = oUserDomWrite.createElement("tns:userName");
				Text userNameText = oUserDomWrite.createTextNode(oItr.getsUserName());
				userNameEle.appendChild(userNameText);
				//password
				Element passwordEle = oUserDomWrite.createElement("tns:password");
				Text passwordText = oUserDomWrite.createTextNode(oItr.getsPassword());
				passwordEle.appendChild(passwordText);
				//permission
				Element permEle = oUserDomWrite.createElement("tns:permission");
				Text permText = oUserDomWrite.createTextNode(Integer.toString(oItr.getoUserPermission().ordinal()));
				permEle.appendChild(permText);
				userEle.appendChild(firstNameEle);
				userEle.appendChild(lastNameEle);
				userEle.appendChild(userNameEle);
				userEle.appendChild(passwordEle);
				userEle.appendChild(permEle);
				rootEle.appendChild(userEle);
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Function: private void printUserXML(String filepath)
	 * Parameters: String filepath: file path of the output XML file.
	 * Description: Writes a new XML based on oUserDomWrite data to filepath. 
	 */
	private void printUserXML(String filepath){

		try
		{
			//print
			OutputFormat format = new OutputFormat(oUserDomWrite);
			format.setIndenting(true);

			//to generate output to console use this serializer
			//XMLSerializer serializer = new XMLSerializer(System.out, format);
			
			//to generate a file output use fileoutputstream instead of system.out
			//Change this directory to something more generic!!
			XMLSerializer serializer = new XMLSerializer(
			new FileOutputStream(new File(filepath)), format);

			serializer.serialize(oUserDomWrite);

		} catch(IOException ie) {
		    ie.printStackTrace();
		}
	}
	
	//Public functions
	/**
	 * Function: public void readUserData()
	 * Parameters: N/A.
	 * Description: Wrapper function for reading in user XML data.
	 */
	public void readUserData()
	{
		parseUserXmlFile(System.getProperty("catalina.home") + "/webapps/HAS/user.xml");
		parseUserDocument();
	}
	
	/**
	 * Function: public void writeUserData()
	 * Parameters: N/A.
	 * Description: Wrapper function for writing user XML data.
	 */
	public void writeUserData()
	{
		createUserDocument();
		createUserDOMTree();
		printUserXML(System.getProperty("catalina.home") + "/webapps/HAS/user.xml");
	}
	
	public UserData getoUserData() {
		return oUserData;
	}

	public void setoUserData(UserData oUserData) {
		this.oUserData = oUserData;
	}

	/**
	 * Function: public static UserPermission convertIntToUserPermission(int value)
	 * Parameters: int value - integer to convert.
	 * Description: Converts an integer to an UserPermission enum. 
	 */
	public static UserPermission convertIntToUserPermission(int value)
	{
		return UserPermission.class.getEnumConstants()[value];
	}

}
