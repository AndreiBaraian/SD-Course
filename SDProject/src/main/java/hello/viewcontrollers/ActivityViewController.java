package hello.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ActivityViewController {

	@RequestMapping("listActivities")
	public ModelAndView listActivities() {
		ModelAndView mv = new ModelAndView("listActivities");
		return mv;
	}
	
	@RequestMapping("addActivityView")
	public ModelAndView addActivityView() {
		ModelAndView mv = new ModelAndView("addActivity");
		return mv;
	}
	
	@RequestMapping(value = "/modifyActivity/{activityId}")
	public ModelAndView modifyActivity(@PathVariable("activityId") int activityId) {
		ModelAndView mv = new ModelAndView("modifyActivity");
		mv.addObject("activityId",activityId);
		return mv;
	}
	
}
