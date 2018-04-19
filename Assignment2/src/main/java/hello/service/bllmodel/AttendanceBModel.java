package hello.service.bllmodel;

public class AttendanceBModel {
	
	private LaboratoryBModel laboratory;
	private StudentBModel student;
	
	public AttendanceBModel() {}

	public LaboratoryBModel getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(LaboratoryBModel laboratory) {
		this.laboratory = laboratory;
	}

	public StudentBModel getStudent() {
		return student;
	}

	public void setStudent(StudentBModel student) {
		this.student = student;
	}
	
	

}
