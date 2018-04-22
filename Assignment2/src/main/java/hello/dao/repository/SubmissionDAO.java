package hello.dao.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.dao.dbModel.SubmissionDB;

@Repository
@Transactional
public interface SubmissionDAO extends JpaRepository<SubmissionDB,Integer> {
	
	public List<SubmissionDB> getSubmissionByAssignmentId(int assignmentId); 
	
	public SubmissionDB findSubmissionByAssignmentIdAndStudentId(int assignmentId,int studentId);

}
