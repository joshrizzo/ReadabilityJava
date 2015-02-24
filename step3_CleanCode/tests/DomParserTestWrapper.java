package step3_CleanCode.tests;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import step3_CleanCode.DomParserExample;

import common.Employee;
import common.ParseEmployeeXmlException;


public class DomParserTestWrapper extends DomParserExample {
	
	public int CalledRunExample = 0;
	public int calledParseXmlFile = 0;
	public int calledParseDocumentForEmployees = 0;
	public int calledGetEmployeeFromXmlNode = 0;
	public int calledGetStringValueByTagName = 0;
	public int calledGetIntValueByTagName = 0;
	public int calledPrintEmployeesToConsole = 0;
	
	public void runExample() throws ParseEmployeeXmlException {
		this.CalledRunExample  += 1;
		super.runExample();
	}
	
	public Document parseXmlFile() throws ParseEmployeeXmlException {
		this.calledParseXmlFile += 1;
		return super.parseXmlFile();
	}
	
	public List<Employee> parseDocumentForEmployees(Document xmlDocument) 
			throws ParseEmployeeXmlException {
		this.calledParseDocumentForEmployees += 1;
		return super.parseDocumentForEmployees(xmlDocument);
	}

	public Employee buildEmployeeFromXmlNode(Node employeeNode) 
			throws ParseEmployeeXmlException {
		this.calledGetEmployeeFromXmlNode += 1;
		return super.buildEmployeeFromXmlNode(employeeNode);
	}

	public String getStringValueByTagName(Element employeeElement, String nodeTagName) 
			throws ParseEmployeeXmlException {
		this.calledGetStringValueByTagName += 1;
		return super.getStringValueByTagName(employeeElement, nodeTagName);
	}

	public int getIntValueByTagName(Element ele, String tagName)
			throws NumberFormatException, ParseEmployeeXmlException {
		this.calledGetIntValueByTagName += 1;
		return super.getIntValueByTagName(ele, tagName);
	}
	
	public void printEmployeesToConsole(List<Employee> employees) {
		this.calledPrintEmployeesToConsole += 1;
		super.printEmployeesToConsole(employees);
	}
}