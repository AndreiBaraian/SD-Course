package hello.service.interfaces;

import java.util.List;

import hello.service.bllmodel.SubmissionBModel;

public interface ISubmissionService {
	
	List<SubmissionBModel> getAllAssignments();
	SubmissionBModel getById(int id);
	boolean addSubmission(int assignmentId, int studenId, SubmissionBModel submission);
	public boolean updateSubmission(int id, SubmissionBModel submission);
	boolean deleteSubmissionById(int id);

}
