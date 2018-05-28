package hello.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hello.apimodel.ActivityAPIModel;
import hello.service.bllmodel.ActivityBModel;
import hello.service.interfaces.IActivityService;

@RestController
@RequestMapping("/activity")
public class ActivityController {

	@Autowired 
	private IActivityService activityService;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ActivityAPIModel>> getAllActivities() {
		List<ActivityBModel> list = activityService.getAllActivities();
		List<ActivityAPIModel> resultList = list.parallelStream().map(x -> mapper.map(x, ActivityAPIModel.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{activityId}")
	public ResponseEntity<ActivityAPIModel> getActivityById(@PathVariable("activityId") int activityId){
		ActivityBModel activityB = activityService.getActivityById(activityId);
		if(activityB == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		ActivityAPIModel activity = mapper.map(activityB, ActivityAPIModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(activity);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ActivityAPIModel> addActivity(@RequestBody ActivityAPIModel activity){
		if(activityService.addActivity(mapper.map(activity, ActivityBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(activity);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{activityId}")
	public ResponseEntity<ActivityAPIModel> updateActivity(@PathVariable("activityId") int activityId,@RequestBody ActivityAPIModel activity){
		if(activityService.updateActivity(activityId, mapper.map(activity, ActivityBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(activity);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{activityId}")
	public ResponseEntity<ActivityAPIModel> deleteActivity(@PathVariable("activityId") int activityId){
		if(activityService.deleteActivity(activityId))
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}
