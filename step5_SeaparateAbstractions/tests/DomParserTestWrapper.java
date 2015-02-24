package step5_SeaparateAbstractions.tests;

import java.util.List;

import step4_RefactoredCode.DomParserExample;
import step4_RefactoredCode.EmployeeDocumentParser;
import step4_RefactoredCode.XmlDocumentLoader;

import common.Employee;
import common.ParseEmployeeXmlException;


public class DomParserTestWrapper extends DomParserExample {
	
	public int CalledRunExample = 0;
	public int calledPrintEmployeesToConsole = 0;
	
	public DomParserTestWrapper(XmlDocumentLoader xmlDocLoader,
			EmployeeDocumentParser employeeDocParser) {
		super(xmlDocLoader, employeeDocParser);
	}

	public void runExample() throws ParseEmployeeXmlException {
		this.CalledRunExample  += 1;
		super.runExample();
	}
	
	public void printEmployeesToConsole(List<Employee> employees) {
		this.calledPrintEmployeesToConsole += 1;
		super.printEmployeesToConsole(employees);
	}
}