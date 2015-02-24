package step3_CleanCode.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import common.Employee;
import common.ParseEmployeeXmlException;

public class DomParserExampleTests {
	
	private DomParserTestWrapper target;

	@Before
    public void setUp() {
        this.target = new DomParserTestWrapper();
    }
	
	private Document getTestDoc() {
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
	
	private List<Employee> getTestEmployeeList() {
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee("Seagul", 3674, 34, "permanent"));
		employeeList.add(new Employee("Robin", 3675, 25, "contract"));
		employeeList.add(new Employee("Crow", 3676, 28, "permanent"));
		return employeeList;
	}
	
	private Node getTestEmployeeNode() {
		return this.getTestDoc().getDocumentElement().getElementsByTagName("Employee").item(0);
	}
	
	@Test
	public void test_runExample_works() throws ParseEmployeeXmlException {
		this.target.runExample();
		assertEquals(1, this.target.calledParseXmlFile);
		assertEquals(1, this.target.calledParseDocumentForEmployees);
		assertEquals(1, this.target.calledPrintEmployeesToConsole);
	}
	
	@Test
	public void test_parseXmlFile_returnsAnExpectedDoc() throws ParseEmployeeXmlException {
		Document retval = target.parseXmlFile();
		assertEquals(1, retval.getElementsByTagName("Personnel").getLength());
	}
	
	@Test
	public void test_parseDocumentForEmployees_getsExpectedNumberOfEmployee() 
			throws ParseEmployeeXmlException {
		Document xmlDocument = this.getTestDoc();
		List<Employee> retval = this.target.parseDocumentForEmployees(xmlDocument);
		assertEquals(3, retval.size());
	}

	@Test
	public void test_buildEmployeeFromXmlNode_getsAnExpectedEmployee() 
			throws ParseEmployeeXmlException {
		Node employeeNode = this.getTestEmployeeNode();
		Employee retval = this.target.buildEmployeeFromXmlNode(employeeNode);
		assertEquals("Employee Details - Name:Seagull, Type:permanent, Id:3674, Age:34.", 
				retval.toString());
	}

	@Test
	public void test_getStringValueByTagName_retrunsExpectedValue() 
			throws ParseEmployeeXmlException {
		Element employeeElement = (Element)this.getTestEmployeeNode();
		String retval = this.target.getStringValueByTagName(employeeElement, "Name");
		assertEquals("Seagull", retval);
	}

	@Test
	public void test_getIntValueByTagName_returnsExpectedValue()
			throws NumberFormatException, ParseEmployeeXmlException {
		Element employeeElement = (Element)this.getTestEmployeeNode();
		int retval = this.target.getIntValueByTagName(employeeElement, "Id");
		assertEquals(3674, retval);
	}
	
	@Test
	public void test_printEmployeesToConsole() throws ParseEmployeeXmlException {
		// Not testing console output, just seeing if it breaks.
		List<Employee> employees = this.getTestEmployeeList();
		target.printEmployeesToConsole(employees);
	}
}