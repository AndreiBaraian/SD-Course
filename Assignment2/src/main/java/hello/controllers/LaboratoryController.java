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

import hello.apimodels.LaboratoryAPIModel;
import hello.service.bllmodel.LaboratoryBModel;
import hello.service.interfaces.ILaboratoryService;

@RestController
@RequestMapping("/lab")
public class LaboratoryController {

	@Autowired
	private ILaboratoryService labService;
	
	@RequestMapping(method = GET)
	public List<LaboratoryAPIModel> getAllLaboratories() {
		List<LaboratoryBModel> list = labService.getAllLaboratories();
		List<LaboratoryAPIModel> resultList = list.parallelStream().map(x -> new ModelMapper().map(x, LaboratoryAPIModel.class)).collect(Collectors.toList());
		return resultList;
	}
	
	@RequestMapping(method = GET, value = "/{labId}")
	public ResponseEntity<LaboratoryAPIModel> getLabById(@RequestParam int labId) {
		LaboratoryBModel labB = labService.getById(labId);
		if(labB == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		LaboratoryAPIModel lab = new ModelMapper().map(labB,LaboratoryAPIModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(lab);
	}
	
	@RequestMapping(method = POST)
	public ResponseEntity<LaboratoryAPIModel> addLaboratory(@RequestBody LaboratoryAPIModel labAPIModel) {
		if(labService.addLaboratory(new ModelMapper().map(labAPIModel, LaboratoryBModel.class)))
			return ResponseEntity.status(HttpStatus.CREATED).body(labAPIModel);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@RequestMapping(method = PUT)
	public ResponseEntity<LaboratoryAPIModel> updateLaboratory(@RequestParam int labId,@RequestBody LaboratoryAPIModel labAPIModel) {
		if(labService.updateLaboratory(labId,new ModelMapper().map(labAPIModel, LaboratoryBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(labAPIModel);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@RequestMapping(method = DELETE, value = "/{labId}")
	public ResponseEntity<LaboratoryAPIModel> deleteLaboratory(@RequestParam int labId) {
		if(labService.deleteLaboratoryById(labId))
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}
