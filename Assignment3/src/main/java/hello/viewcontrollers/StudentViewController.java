package hello.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentViewController {

	@RequestMapping("/listStudents")
	 public ModelAndView listStudents() {
		 ModelAndView mv = new ModelAndView("listStudents");
		 return mv;
	 }
	
	@RequestMapping("/addStudentView")
	 public ModelAndView addStudent() {
		 ModelAndView mv = new ModelAndView("addStudent");
		 return mv;
	 }
	
	@RequestMapping("/modifyStudent/{studentId}")
	 public ModelAndView modifyStudent(@PathVariable("studentId") int studentId) {
		 ModelAndView mv = new ModelAndView("modifyStudentForm");
		 mv.addObject("studentId", studentId);
		 return mv;
	 }
	
	@RequestMapping("/student/lab/assignments/{labId}")
	 public ModelAndView addStudent(@PathVariable("labId") int labId) {
		 ModelAndView mv = new ModelAndView("listAssignmentsStudents");
		 mv.addObject("labId",labId);
		 return mv;
	 }
}
