package hello.service.interfaces;

import java.util.List;

import hello.service.bllmodel.SubmissionBModel;

public interface ISubmissionService {
	
	List<SubmissionBModel> getAllAssignments();
	SubmissionBModel getById(int id);
	boolean addSubmission(int labId, SubmissionBModel submission);
	boolean updateSubmission(int id,SubmissionBModel submission);
	boolean deleteSubmissionById(int id);

}
