package hello.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.ProfileDB;
import hello.dao.repository.ProfileDAO;
import hello.service.bllmodel.ProfileBModel;
import hello.service.interfaces.IProfileService;

@Service
public class ProfileService implements IProfileService {

	@Autowired
	private ProfileDAO profileDAO;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<ProfileBModel> getAllProfiles() {
		List<ProfileDB> list = profileDAO.findAll();
		List<ProfileBModel> resultList = list.parallelStream().map(x -> mapper.map(x, ProfileBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public ProfileBModel getProfileById(int id) {
		System.err.println("eeeeei");
		Optional<ProfileDB> profileDB = profileDAO.findById(id);
		System.out.println("This is profile name " + profileDB.get().getName());
		if(profileDB.isPresent()) {
			return mapper.map(profileDB.get(), ProfileBModel.class);
		}
		return null;
	}

	@Override
	public boolean addProfile(ProfileBModel profile) {
		ProfileDB profileDB = mapper.map(profile, ProfileDB.class);
		if(profileDAO.findByName(profile.getName()) != null)
			return false;
		profileDAO.save(profileDB);
		return true;
	}

	@Override
	public boolean updateProfile(int id, ProfileBModel profile) {
		Optional<ProfileDB> profileDB = profileDAO.findById(id);
		if(profileDB.isPresent()) {
			ProfileDB profDB = profileDB.get();
			profDB.setName(profile.getName());
			profDB.setAllowedFunctions(profile.getAllowedFunctions());
			profileDAO.save(profDB);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteProfile(int id) {
		Optional<ProfileDB> profileDB = profileDAO.findById(id);
		if(profileDB.isPresent()) {
			profileDAO.delete(profileDB.get());
			return true;
		}
		return false;
	}

}
