package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HelloController {
	 
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
	 public ModelAndView mainMenu() {
		 ModelAndView mv = new ModelAndView("mainAdminMenu");
		 return mv;
	 }
	 
	 @RequestMapping("/hello")
	 public ModelAndView hello() {
		 ModelAndView mv = new ModelAndView("hello");
		 return mv;
	 }
	 
	 @RequestMapping("/access-denied")
	 public ModelAndView access_denied() {
		 ModelAndView mv = new ModelAndView("access-denied");
		 return mv;
	 }
	 
	 @RequestMapping("/registerView")
	 public ModelAndView registrationView() {
		 ModelAndView mv = new ModelAndView("registration");
		 return mv;
	 }
	 
	 @RequestMapping("/mainStudentMenu")
	 public ModelAndView mainStudentMenu() {
		 ModelAndView mv = new ModelAndView("mainStudentMenu");
		 return mv;
	 }
	 
	 @RequestMapping("/mainStudentPage")
	 public ModelAndView mainStudentPage() {
		 ModelAndView mv = new ModelAndView("mainStudentPage");
		 return mv;
	 }
	 
	 @RequestMapping(value="/logout")
	public RedirectView logout() {
		RedirectView rv = new RedirectView("/");
		return rv;
	}

}
