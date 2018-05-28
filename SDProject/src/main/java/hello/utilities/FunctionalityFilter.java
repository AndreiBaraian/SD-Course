package hello.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXException;

import hello.service.bllmodel.UserBModel;
import hello.service.interfaces.IProfileService;

public class FunctionalityFilter {

	@Autowired
	private IProfileService profileService;
	
	private MenuXMLParser parser;
	private UserBModel user;
	
	public FunctionalityFilter(MenuXMLParser parser, UserBModel user) {
		this.parser = parser;
		this.user = user;
	}
	
	private List<String> getAllowedFunctions() {
		List<String> allowedFunctions = new ArrayList<String>();
		//String[] functions = (new ProfileBLL()).extractActiveObject(user.getProfileName(),null).getAllowedFunctions().split(", ");
		System.out.println("here is the call");
		profileService.getProfileById(3);
		
		String[] functions = profileService.getProfileById(user.getProfile().getId()).getAllowedFunctions().split(", ");
		for(String s : functions) {
			allowedFunctions.add(s);
		}
		return allowedFunctions;
	}
	
	public Map<MenuItem, List<Option>> filter() {
		List<String> allowedFunctions = getAllowedFunctions();
		Map<MenuItem, List<Option>> menuFunctions = parser.getMenuFunctions();
		Map<MenuItem, List<Option>> filteredMenuFunctions = new TreeMap<MenuItem, List<Option>>();
		for(Map.Entry<MenuItem, List<Option>> entry : menuFunctions.entrySet()) {
			List<Option> options = entry.getValue();
			List<Option> allowedOptions = new ArrayList<Option>();
			for(Option o : options) {
				if(allowedFunctions.contains(o.getTitle())) {
					allowedOptions.add(new Option(o.getTitle(), o.getHref(), o.getLeftComponentStyle(), o.getStyle(), o.getRightComponentStyle(), o.getUserTypes()));
				}
			}
			if(allowedOptions.size() != 0) {
				filteredMenuFunctions.put(entry.getKey(), allowedOptions);
			}
		}
		return filteredMenuFunctions;
	}
	
	public static ResponseEntity<?> getUserSpecificFunctions(String userType) throws ParserConfigurationException, SAXException, IOException {
		List<String> listOfOptions = (new MenuXMLParser()).getMenuFunctionsList().stream().filter(e -> {
			return e.getUserTypes().contains(userType);
		}).collect(Collectors.toList()).stream().map(e -> e.getTitle()).collect(Collectors.toList());
		return new ResponseEntity<String>(listOfOptions.toString(), HttpStatus.OK);
	}
	
}
