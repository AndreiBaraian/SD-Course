package hello.apimodels;

public class SubmissionAPIModel {

	private int id;
	private String gitRepositoryLink;
	private String remark;
	private int numberOfSubmissions;
	private int grade;
	private StudentAPIModel student;
	
	public SubmissionAPIModel() {}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "SubmissionAPIModel [id=" + id + ", gitRepositoryLink=" + gitRepositoryLink + ", remark=" + remark
				+ ", numberOfSubmissions=" + numberOfSubmissions + ", grade=" + grade + "]";
	}

	public StudentAPIModel getStudent() {
		return student;
	}

	public void setStudent(StudentAPIModel student) {
		this.student = student;
	}

}
