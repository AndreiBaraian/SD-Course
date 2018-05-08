package hello.apimodels;

public class AttendanceAPIModel {
	
	private int id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
