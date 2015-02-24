package step4_RefactoredCode.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import common.Employee;
import common.ParseEmployeeXmlException;

public class EmployeeDocumentParserTests {
	
	private EmployeeDocumentParserTestWrapper target;

	@Before
    public void setUp() {
        this.target = new EmployeeDocumentParserTestWrapper();
    }
	
	@Test
	public void test_parseDocumentForEmployees_getsExpectedNumberOfEmployee() 
			throws ParseEmployeeXmlException {
		Document xmlDocument = TestDataFactory.getTestDoc();
		List<Employee> retval = this.target.parseDocumentForEmployees(xmlDocument);
		assertEquals(3, retval.size());
	}

	@Test
	public void test_getEmployeeFromXmlNode_getsAnExpectedEmployee() 
			throws ParseEmployeeXmlException {
		Node employeeNode = TestDataFactory.getTestEmployeeNode();
		Employee retval = this.target.getEmployeeFromXmlNode(employeeNode);
		assertEquals("Employee Details - Name:Seagull, Type:permanent, Id:3674, Age:34.", 
				retval.toString());
	}
}
