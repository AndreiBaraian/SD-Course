package hello.apimodels;

public class SubmissionAPIModel {
	
	private String gitRepositoryLink;
	private String remark;
	private int numberOfSubmissions;
	private int grade;
	
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

}
