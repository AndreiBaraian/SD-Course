package hello.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import hello.dao.dbModel.AssignmentDB;
import hello.dao.dbModel.StudentDB;
import hello.dao.dbModel.SubmissionDB;
import hello.dao.repository.AssignmentDAO;
import hello.dao.repository.StudentDAO;
import hello.dao.repository.SubmissionDAO;
import hello.service.bllmodel.SubmissionBModel;
import hello.service.interfaces.ISubmissionService;

public class SubmissionService implements ISubmissionService {
	
	private static final int MAX_NUMBER_SUBMISSIONS = 3; 
	
	@Autowired
	private SubmissionDAO submissionDAO;
	
	@Autowired
	private AssignmentDAO assignmentDAO;
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<SubmissionBModel> getAllAssignments() {
		List<SubmissionDB> list = submissionDAO.findAll();
		List<SubmissionBModel> resultList = list.parallelStream().map(x-> modelMapper.map(x, SubmissionBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public SubmissionBModel getById(int id) {
		Optional<SubmissionDB> submissionDB = submissionDAO.findById(id);
		if(submissionDB.isPresent()){
			return modelMapper.map(submissionDB, SubmissionBModel.class);
		}
		return null;
	}

	@Override
	public boolean addSubmission(int assignmentId, int studentId, SubmissionBModel submission) {
		AssignmentDB assignmentDB = assignmentDAO.getOne(assignmentId);
		StudentDB studentDB = studentDAO.getOne(studentId);
		LocalDateTime currentDate = LocalDateTime.now();
		if(currentDate.isAfter(assignmentDB.getDeadline()))
			return false;
		SubmissionDB submissionDB = null;
		submissionDB = submissionDAO.findSubmissionByAssignmentIdAndStudentId(assignmentId, studentId);
		// there was no previous submission for that assignment
		if(submissionDB == null){
			SubmissionDB sub = modelMapper.map(submission, SubmissionDB.class);
			sub.setAssignment(assignmentDB);
			sub.setStudent(studentDB);
			sub.setNumberOfSubmissions(1);
			submissionDAO.save(sub);
			return true;
		}
		// there was a previous submission, but it was deleted
		else if (submissionDB.getWasDeleted() && submissionDB.getNumberOfSubmissions() <= MAX_NUMBER_SUBMISSIONS){
			SubmissionDB sub = modelMapper.map(submission, SubmissionDB.class);
			sub.setAssignment(assignmentDB);
			sub.setStudent(studentDB);
			sub.setNumberOfSubmissions(submissionDB.getNumberOfSubmissions() + 1);
			submissionDAO.save(sub);
			return true;
		}
		else
			return false;
	}

	
	@Override
	public boolean updateSubmission(int id, SubmissionBModel submission) {
		Optional<SubmissionDB> submissionDB = submissionDAO.findById(id);
		if(submissionDB.isPresent()){
			SubmissionDB subDB = submissionDB.get();
			if(subDB.getNumberOfSubmissions() <= MAX_NUMBER_SUBMISSIONS){
				subDB.setGitRepositoryLink(submission.getGitRepositoryLink());
				subDB.setRemark(submission.getRemark());
				subDB.setNumberOfSubmissions(subDB.getNumberOfSubmissions() + 1);
				submissionDAO.save(subDB);
				return true;
			}
			return false;
		}
		return false;
	}
	

	
	@Override
	public boolean deleteSubmissionById(int id) {
		Optional<SubmissionDB> submissionDB = submissionDAO.findById(id);
		if(submissionDB.isPresent()){
			SubmissionDB subDB = submissionDB.get();
			subDB.setWasDeleted(true);
			submissionDAO.save(subDB);
			return true;
		}
		return false;
	}
	

}
