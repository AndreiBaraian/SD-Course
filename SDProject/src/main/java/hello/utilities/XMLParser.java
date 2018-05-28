package hello.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

	public static String VALUE = "value";	
	private Document document;
	
	public XMLParser(String fileName) throws ParserConfigurationException, SAXException, IOException {
		File file = new File(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		this.document = dBuilder.parse(file);
		document.normalize();
	}
	
	public List<String> getNodeValues(String tagName) {
		NodeList nodes = document.getElementsByTagName(tagName).item(0).getChildNodes();
		List<String> nodeValues = new ArrayList<String>();
		for(int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element)node;
				nodeValues.add(getValue(XMLParser.VALUE, element));
			}
		}
		return nodeValues;
	}
	
	private static String getValue(String tagName, Element element) {
		NodeList nodes = element.getElementsByTagName(tagName).item(0).getChildNodes();
		Node node = (Node)nodes.item(0);
		return node.getNodeValue();
	}
	
	public Document getDocument() {
		return document;
	}
	
}
