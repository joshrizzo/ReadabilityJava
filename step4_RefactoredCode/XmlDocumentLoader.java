package step4_RefactoredCode;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import common.ParseEmployeeXmlException;

public class XmlDocumentLoader {
	
	protected Document parseXmlFile(String documentPath) throws ParseEmployeeXmlException {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			return db.parse(documentPath);
		}
		catch (ParserConfigurationException | SAXException | IOException exception) {
			throw new ParseEmployeeXmlException(exception);
		}
	}
}
