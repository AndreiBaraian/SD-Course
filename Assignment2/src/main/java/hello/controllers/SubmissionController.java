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

import hello.apimodels.SubmissionAPIModel;
import hello.service.bllmodel.SubmissionBModel;
import hello.service.interfaces.ISubmissionService;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

	@Autowired
	private ISubmissionService subService;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(method = GET)
	public ResponseEntity<List<SubmissionAPIModel>> getAllSubmission(){
		List<SubmissionBModel> list = subService.getAllSubmissions();
		List<SubmissionAPIModel> resultList = list.parallelStream().map(x -> mapper.map(x, SubmissionAPIModel.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
	
	@RequestMapping(method = GET, value = "/{submissionId")
	public ResponseEntity<SubmissionAPIModel> getSubmissionById(@RequestParam int subId){
		SubmissionAPIModel submission = mapper.map(subService.getById(subId), SubmissionAPIModel.class); 
		if(submission != null)
			return ResponseEntity.status(HttpStatus.OK).body(submission);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(method = POST)
	public ResponseEntity<SubmissionAPIModel> addSubmission(@RequestParam int assignmentId, @RequestParam int studentId,@RequestBody SubmissionAPIModel submission){
		if(subService.addSubmission(assignmentId, studentId, mapper.map(submission, SubmissionBModel.class)))
			return ResponseEntity.status(HttpStatus.CREATED).body(submission);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@RequestMapping(method = PUT)
	public ResponseEntity<?> updateSubmission(@RequestParam int subId, @RequestBody SubmissionAPIModel submission){
		if(!subService.exists(subId))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		if(subService.updateSubmission(subId, mapper.map(submission, SubmissionBModel.class)))
			return ResponseEntity.status(HttpStatus.CREATED).body(submission);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The maximum number of submissions has been reached!");
	}
	
	@RequestMapping(method = DELETE)
	public ResponseEntity<SubmissionAPIModel> deleteSubmission(@RequestParam int id){
		if(subService.deleteSubmissionById(id))
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}
