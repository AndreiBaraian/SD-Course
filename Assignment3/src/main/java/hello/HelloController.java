package hello;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {
	 
	 @RequestMapping("/")
	 public ModelAndView index() {
		 ModelAndView mv = new ModelAndView("login");
		 return mv;
	 }
	 
	 @RequestMapping("/mainPage")
	 public ModelAndView mainPage() {
		 ModelAndView mv = new ModelAndView("mainPage");
		 return mv;
	 }
	 
	 @RequestMapping("/mainMenu")
	 public ModelAndView mainMenu() {
		 ModelAndView mv = new ModelAndView("mainMenu");
		 return mv;
	 }
	 
	 @RequestMapping("/hello")
	 public ModelAndView hello() {
		 ModelAndView mv = new ModelAndView("hello");
		 return mv;
	 }
	 
	 @RequestMapping("/addLab")
	 public ModelAndView addLab() {
		 ModelAndView mv = new ModelAndView("addLab");
		 return mv;
	 }
	 
	 @RequestMapping("/addAssignmentView")
	 public ModelAndView addAssignment(HttpServletRequest request) {
		 ModelAndView mv = new ModelAndView("addAssignment");
		 mv.addObject("labId",Integer.parseInt(request.getParameter("labId")));
		 return mv;
	}
	 
	 @RequestMapping("/deleteLabView")
	 public ModelAndView deleteLab() {
		 ModelAndView mv = new ModelAndView("deleteLab");
		 return mv;
	 }
	 
	 @RequestMapping("/access-denied")
	 public ModelAndView access_denied() {
		 ModelAndView mv = new ModelAndView("access-denied");
		 return mv;
	 }

}
