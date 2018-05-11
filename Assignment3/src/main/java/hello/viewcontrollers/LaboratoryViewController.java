package hello.viewcontrollers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LaboratoryViewController {
	
	@RequestMapping("/listLaboratories")
	 public ModelAndView listLaboratories() {
		 ModelAndView mv = new ModelAndView("listLaboratories");
		 return mv;
	 }
	
	@RequestMapping(value = "/modifyLaboratory/{labId}")
	 public ModelAndView modifyLaboratory(@PathVariable("labId") int labId) {
		 ModelAndView mv = new ModelAndView("modifyLaboratoryForm");
		 mv.addObject("labId",labId);
		 return mv;
	 }
	
	@RequestMapping("/addLabView")
	 public ModelAndView addLab() {
		 ModelAndView mv = new ModelAndView("addLab");
		 return mv;
	 }
	
	@RequestMapping("/deleteLabView")
	 public ModelAndView deleteLab() {
		 ModelAndView mv = new ModelAndView("deleteLab");
		 return mv;
	 }
	
	@RequestMapping("/viewLabsStudent")
	 public ModelAndView viewLabs() {
		 ModelAndView mv = new ModelAndView("listLaboratoriesStudents");
		 return mv;
	 }

}
