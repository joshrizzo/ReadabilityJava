package step5_SeaparateAbstractions.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import common.Employee;

public class TestDataFactory {

	protected static Document getTestDoc() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse("employeesTestData.xml");
		}
		catch (ParserConfigurationException | SAXException | IOException exception) {
			// Do nothing.
		}
		return doc;
	}
	
	protected static Node getTestEmployeeNode() {
		return TestDataFactory.getTestDoc().getDocumentElement().getElementsByTagName("Employee").item(0);
	}
	
	protected static List<Employee> getTestEmployeeList() {
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee("Seagul", 3674, 34, "permanent"));
		employeeList.add(new Employee("Robin", 3675, 25, "contract"));
		employeeList.add(new Employee("Crow", 3676, 28, "permanent"));
		return employeeList;
	}
}
