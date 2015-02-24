package step5_SeaparateAbstractions;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import common.Employee;
import common.ParseEmployeeXmlException;

public class EmployeeDocumentParser extends XmlDocumentParser {
	
	public static final String EMPLOYEE_TYPE_ATTRIBUTE = "type";
	public static final String EMPLOYEE_AGE_ELEMENT = "Age";
	public static final String EMPLOYEE_ID_ELEMENT = "Id";
	public static final String EMPLOYEE_NAME_ELEMENT = "Name";
	public static final String EMPLOYEE_ELEMENT_NAME = "Employee";

	public List<Employee> parseDocumentForEmployees(Document xmlDocument) 
			throws ParseEmployeeXmlException {
		
		NodeList employeeNodes = retrieveEmployeeNodes(xmlDocument.getDocumentElement());
		List<Employee> employeesFromXml = new ArrayList<Employee>();
		
		for (int i = 0; i < employeeNodes.getLength(); i++) { 
			processEmployeeXml(employeesFromXml, employeeNodes.item(i));
		}
		
		return employeesFromXml;
	}

	protected void processEmployeeXml(List<Employee> employeesFromXml, Node nodeFromXml) 
			throws ParseEmployeeXmlException {

		Employee employeeFromXml = buildEmployeeFromXmlNode(nodeFromXml);
		employeesFromXml.add(employeeFromXml);
	}

	protected NodeList retrieveEmployeeNodes(Element rootElement)
			throws ParseEmployeeXmlException {
		
		NodeList nodesInRoot = rootElement.getElementsByTagName(EMPLOYEE_ELEMENT_NAME);
		
		if (nodesInRoot == null || nodesInRoot.getLength() == 0) {
			throw new ParseEmployeeXmlException("No nodes in root.");
		}
		return nodesInRoot;
	}

	protected Employee buildEmployeeFromXmlNode(Node employeeNode) 
			throws ParseEmployeeXmlException {
		
		Element employeeElement = (Element)employeeNode;
		
		String name = getStringValueByTagName(employeeElement, EMPLOYEE_NAME_ELEMENT);
		int id = getIntValueByTagName(employeeElement, EMPLOYEE_ID_ELEMENT);
		int age = getIntValueByTagName(employeeElement, EMPLOYEE_AGE_ELEMENT);
		String type = employeeElement.getAttribute(EMPLOYEE_TYPE_ATTRIBUTE);

		return new Employee(name,id,age,type);
	}
}
