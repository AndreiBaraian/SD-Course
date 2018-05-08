package hello.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import hello.apimodels.AssignmentAPIModel;
import hello.service.bllmodel.AssignmentBModel;
import hello.service.interfaces.IAssignmentService;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {
	
	@Autowired
	private IAssignmentService assignmentService;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(method = POST,value="/{labId}")
	public ModelAndView addAssignment(@PathVariable("labId") int labId,@RequestBody AssignmentAPIModel assignmentAPIModel) {
		ModelAndView mv = new ModelAndView("hello");
		System.out.println(assignmentAPIModel.toString());
		if(assignmentService.addAssignment(labId, mapper.map(assignmentAPIModel,AssignmentBModel.class)))
			System.out.println("is ok");
		return mv;
	}
	
	@RequestMapping(method = DELETE, value = "/{id}")
	public RedirectView deleteAssignment(@PathVariable("id") int id){
		RedirectView rv = new RedirectView("hello");
		if(assignmentService.deleteAssignmentById(id))
			System.out.println("is ok");
		return rv;
	}
	
	@RequestMapping(method = PUT, value="/{assignmentId}")
	public ResponseEntity<AssignmentAPIModel> updateAssignment(@PathVariable("assignmentId") int assignmentId,@RequestBody AssignmentAPIModel assignmentAPIModel){
		if(assignmentService.updateAssignment(assignmentId, mapper.map(assignmentAPIModel, AssignmentBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(assignmentAPIModel);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	
	@RequestMapping(method = GET, value = "/{id}")
	public ModelAndView getAssignmentById(@PathVariable("id") int id){
		ModelAndView mv = new ModelAndView("modifyAssignmentForm");
		AssignmentBModel assignmentBModel = assignmentService.getById(id);
		if(assignmentBModel == null)
			System.out.println("not ok");;
		AssignmentAPIModel assignmentAPIModel = mapper.map(assignmentBModel, AssignmentAPIModel.class);
		mv.addObject("assignment",assignmentAPIModel);
		return mv;
	}
	
	
	
	/*
	@RequestMapping(method = GET, value = "/{assignmentId}")
	public ResponseEntity<HashMap<String,Integer>> getGrades(@RequestParam int assignmentId){
		HashMap<String,Integer> resultList = assignmentService.getGradesForAssignment(assignmentId);
		if(resultList != null)
			return ResponseEntity.status(HttpStatus.OK).body(resultList);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	*/
	
	
	@RequestMapping(method = GET)
	public ModelAndView getAllAssignments(){
		ModelAndView mv = new ModelAndView("listAssignments");
		List<AssignmentAPIModel> assignments = assignmentService.getAllAssignments().parallelStream().map(x -> mapper.map(x, AssignmentAPIModel.class)).collect(Collectors.toList());
		mv.addObject("assignments",assignments);
		return mv;
	}

}
