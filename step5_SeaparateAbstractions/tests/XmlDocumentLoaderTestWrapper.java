package step5_SeaparateAbstractions.tests;

import org.w3c.dom.Document;

import step4_RefactoredCode.XmlDocumentLoader;

import common.ParseEmployeeXmlException;

public class XmlDocumentLoaderTestWrapper extends XmlDocumentLoader {

	public int calledParseXmlFile = 0;
	
	public Document parseXmlFile(String documentPath) throws ParseEmployeeXmlException {
		this.calledParseXmlFile += 1;
		return super.parseXmlFile(documentPath);
	}
}
