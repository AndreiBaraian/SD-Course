package hello.service.implementation;

import java.util.List;

import hello.service.bllmodel.SubmissionBModel;
import hello.service.interfaces.ISubmissionService;

public class SubmissionService implements ISubmissionService {

	@Override
	public List<SubmissionBModel> getAllAssignments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubmissionBModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addSubmission(int labId, SubmissionBModel submission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSubmission(int id, SubmissionBModel submission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSubmissionById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
