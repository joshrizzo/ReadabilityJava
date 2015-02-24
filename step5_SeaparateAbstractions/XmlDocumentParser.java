package step5_SeaparateAbstractions;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import common.ParseEmployeeXmlException;

public abstract class XmlDocumentParser {

	protected String getStringValueByTagName(Element element, String nodeTagName) 
			throws ParseEmployeeXmlException {
		
		NodeList nodesInEmployeeElement = element.getElementsByTagName(nodeTagName);

		if(nodesInEmployeeElement == null || nodesInEmployeeElement.getLength() != 1) {
			throw new ParseEmployeeXmlException(
					"There wasn't exactly one " + nodeTagName + " element.");
		}
		
		Element el = (Element)nodesInEmployeeElement.item(0);
		return el.getFirstChild().getNodeValue();
	}

	protected int getIntValueByTagName(Element ele, String tagName)
			throws ParseEmployeeXmlException {
		
		try {
			return Integer.parseInt(getStringValueByTagName(ele,tagName));
		} 
		catch (NumberFormatException exception) {
			return -1;
		}
	}
}
