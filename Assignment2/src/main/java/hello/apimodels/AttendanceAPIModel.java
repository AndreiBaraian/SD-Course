package hello.apimodels;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AttendanceAPIModel {
	
	@JsonIgnore
	private LaboratoryAPIModel laboratory;
	private StudentAPIModel student;
	
	public AttendanceAPIModel() {}

	public LaboratoryAPIModel getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(LaboratoryAPIModel laboratory) {
		this.laboratory = laboratory;
	}

	public StudentAPIModel getStudent() {
		return student;
	}

	public void setStudent(StudentAPIModel student) {
		this.student = student;
	}

}
