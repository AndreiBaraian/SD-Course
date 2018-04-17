package hello.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.apimodels.AssignmentAPIModel;
import hello.service.bllmodel.AssignmentBModel;
import hello.service.interfaces.IAssignmentService;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {
	
	@Autowired
	private IAssignmentService assignmentService;
	
	@RequestMapping(method = POST)
	public ResponseEntity<String> addAssignment(@RequestBody AssignmentAPIModel assignmentAPIModel) {
		if(assignmentService.addAssignment(new ModelMapper().map(assignmentAPIModel,AssignmentBModel.class)))
			return ResponseEntity.status(HttpStatus.CREATED).build();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
