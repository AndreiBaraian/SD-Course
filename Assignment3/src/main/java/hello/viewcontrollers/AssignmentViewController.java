package hello.viewcontrollers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AssignmentViewController {

	@RequestMapping("/listLabsAssignments")
	 public ModelAndView listLabsAssignments() {
		 ModelAndView mv = new ModelAndView("listLabs");
		 return mv;
	 }
	
	@RequestMapping("/lab/assignments/{labId}")
	 public ModelAndView labAssignments(@PathVariable("labId") int labId) {
		 ModelAndView mv = new ModelAndView("listAssignments");
		 mv.addObject("labId",labId);
		 return mv;
	 }
	
	@RequestMapping("/addAssignmentView")
	 public ModelAndView addAssignment(HttpServletRequest request) {
		 ModelAndView mv = new ModelAndView("addAssignment");
		 mv.addObject("labId",Integer.parseInt(request.getParameter("labId")));
		 return mv;
	}
	
	@RequestMapping(value = "/modifyAssignment/{assignmentId}")
	 public ModelAndView modifyLaboratory(@PathVariable("assignmentId") int assignmentId) {
		 ModelAndView mv = new ModelAndView("modifyAssignmentForm");
		 mv.addObject("assignmentId",assignmentId);
		 return mv;
	 }
	
}
