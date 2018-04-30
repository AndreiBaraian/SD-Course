package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {
	 
	 @RequestMapping("/")
	 public ModelAndView index() {
		 ModelAndView mv = new ModelAndView("index");
		 return mv;
	 }
	 
	 @RequestMapping("/secure/mainPage")
	 public ModelAndView mainPage() {
		 ModelAndView mv = new ModelAndView("mainPage");
		 return mv;
	 }

}
