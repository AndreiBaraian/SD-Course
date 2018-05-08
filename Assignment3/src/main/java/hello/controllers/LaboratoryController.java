package hello.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import hello.apimodels.AssignmentAPIModel;
import hello.apimodels.LaboratoryAPIModel;
import hello.service.bllmodel.LaboratoryBModel;
import hello.service.interfaces.IAssignmentService;
import hello.service.interfaces.ILaboratoryService;

@RestController
@RequestMapping("/lab")
public class LaboratoryController {

	@Autowired
	private ILaboratoryService labService;
	
	@Autowired
	private IAssignmentService assignmentService;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(method = GET)
	public ModelAndView getAllLaboratories() {
		List<LaboratoryBModel> list = labService.getAllLaboratories();
		List<LaboratoryAPIModel> resultList = list.parallelStream().map(x -> mapper.map(x, LaboratoryAPIModel.class)).collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("listLaboratories");
		mv.addObject("laboratories",resultList);
		return mv;
	}
	
	@RequestMapping(method = GET, value = "/{labId}")
	public ModelAndView getLabById(@PathVariable("labId") int labId) {
		ModelAndView mv = new ModelAndView("modifyLaboratoryForm");
		LaboratoryBModel labB = labService.getById(labId);
		if(labB == null)
			System.out.println("NOT OK");
		LaboratoryAPIModel lab = mapper.map(labB,LaboratoryAPIModel.class);
		mv.addObject("laboratory",lab);
		return mv;
	}
	
	/*
	@RequestMapping(method = GET, value = "/{keyword}")
	public ResponseEntity<List<LaboratoryAPIModel>> getLabByKeyword(@RequestParam String keyword){
		List<LaboratoryAPIModel> list = labService.getLabsByKeyword(keyword).parallelStream()
				.map(x -> mapper.map(x, LaboratoryAPIModel.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	*/
	@RequestMapping(method = POST)
	public RedirectView addLaboratory(@RequestBody LaboratoryAPIModel labAPIModel) {
		RedirectView rv = new RedirectView("hello");
		if(labService.addLaboratory(mapper.map(labAPIModel, LaboratoryBModel.class)))
			System.out.println("ok");
		return rv;
	}
	
	@ResponseBody
	@RequestMapping(method = PUT, value="/{labId}")
	public ModelAndView updateLaboratory(@PathVariable("labId") int labId,@RequestBody LaboratoryAPIModel labAPIModel) {
		ModelAndView mv = new ModelAndView("hello");
		if(labService.updateLaboratory(labId,mapper.map(labAPIModel, LaboratoryBModel.class)))
			System.out.println("OK");
		return mv;
	}
	
	@RequestMapping(method = DELETE, value = "/{labId}")
	public RedirectView deleteLaboratory(@PathVariable("labId") int labId) {
		RedirectView rv = new RedirectView("/lab");
		System.out.println("yaaay");
		if(labService.deleteLaboratoryById(labId))
			System.out.println("OK");
		return rv;
	}
	
	@RequestMapping(method = GET,value="/assignmentLabs")
	public ModelAndView getAllLabs() {
		List<LaboratoryBModel> list = labService.getAllLaboratories();
		List<LaboratoryAPIModel> resultList = list.parallelStream().map(x -> mapper.map(x, LaboratoryAPIModel.class)).collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("listLabs");
		mv.addObject("laboratories",resultList);
		return mv;
	}
	
	@RequestMapping(method = GET, value="/assignments/{labId}")
	public ModelAndView getAssignmentsByLab(@PathVariable("labId") int labId){
		ModelAndView mv = new ModelAndView("listAssignments");
		List<AssignmentAPIModel> assignments = assignmentService.getAssignmentsByLab(labId).parallelStream()
												.map(x -> mapper.map(x, AssignmentAPIModel.class))
												.collect(Collectors.toList());
		for(AssignmentAPIModel ass: assignments)
			System.out.println(ass.toString());
		mv.addObject("assignments",assignments);
		mv.addObject("labId",labId);
		return mv;
	}
	
}
