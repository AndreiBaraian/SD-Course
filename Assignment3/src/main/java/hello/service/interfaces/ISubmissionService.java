package hello.service.interfaces;

import java.util.List;

import hello.service.bllmodel.SubmissionBModel;

public interface ISubmissionService {
	
	List<SubmissionBModel> getAllSubmissions();
	SubmissionBModel getById(int id);
	boolean addSubmission(int assignmentId, int studenId, SubmissionBModel submission);
	public boolean updateSubmission(int id, SubmissionBModel submission);
	public boolean gradeSubmission(int id, int grade);
	public boolean exists(int id);
	boolean deleteSubmissionById(int id);
	SubmissionBModel getByAssignmentAndStudent(int assignmentId, int studentId);

}
