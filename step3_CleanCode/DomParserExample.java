package step3_CleanCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import common.Employee;
import common.ParseEmployeeXmlException;


public class DomParserExample {																	///NOTE: Methods now "protected" level for testability.
	
	public static void main(String[] args) {													///NOTE: Main() at top of class.
		
		try {
			new DomParserExample().runExample();												///NOTE: This is all on one line now.
		} 
		catch (ParseEmployeeXmlException exception) {											///NOTE: Bubbled up custom exception and handled at last responsible moment.
			exception.printStackTrace();
		}
	}

	public void runExample() throws ParseEmployeeXmlException {									///NOTE: White space after method declaration, or no?
		Document documentFromFile = parseXmlFile();												///NOTE: Renamed methods, so no need for comments.
		List<Employee> employees = parseDocumentForEmployees(documentFromFile);					///NOTE: Passing results instead of using class-level vars.
		printEmployeesToConsole(employees);
	}
	
	protected Document parseXmlFile() throws ParseEmployeeXmlException {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();						///NOTE: Do we like this var name?

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			return db.parse("employees.xml");
		} 																						///NOTE: Should we have done "} catch (..." on one line?
		catch (ParserConfigurationException | SAXException | IOException exception) {			///NOTE: Now uses multi-catch.
			throw new ParseEmployeeXmlException(exception);
		}
	}
	
	protected List<Employee> parseDocumentForEmployees(Document xmlDocument) 
			throws ParseEmployeeXmlException {													///NOTE: Wrapped throws to next line to keep width down.
		
		List<Employee> employeesFromXml = new ArrayList<Employee>();							///NOTE: Return var is separate and declared first.
		
		Element rootElement = xmlDocument.getDocumentElement();									///NOTE: Should we have done these two lines on one line?
		NodeList nodesInRoot = rootElement.getElementsByTagName("Employee");
		
		if (nodesInRoot == null || nodesInRoot.getLength() == 0) {								///NOTE: Guard statement.
			throw new ParseEmployeeXmlException("No nodes in root.");							///NOTE: Custom exception.
		}
		
		for (int i = 0; i < nodesInRoot.getLength(); i++) {										///NOTE: Too bad NodeList is not iterable... 
			Node nodeFromXml = nodesInRoot.item(i);												///NOTE: Should we make this method one line?
			Employee employeeFromXml = getEmployeeFromXmlNode(nodeFromXml);
			employeesFromXml.add(employeeFromXml);												///NOTE: Are these var names confusing?
		}
		
		return employeesFromXml;
	}

	protected Employee getEmployeeFromXmlNode(Node employeeNode)								///NOTE: Do we need jDoc with this kind of name? 
			throws ParseEmployeeXmlException {
		
		Element employeeElement = (Element)employeeNode;										///NOTE: Could have done this cast in each method.  Should we?
		
		String name = getStringValueByTagName(employeeElement, "Name");							///NOTE: Used white space to separate sections.
		int id = getIntValueByTagName(employeeElement, "Id");
		int age = getIntValueByTagName(employeeElement, "Age");
		String type = employeeElement.getAttribute("type");

		return new Employee(name,id,age,type);													///NOTE: Returned on creation.  Like or dislike?
	}

	protected String getStringValueByTagName(Element employeeElement, String nodeTagName) 
			throws ParseEmployeeXmlException {
		
		NodeList nodesInEmployeeElement = employeeElement.getElementsByTagName(nodeTagName);

		if(nodesInEmployeeElement == null || nodesInEmployeeElement.getLength() != 1) {			///NOTE: Another guard statement.  Note the check for exactly one element.
			throw new ParseEmployeeXmlException(
					"There wasn't exactly one " + nodeTagName + " element.");
		}
		
		Element el = (Element)nodesInEmployeeElement.item(0);									///NOTE: Should this be on one line or two?
		return el.getFirstChild().getNodeValue();
	}

	protected int getIntValueByTagName(Element ele, String tagName) 							///NOTE: Is this wrapper handy, or clutter?
			throws NumberFormatException, ParseEmployeeXmlException {
		
		return Integer.parseInt(getStringValueByTagName(ele,tagName));							///NOTE: Would this be less confusing on two lines?
	}
	
	protected void printEmployeesToConsole(List<Employee> employees) {
		
		System.out.println("No of Employees '" + employees.size() + "'.");
		Iterator<Employee> it = employees.iterator();											///NOTE: No longer a generic.
		
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
}