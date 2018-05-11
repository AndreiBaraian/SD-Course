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
	public ResponseEntity<List<LaboratoryAPIModel>> getAllLaboratories() {
		List<LaboratoryBModel> list = labService.getAllLaboratories();
		List<LaboratoryAPIModel> resultList = list.parallelStream().map(x -> mapper.map(x, LaboratoryAPIModel.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
	
	@RequestMapping(method = GET, value = "/{labId}")
	public ResponseEntity<LaboratoryAPIModel> getLabById(@PathVariable("labId") int labId) {
		LaboratoryBModel labB = labService.getById(labId);
		if(labB == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		LaboratoryAPIModel lab = mapper.map(labB,LaboratoryAPIModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(lab);
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
	public ResponseEntity<LaboratoryAPIModel> addLaboratory(@RequestBody LaboratoryAPIModel labAPIModel) {
		if(labService.addLaboratory(mapper.map(labAPIModel, LaboratoryBModel.class)))
			return ResponseEntity.status(HttpStatus.CREATED).body(labAPIModel);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	@RequestMapping(method = PUT, value = "/{labId}")
	public ResponseEntity<LaboratoryAPIModel> updateLaboratory(@PathVariable("labId") int labId,@RequestBody LaboratoryAPIModel labAPIModel) {
		if(labService.updateLaboratory(labId,mapper.map(labAPIModel, LaboratoryBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(labAPIModel);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@RequestMapping(method = DELETE, value = "/{labId}")
	public ResponseEntity<LaboratoryAPIModel> deleteLaboratory(@PathVariable("labId") int labId) {
		if(labService.deleteLaboratoryById(labId))
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(method = GET, value="/{labId}/assignments")
	public ResponseEntity<List<AssignmentAPIModel>> getAssignmentsByLab(@PathVariable("labId") int labId){
		List<AssignmentAPIModel> assignments = assignmentService.getAssignmentsByLab(labId).parallelStream()
												.map(x -> mapper.map(x, AssignmentAPIModel.class))
												.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(assignments); 
	}
	
}
