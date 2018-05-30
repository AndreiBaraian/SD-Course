package hello.viewcontrollers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hello.service.bllmodel.ActivityBModel;
import hello.service.interfaces.IActivityService;
import hello.service.interfaces.ICustomerService;

@Controller
public class CustomerViewController {
	
	@Autowired
	private IActivityService activityService;
	
	@Autowired
	private ICustomerService customerService;

	@RequestMapping("/listCustomerActivities")
	public ModelAndView listCustomerActivities() {
		ModelAndView mv = new ModelAndView("listCustomerActivities");
		return mv;
	}
	
	@RequestMapping("viewInstructor/{activityId}")
	public ModelAndView viewInstructor(@PathVariable("activityId") int activityId) {
		ModelAndView mv = new ModelAndView("viewInstructor");
		ActivityBModel a = activityService.getActivityById(activityId);
		int instructorId = a.getEmployee().getId();
		mv.addObject("instructorId", instructorId);
		return mv;
	}
	
	@RequestMapping("/listBalance")
	public ModelAndView listBalance(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("viewBalance");
		HttpSession session = request.getSession();
		int balance = customerService.getCustomerById(Integer.parseInt((String) session.getAttribute("userId"))).getBalance();
		mv.addObject("balance", balance);
		return mv;
	}
	
	@RequestMapping("/addBalance")
	public ModelAndView addBalance() {
		ModelAndView mv = new ModelAndView("addBalance");
		return mv;
	}
	
	@RequestMapping("/listCustomerProducts")
	public ModelAndView listCustomerProducts() {
		ModelAndView mv = new ModelAndView("listCustomerProducts");
		return mv;
	}
	
	@RequestMapping("/listCustomers")
	public ModelAndView listCustomer() {
		ModelAndView mv = new ModelAndView("listCustomers");
		return mv;
	}
	
	@RequestMapping("/viewRentedEquipments/{customerId}")
	public ModelAndView viewRentedEquipment(@PathVariable("customerId") int customerId) {
		ModelAndView mv = new ModelAndView("listRentedEquipment");
		mv.addObject("customerId",customerId);
		return mv;
	}
	
}
