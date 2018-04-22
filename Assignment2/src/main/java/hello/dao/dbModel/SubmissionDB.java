package hello.dao.dbModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "submissions")
public class SubmissionDB {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "repository_link")
	private String gitRepositoryLink;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name = "number_of_submissions")
	private int numberOfSubmissions;
	
	@Column(name = "grade")
	private int grade;
	
	@Column(name = "was_deleted")
	private boolean wasDeleted;
	
	@ManyToOne
	@JoinColumn(name = "assignment_id")
	private AssignmentDB assignment;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private StudentDB student;

	public SubmissionDB() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGitRepositoryLink() {
		return gitRepositoryLink;
	}

	public void setGitRepositoryLink(String gitRepositoryLink) {
		this.gitRepositoryLink = gitRepositoryLink;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getNumberOfSubmissions() {
		return numberOfSubmissions;
	}

	public void setNumberOfSubmissions(int numberOfSubmissions) {
		this.numberOfSubmissions = numberOfSubmissions;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public AssignmentDB getAssignment() {
		return assignment;
	}

	public void setAssignment(AssignmentDB assignment) {
		this.assignment = assignment;
	}

	public boolean getWasDeleted() {
		return wasDeleted;
	}

	public void setWasDeleted(boolean wasDeleted) {
		this.wasDeleted = wasDeleted;
	}

	public StudentDB getStudent() {
		return student;
	}

	public void setStudent(StudentDB student) {
		this.student = student;
	}

}
