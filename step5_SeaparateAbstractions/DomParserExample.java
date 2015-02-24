package step5_SeaparateAbstractions;

import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;
import common.Employee;
import common.ParseEmployeeXmlException;


public class DomParserExample {
	
	private String employeeXmlLocation = "employees.xml";
	private XmlDocumentLoader xmlDocLoader;
	private EmployeeDocumentParser employeeDocParser;

	public static void main(String[] args) {
		
		try {
			new DomParserExample(new XmlDocumentLoader(), new EmployeeDocumentParser()).runExample();
		} 
		catch (ParseEmployeeXmlException exception) {
			exception.printStackTrace();
		}
	}
	
	public DomParserExample(XmlDocumentLoader xmlDocLoader,EmployeeDocumentParser employeeDocParser) {
		this.xmlDocLoader = xmlDocLoader;
		this.employeeDocParser = employeeDocParser;
	}

	public void runExample() throws ParseEmployeeXmlException {
		Document documentFromFile = xmlDocLoader.parseXmlFile(this.employeeXmlLocation);
		List<Employee> employees = employeeDocParser.parseDocumentForEmployees(documentFromFile);
		printEmployeesToConsole(employees);
	}
	
	protected void printEmployeesToConsole(List<Employee> employees) {
		
		System.out.println("No of Employees '" + employees.size() + "'.");
		Iterator<Employee> it = employees.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
}