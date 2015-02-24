package step4_RefactoredCode;

import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;
import common.Employee;
import common.ParseEmployeeXmlException;


public class DomParserExample {
	
	private String employeeXmlLocation = "employees.xml";

	public static void main(String[] args) {
		
		try {
			new DomParserExample().runExample();
		} 
		catch (ParseEmployeeXmlException exception) {
			exception.printStackTrace();
		}
	}

	public void runExample() throws ParseEmployeeXmlException {
		Document documentFromFile = new XmlDocumentLoader().parseXmlFile(this.employeeXmlLocation);
		List<Employee> employees = new EmployeeDocumentParser().parseDocumentForEmployees(documentFromFile);
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