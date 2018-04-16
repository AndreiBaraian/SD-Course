package hello.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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

import hello.apimodels.LaboratoryAPIModel;
import hello.service.bllmodel.LaboratoryBModel;
import hello.service.interfaces.ILaboratoryService;

@RestController
@RequestMapping("/labs")
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
	public LaboratoryAPIModel getLabById(@PathVariable Integer labId) {
		LaboratoryAPIModel lab = new ModelMapper().map(labService.getById(labId),LaboratoryAPIModel.class);
		return lab;
	}
	
	@RequestMapping(method = POST)
	public ResponseEntity<String> addLaboratory(@RequestBody LaboratoryAPIModel labAPIModel) {
		if(labService.addLaboratory(new ModelMapper().map(labAPIModel, LaboratoryBModel.class)))
			return ResponseEntity.status(HttpStatus.CREATED).build();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@RequestMapping(method = PUT)
	public ResponseEntity<String> updateLaboratory(@RequestBody LaboratoryAPIModel labAPIModel) {
		if(labService.updateLaboratory(new ModelMapper().map(labAPIModel, LaboratoryBModel.class)))
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@RequestMapping(method = DELETE, value = "/{labId}")
	public ResponseEntity<String> deleteLaboratory(@PathVariable Integer labId) {
		labService.deleteLaboratoryById(labId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
