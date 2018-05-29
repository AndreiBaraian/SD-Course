package hello.service.interfaces;

import java.util.List;

import hello.service.bllmodel.ActivityBModel;

public interface IActivityService {
	
	public List<ActivityBModel> getAllActivities();
	
	public ActivityBModel getActivityById(int id);
	
	public boolean updateActivity(int id, ActivityBModel activity);
	
	public boolean deleteActivity(int id);

	boolean addActivity(int employeeId, ActivityBModel activity);

}
