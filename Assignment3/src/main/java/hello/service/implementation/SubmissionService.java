package hello.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.AssignmentDB;
import hello.dao.dbModel.StudentDB;
import hello.dao.dbModel.SubmissionDB;
import hello.dao.repository.AssignmentDAO;
import hello.dao.repository.StudentDAO;
import hello.dao.repository.SubmissionDAO;
import hello.service.bllmodel.SubmissionBModel;
import hello.service.interfaces.ISubmissionService;

@Service
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
	public List<SubmissionBModel> getAllSubmissions() {
		List<SubmissionDB> list = submissionDAO.findAll().parallelStream().filter(x -> (x.getWasDeleted() == false)).collect(Collectors.toList());
		List<SubmissionBModel> resultList = list.parallelStream().map(x-> modelMapper.map(x, SubmissionBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public SubmissionBModel getById(int id) {
		Optional<SubmissionDB> submissionDB = submissionDAO.findById(id);
		if(submissionDB.isPresent() && (!submissionDB.get().getWasDeleted())){
			return modelMapper.map(submissionDB, SubmissionBModel.class);
		}
		return null;
	}
	
	@Override
	public SubmissionBModel getByAssignmentAndStudent(int assignmentId, int studentId) {
		SubmissionDB submissionDB = submissionDAO.findSubmissionByAssignmentIdAndStudentId(assignmentId, studentId);
		if(submissionDB != null && (!submissionDB.getWasDeleted())){
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
		else if((!submissionDB.getWasDeleted()) && submissionDB.getNumberOfSubmissions() < MAX_NUMBER_SUBMISSIONS) {
			updateSubmission(submissionDB.getId(),submission);
			return true;
		}
		// there was a previous submission, but it was deleted
		else if (submissionDB.getWasDeleted() && submissionDB.getNumberOfSubmissions() < MAX_NUMBER_SUBMISSIONS){
			//SubmissionDB sub = modelMapper.map(submission, SubmissionDB.class);
			//sub.setAssignment(assignmentDB);
			//sub.setStudent(studentDB);
			submissionDB.setGitRepositoryLink(submission.getGitRepositoryLink());
			submissionDB.setRemark(submission.getRemark());
			submissionDB.setNumberOfSubmissions(submissionDB.getNumberOfSubmissions() + 1);
			submissionDB.setWasDeleted(false);
			submissionDAO.save(submissionDB);
			return true;
		}
		else
			return false;
	}

	
	@Override
	public boolean updateSubmission(int id, SubmissionBModel submission) {
		SubmissionDB subDB = submissionDAO.getOne(id);
		if(subDB.getNumberOfSubmissions() < MAX_NUMBER_SUBMISSIONS){
			subDB.setGitRepositoryLink(submission.getGitRepositoryLink());
			subDB.setRemark(submission.getRemark());
			subDB.setNumberOfSubmissions(subDB.getNumberOfSubmissions() + 1);
			submissionDAO.save(subDB);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean gradeSubmission(int id, int grade) {
		Optional<SubmissionDB> subDB = submissionDAO.findById(id);
		if(subDB.isPresent()){
			SubmissionDB sub = subDB.get();
			sub.setGrade(grade);
			submissionDAO.save(sub);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean exists(int id){
		Optional<SubmissionDB> submissionDB = submissionDAO.findById(id);
		if(submissionDB.isPresent()){
			return true;
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
