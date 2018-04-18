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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.apimodels.AssignmentAPIModel;
import hello.service.bllmodel.AssignmentBModel;
import hello.service.interfaces.IAssignmentService;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {
	
	@Autowired
	private IAssignmentService assignmentService;
	
	@RequestMapping(method = POST)
	public ResponseEntity<AssignmentAPIModel> addAssignment(@RequestParam int labId,@RequestBody AssignmentAPIModel assignmentAPIModel) {
		if(assignmentService.addAssignment(labId, new ModelMapper().map(assignmentAPIModel,AssignmentBModel.class)))
			return ResponseEntity.status(HttpStatus.CREATED).body(assignmentAPIModel);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@RequestMapping(method = DELETE, value = "/{id}")
	public ResponseEntity<String> deleteAssignment(@RequestParam int id){
		if(assignmentService.deleteAssignmentById(id))
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(method = PUT)
	public ResponseEntity<AssignmentAPIModel> updateAssignment(@RequestParam int assignmentId,@RequestBody AssignmentAPIModel assignmentAPIModel){
		if(assignmentService.updateAssignment(assignmentId, new ModelMapper().map(assignmentAPIModel, AssignmentBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(assignmentAPIModel);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(method = GET, value = "/{id}")
	public ResponseEntity<AssignmentAPIModel> getAssignmentById(@RequestParam int id){
		AssignmentBModel assignmentBModel = assignmentService.getById(id);
		if(assignmentBModel == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		AssignmentAPIModel assignmentAPIModel = new ModelMapper().map(assignmentBModel, AssignmentAPIModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(assignmentAPIModel);
	}
	
	@RequestMapping(method = GET)
	public ResponseEntity<List<AssignmentAPIModel>> getAllAssignments(){
		List<AssignmentAPIModel> assignments = assignmentService.getAllAssignments().parallelStream().map(x -> new ModelMapper().map(x, AssignmentAPIModel.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(assignments);
	}

}
