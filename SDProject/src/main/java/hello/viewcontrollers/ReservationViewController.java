package hello.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReservationViewController {

	@RequestMapping("/addReservationView/{activityId}")
	public ModelAndView addReservation(@PathVariable("activityId") int activityId) {
		ModelAndView mv = new ModelAndView("addReservation");
		mv.addObject("activityId,activityId");
		return mv;
	}
	
	@RequestMapping("/listReservations")
	public ModelAndView listReservations() {
		ModelAndView mv = new ModelAndView("listReservations");
		return mv;
	}
	
	@RequestMapping("/listReservationsCustomer")
	public ModelAndView listReservationsCustomer() {
		ModelAndView mv = new ModelAndView("listReservationsCustomer");
		return mv;
	}
	
}
