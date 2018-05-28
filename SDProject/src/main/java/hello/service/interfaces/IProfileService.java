package hello.service.interfaces;

import java.util.List;

import hello.service.bllmodel.ProfileBModel;

public interface IProfileService {
	
	public List<ProfileBModel> getAllProfiles();
	
	public ProfileBModel getProfileById(int id);
	
	public boolean addProfile(ProfileBModel profile);
	
	public boolean updateProfile(int id, ProfileBModel profile);
	
	public boolean deleteProfile(int id);

}
