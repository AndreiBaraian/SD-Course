package hello.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SubmissionViewController {

	@RequestMapping("/addSubmissionView")
	 public ModelAndView addSubmissions(@RequestParam int assignmentId) {
		 ModelAndView mv = new ModelAndView("addSubmission");
		 mv.addObject("assignmentId",assignmentId);
		 return mv;
	 }
	
	@RequestMapping("/viewSubmission")
	public ModelAndView listSubmissions(@RequestParam int assignmentId) {
		 ModelAndView mv = new ModelAndView("viewSubmission");
		 mv.addObject("assignmentId",assignmentId);
		 return mv;
	 }
	
}
