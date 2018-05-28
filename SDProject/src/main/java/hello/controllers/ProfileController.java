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

import hello.apimodel.ProfileAPIModel;
import hello.service.bllmodel.ProfileBModel;
import hello.service.interfaces.IProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private IProfileService profileService;
	
	@Autowired
	private ModelMapper mapper;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProfileAPIModel>> getAllProfiles() {
		List<ProfileBModel> list = profileService.getAllProfiles();
		List<ProfileAPIModel> resultList = list.parallelStream().map(x -> mapper.map(x, ProfileAPIModel.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{profileId}")
	public ResponseEntity<ProfileAPIModel> getProfileById(@PathVariable("profileId") int profileId){
		ProfileBModel profileB = profileService.getProfileById(profileId);
		if(profileB == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		ProfileAPIModel profile = mapper.map(profileB, ProfileAPIModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(profile);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ProfileAPIModel> addProfile(@RequestBody ProfileAPIModel profile){
		if(profileService.addProfile(mapper.map(profile, ProfileBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(profile);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{profileId}")
	public ResponseEntity<ProfileAPIModel> updateProfile(@PathVariable("profileId") int profileId,@RequestBody ProfileAPIModel profile){
		if(profileService.updateProfile(profileId, mapper.map(profile, ProfileBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(profile);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{profileId}")
	public ResponseEntity<ProfileAPIModel> deleteProduct(@PathVariable("profileId") int profileId){
		if(profileService.deleteProfile(profileId))
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}
