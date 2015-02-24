package step5_SeaparateAbstractions.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import common.Employee;
import common.ParseEmployeeXmlException;

public class DomParserExampleTests {
	
	private DomParserTestWrapper target;
	private XmlDocumentLoaderTestWrapper fakeXmlDocParser;
	private EmployeeDocumentParserTestWrapper fakeEmployeeDocParser;

	@Before
    public void setUp() {
		this.fakeXmlDocParser = new XmlDocumentLoaderTestWrapper();
		this.fakeEmployeeDocParser = new EmployeeDocumentParserTestWrapper();
        this.target = new DomParserTestWrapper(this.fakeXmlDocParser, fakeEmployeeDocParser);
    }
	
	@Test
	public void test_runExample_works() throws ParseEmployeeXmlException {
		this.target.runExample();
		assertEquals(1, this.fakeXmlDocParser.calledParseXmlFile);
		assertEquals(1, this.fakeEmployeeDocParser.calledParseDocumentForEmployees);
		assertEquals(1, this.target.calledPrintEmployeesToConsole);
	}
		
	@Test
	public void test_printEmployeesToConsole() throws ParseEmployeeXmlException {
		// Not testing console output, just seeing if it breaks.
		List<Employee> employees = TestDataFactory.getTestEmployeeList();
		target.printEmployeesToConsole(employees);
	}
}