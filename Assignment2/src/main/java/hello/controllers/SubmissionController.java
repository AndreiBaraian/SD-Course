package hello.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.apimodels.SubmissionAPIModel;
import hello.service.interfaces.ISubmissionService;

@RestController
@RequestMapping("/lab")
public class SubmissionController {

	@Autowired
	private ISubmissionService subService;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(method = POST)
	public ResponseEntity<SubmissionAPIModel> addSubmission(@RequestParam int assignmentId, @RequestParam int studentId,@RequestBody SubmissionAPIModel submission){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
}
