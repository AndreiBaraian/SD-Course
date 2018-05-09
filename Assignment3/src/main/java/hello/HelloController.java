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
	 
	 @RequestMapping("/addStudentView")
	 public ModelAndView addStudent() {
		 ModelAndView mv = new ModelAndView("addStudent");
		 return mv;
	 }
	 
	 @RequestMapping("/addAssignmentView")
	 public ModelAndView addAssignment(HttpServletRequest request) {
		 ModelAndView mv = new ModelAndView("addAssignment");
		 mv.addObject("labId",Integer.parseInt(request.getParameter("labId")));
		 return mv;
	}
	 
	 @RequestMapping("/access-denied")
	 public ModelAndView access_denied() {
		 ModelAndView mv = new ModelAndView("access-denied");
		 return mv;
	 }
	 
	 @RequestMapping("registerView")
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
	 
	 @RequestMapping("/viewLabsStudent")
	 public ModelAndView viewLabsStudent() {
		 ModelAndView mv = new ModelAndView("listLaboratoriesStudents");
		 return mv;
	 }
	 
	 @RequestMapping("/listAssignmentsStudents")
	 public ModelAndView listAssignmentStudents() {
		 ModelAndView mv = new ModelAndView("listAssignmentsStudents");
		 return mv;
	 }

}
