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
