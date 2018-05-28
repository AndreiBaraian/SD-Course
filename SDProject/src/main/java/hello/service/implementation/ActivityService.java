package hello.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.ActivityDB;
import hello.dao.repository.ActivityDAO;
import hello.service.bllmodel.ActivityBModel;
import hello.service.interfaces.IActivityService;

@Service
public class ActivityService implements IActivityService {

	@Autowired
	private ActivityDAO activityDAO;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<ActivityBModel> getAllActivities() {
		List<ActivityDB> list = activityDAO.findAll();
		List<ActivityBModel> resultList = list.parallelStream().map(x -> mapper.map(x, ActivityBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public ActivityBModel getActivityById(int id) {
		Optional<ActivityDB> activityDB = activityDAO.findById(id);
		if(activityDB.isPresent())
			return mapper.map(activityDB, ActivityBModel.class);
		return null;
	}

	@Override
	public boolean addActivity(ActivityBModel activity) {
		ActivityDB activityDB = mapper.map(activity, ActivityDB.class);
		if(activityDAO.findByName(activity.getName()) != null)
			return false;
		activityDAO.save(activityDB);
		return true;
	}

	@Override
	public boolean updateActivity(int id, ActivityBModel activity) {
		Optional<ActivityDB> activityDB = activityDAO.findById(id);
		if(activityDB.isPresent()) {
			ActivityDB actDB = activityDB.get();
			actDB.setDescription(activity.getDescription());
			actDB.setLocation(activity.getLocation());
			actDB.setMaxPersons(activity.getMaxPersons());
			actDB.setName(activity.getName());
			actDB.setPrice(activity.getPrice());
			activityDAO.save(actDB);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteActivity(int id) {
		Optional<ActivityDB> activityDB = activityDAO.findById(id);
		if(activityDB.isPresent()) {
			activityDAO.delete(activityDB.get());
			return true;
		}
		return false;
	}

}