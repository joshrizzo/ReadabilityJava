package step4_RefactoredCode.tests;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import common.Employee;
import common.ParseEmployeeXmlException;

import step4_RefactoredCode.EmployeeDocumentParser;

public class EmployeeDocumentParserTestWrapper extends EmployeeDocumentParser {

	public int calledParseDocumentForEmployees = 0;
	public int calledGetEmployeeFromXmlNode = 0;
	
	public List<Employee> parseDocumentForEmployees(Document xmlDocument) 
			throws ParseEmployeeXmlException {
		this.calledParseDocumentForEmployees += 1;
		return super.parseDocumentForEmployees(xmlDocument);
	}

	public Employee getEmployeeFromXmlNode(Node employeeNode) 
			throws ParseEmployeeXmlException {
		this.calledGetEmployeeFromXmlNode += 1;
		return super.getEmployeeFromXmlNode(employeeNode);
	}
}
