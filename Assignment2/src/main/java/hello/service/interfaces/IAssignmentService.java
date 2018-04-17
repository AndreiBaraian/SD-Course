package hello.service.interfaces;

import java.util.List;

import hello.service.bllmodel.AssignmentBModel;

public interface IAssignmentService {
	
	List<AssignmentBModel> getAllAssignment();
	AssignmentBModel getById(AssignmentBModel assignment);
	boolean addAssignment(AssignmentBModel assignment);
	boolean updateAssignment(AssignmentBModel assignment);
	boolean deleteAssignmentById(AssignmentBModel assignment);

}
