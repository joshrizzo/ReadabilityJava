package step4_RefactoredCode;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import common.ParseEmployeeXmlException;

public abstract class XmlDocumentParser {

	protected String getStringValueByTagName(Element employeeElement, String nodeTagName) 
			throws ParseEmployeeXmlException {
		
		NodeList nodesInEmployeeElement = employeeElement.getElementsByTagName(nodeTagName);

		if(nodesInEmployeeElement == null || nodesInEmployeeElement.getLength() != 1) {
			throw new ParseEmployeeXmlException(
					"There wasn't exactly one " + nodeTagName + " element.");
		}
		
		Element el = (Element)nodesInEmployeeElement.item(0);
		return el.getFirstChild().getNodeValue();
	}

	protected int getIntValueByTagName(Element ele, String tagName)
			throws NumberFormatException, ParseEmployeeXmlException {
		
		return Integer.parseInt(getStringValueByTagName(ele,tagName));
	}
}
