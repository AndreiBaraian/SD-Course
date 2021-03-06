package hello.service.interfaces;

import java.util.HashMap;
import java.util.List;

import hello.service.bllmodel.AssignmentBModel;

public interface IAssignmentService {
	
	List<AssignmentBModel> getAllAssignments();
	List<AssignmentBModel> getAssignmentsByLab(int labId);
	public HashMap<String,Integer> getGradesForAssignment(int assignmentId);
	AssignmentBModel getById(int id);
	boolean addAssignment(int labId, AssignmentBModel assignment);
	boolean updateAssignment(int id,AssignmentBModel assignment);
	boolean deleteAssignmentById(int id);

}
