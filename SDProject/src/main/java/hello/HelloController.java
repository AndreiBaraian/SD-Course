package hello;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import hello.service.bllmodel.UserBModel;
import hello.service.interfaces.IProfileService;
import hello.service.interfaces.IUserService;
import hello.utilities.FunctionalityFilter;
import hello.utilities.MenuItem;
import hello.utilities.MenuXMLParser;
import hello.utilities.Option;

@Controller
public class HelloController {
	
	private Map<MenuItem, List<Option>> filteredFunctions = null;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IProfileService profileService;

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@RequestMapping("/mainAdminPage")
	public ModelAndView mainPage() {
		ModelAndView mv = new ModelAndView("mainAdminPage");
		return mv;
	}
	
	@RequestMapping("/mainAdminMenu")
	public ModelAndView mainAdminMenu() {
		ModelAndView mv = new ModelAndView("mainAdminMenu");
		return mv;
	}
	
	@RequestMapping("/mainEmployeeMenu")
	public ModelAndView mainEmployeeMenu() {
		ModelAndView mv = new ModelAndView("mainEmployeeMenu");
		return mv;
	}
	
	@RequestMapping("/mainEmployeePage")
	public ModelAndView mainEmployeePage() {
		ModelAndView mv = new ModelAndView("mainEmployeePage");
		return mv;
	}
	
	@RequestMapping("/mainCustomerPage")
	public ModelAndView mainCustomerPage() {
		ModelAndView mv = new ModelAndView("mainCustomerPage");
		return mv;
	}
	
	@RequestMapping("/mainCustomerMenu")
	public ModelAndView mainCustomerMenu() {
		ModelAndView mv = new ModelAndView("mainCustomerMenu");
		return mv;
	}
	
	@RequestMapping("/registerView")
	public ModelAndView registration() {
		ModelAndView mv = new ModelAndView("registration");
		return mv;
	}
	
	@RequestMapping("/signUp")
	public ModelAndView signUp() {
		ModelAndView mv = new ModelAndView("signUp");
		return mv;
	}
	
	/*
	@RequestMapping("/mainMenu")
	public ModelAndView mainMenu() throws ParserConfigurationException, SAXException, IOException {
		ModelAndView mv = new ModelAndView("mainMenu");
		MenuXMLParser parser = new MenuXMLParser();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		UserBModel user = userService.getUserByUsername(username);
		if (filteredFunctions == null) {
			FunctionalityFilter filter = new FunctionalityFilter(parser, user);
			filteredFunctions = filter.filter();
		}
		return mv;
	}
	*/
	
	@RequestMapping("/hello")
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView("hello");
		return mv;
	}
	
}
