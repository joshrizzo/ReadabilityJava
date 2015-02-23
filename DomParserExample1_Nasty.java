import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParserExample1_Nasty {
	//No generics
	static List myEmpls = new ArrayList(); 
	static Document dom;

	public static void main(String[] args){
		//parse the xml file and get the dom object
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			//parse using builder to get DOM representation of the XML file
			dom = db.parse("employees.xml");
			//get each employee element and create a Employee object
			Element docEle = dom.getDocumentElement();
			//get a nodelist of <employee> elements
			NodeList nl = docEle.getElementsByTagName("Employee");
			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {
					//get the employee element
					Element el = (Element)nl.item(i);
					//get the Employee object
					String name = ((Element)(el.getElementsByTagName("Name").item(0))).getFirstChild().getNodeValue();
					int id = Integer.parseInt(((Element)(el.getElementsByTagName("Id").item(0))).getFirstChild().getNodeValue());
					int age = Integer.parseInt(((Element)(el.getElementsByTagName("Age").item(0))).getFirstChild().getNodeValue());
					String type = el.getAttribute("type");
					//Create a new Employee with the value read from the xml nodes
					Employee e = new Employee(name,id,age,type);
					//add it to list
					myEmpls.add(e);
				}
			}
			//Iterate through the list and print the data
			System.out.println("No of Employees '" + myEmpls.size() + "'.");
			Iterator it = myEmpls.iterator();
			while(it.hasNext()) {
				System.out.println(it.next().toString());
			}
		}
		catch(ParserConfigurationException pce) { pce.printStackTrace(); }
		catch(SAXException se) { se.printStackTrace(); }
		catch(IOException ioe) { ioe.printStackTrace(); }
	}
}
