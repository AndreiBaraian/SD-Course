package hello.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AttendanceViewController {
	
	@RequestMapping("/attendance/labsDisplay")
	 public ModelAndView labsDisplay() {
		 ModelAndView mv = new ModelAndView("listAttendanceLabs");
		 return mv;
	 }
	
	@RequestMapping("/addAttendanceView/{labId}")
	 public ModelAndView addAttendance(@PathVariable("labId") int labId) {
		 ModelAndView mv = new ModelAndView("listAttendanceStudents");
		 mv.addObject("labId",labId);
		 return mv;
	 }
	
	@RequestMapping("/viewAttendance")
	 public ModelAndView viewAttendance(@RequestParam("labId") int labId) {
		 ModelAndView mv = new ModelAndView("listAttendances");
		 mv.addObject("labId",labId);
		 return mv;
	 }

}
