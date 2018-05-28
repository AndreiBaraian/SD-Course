package hello.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MenuXMLParser extends XMLParser {

private static String menuConstantsPath = (new File("src/main/java/hello/config/menuFunctions.xml")).getAbsolutePath();
	
	public static String OPTION = "option";
	public static String MENU_ITEM = "menuItem";
	public static String TITLE = "title";
	public static String HREF = "href"; 
	public static String STYLE = "style";
	public static String LEFT = "left";
	public static String RIGHT = "right";
	public static String USER_TYPES = "userTypes";

	public MenuXMLParser() throws ParserConfigurationException, SAXException, IOException {
		super(menuConstantsPath);
	}
	
	public Map<MenuItem, List<Option>> getMenuFunctions() {
		Map<MenuItem, List<Option>> menuFunctions = new HashMap<MenuItem, List<Option>>();
		Document document = this.getDocument();
		document.getDocumentElement().normalize();
		NodeList nodes = document.getElementsByTagName(MENU_ITEM);
		for(int i = 0; i < nodes.getLength(); i++) {
			List<Option> options = new ArrayList<Option>();
			Element e = (Element)nodes.item(i);
			MenuItem menuItem = new MenuItem(e.getAttribute(TITLE), e.getAttribute(STYLE), e.getAttribute(LEFT), e.getAttribute(RIGHT));
			if(nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element)nodes.item(i);
				NodeList optionList = element.getElementsByTagName(OPTION);
				for(int j = 0; j < optionList.getLength(); j++) {
					if(optionList.item(j).getNodeType() == Node.ELEMENT_NODE) {
						Element option = (Element)optionList.item(j);
						options.add(new Option(option.getTextContent(), option.getAttribute(HREF), option.getAttribute(LEFT), option.getAttribute(STYLE), option.getAttribute(RIGHT), option.getAttribute(USER_TYPES)));
					}
				}
			}
			menuFunctions.put(menuItem, options);
		}
		return menuFunctions;
	}
	
	public List<Option> getMenuFunctionsList() {
		Map<MenuItem, List<Option>> menuOptions = getMenuFunctions();
		List<Option> menuFunctions = new ArrayList<Option>();
		for(List<Option> l : menuOptions.values()) {
			for(Option o : l) {
				menuFunctions.add(o);
			}
		}
		return menuFunctions;
	}
	
}
