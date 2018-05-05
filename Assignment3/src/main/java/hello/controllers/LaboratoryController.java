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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import hello.apimodels.LaboratoryAPIModel;
import hello.service.bllmodel.LaboratoryBModel;
import hello.service.interfaces.ILaboratoryService;

@RestController
public class LaboratoryController {

	@Autowired
	private ILaboratoryService labService;
	
	@Autowired
	private ModelMapper mapper;
	
	/*
	@RequestMapping(method = GET)
	public List<LaboratoryAPIModel> getAllLaboratories() {
		List<LaboratoryBModel> list = labService.getAllLaboratories();
		List<LaboratoryAPIModel> resultList = list.parallelStream().map(x -> mapper.map(x, LaboratoryAPIModel.class)).collect(Collectors.toList());
		return resultList;
	}
	*/
	
	@RequestMapping(value = "/lab",method = GET)
	public ModelAndView getAllLaboratories() {
		List<LaboratoryBModel> list = labService.getAllLaboratories();
		List<LaboratoryAPIModel> resultList = list.parallelStream().map(x -> mapper.map(x, LaboratoryAPIModel.class)).collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("listLaboratories");
		mv.addObject("laboratories",resultList);
		return mv;
	}
	
	@RequestMapping(method = GET, value = "/lab/{labId}")
	public ResponseEntity<LaboratoryAPIModel> getLabById(@RequestParam int labId) {
		LaboratoryBModel labB = labService.getById(labId);
		if(labB == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		LaboratoryAPIModel lab = mapper.map(labB,LaboratoryAPIModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(lab);
	}
	
	
	@RequestMapping(method = GET, value = "/lab/{keyword}")
	public ResponseEntity<List<LaboratoryAPIModel>> getLabByKeyword(@RequestParam String keyword){
		List<LaboratoryAPIModel> list = labService.getLabsByKeyword(keyword).parallelStream()
				.map(x -> mapper.map(x, LaboratoryAPIModel.class))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	/*
	@RequestMapping(method = POST)
	public ResponseEntity<LaboratoryAPIModel> addLaboratory(@RequestBody LaboratoryAPIModel labAPIModel) {
		if(labService.addLaboratory(mapper.map(labAPIModel, LaboratoryBModel.class)))
			return ResponseEntity.status(HttpStatus.CREATED).body(labAPIModel);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	*/
	/*
	@RequestMapping(value = "/lab",method = RequestMethod.POST)
	public RedirectView addLaboratory(@RequestBody String[] list) {
		RedirectView rv = new RedirectView("/hello");
		System.out.println(list[0]);
		return rv;
	}
	*/
	
	@RequestMapping(value = "/lab",method = POST)
	public RedirectView addLaboratory(@RequestBody LaboratoryAPIModel labAPIModel) {
		RedirectView rv = new RedirectView("hello");
		if(labService.addLaboratory(mapper.map(labAPIModel, LaboratoryBModel.class)))
			System.out.println("ok");
		return rv;
	}
	
	
	/*
	@RequestMapping(value = "/lab",method = POST)
	public RedirectView addLaboratory(HttpServletRequest request) {
		RedirectView rv = new RedirectView("/hello");
		String l = request.getParameter("labNumber");
		System.out.println(l);
		return rv;
	}
	*/
	
	@RequestMapping(value = "/lab",method = PUT)
	public ResponseEntity<LaboratoryAPIModel> updateLaboratory(@RequestParam int labId,@RequestBody LaboratoryAPIModel labAPIModel) {
		if(labService.updateLaboratory(labId,mapper.map(labAPIModel, LaboratoryBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(labAPIModel);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@RequestMapping(method = DELETE, value = "/lab/{labId}")
	public ResponseEntity<LaboratoryAPIModel> deleteLaboratory(@RequestParam int labId) {
		if(labService.deleteLaboratoryById(labId))
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}
