package hello.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeViewController {
	
	@RequestMapping("listEmployees")
	public ModelAndView listEmployees() {
		ModelAndView mv = new ModelAndView("listEmployees");
		return mv;
	}
	
	@RequestMapping("addEmployeeView")
	public ModelAndView addEmployee() {
		ModelAndView mv = new ModelAndView("addEmployee");
		return mv;
	}
	
	@RequestMapping(value = "/modifyEmployeeFromAdmin/{employeeId}")
	public ModelAndView modifyEmployee(@PathVariable("employeeId") int employeeId) {
		ModelAndView mv = new ModelAndView("modifyExpirationDate");
		mv.addObject("employeeId",employeeId);
		return mv;
	}

}
