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
public class AttendaceDB {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private StudentDB student;
	
	@ManyToOne
	@JoinColumn(name = "lab_id")
	private LaboratoryDB lab;
	
	public AttendaceDB() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StudentDB getStudent() {
		return student;
	}

	public void setStudent(StudentDB student) {
		this.student = student;
	}

	public LaboratoryDB getLab() {
		return lab;
	}

	public void setLab(LaboratoryDB lab) {
		this.lab = lab;
	}
	
}
