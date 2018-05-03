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
@Table(name = "attendances")
public class AttendanceDB {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "lab_id")
	private LaboratoryDB laboratory;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private StudentDB student;
	
	public AttendanceDB() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LaboratoryDB getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(LaboratoryDB laboratory) {
		this.laboratory = laboratory;
	}

	public StudentDB getStudent() {
		return student;
	}

	public void setStudent(StudentDB student) {
		this.student = student;
	}
	
}
