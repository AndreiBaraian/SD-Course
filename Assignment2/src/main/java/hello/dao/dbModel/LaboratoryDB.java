package hello.dao.dbModel;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "laboratories")
public class LaboratoryDB {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "labNumber")
	private int labNumber;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "dateOfLab")
	private LocalDateTime date;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "curricula")
	private String curricula;
	
	@Column(name = "description")
	private String description;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy="laboratory")
	private Set<AssignmentDB> assignments;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy="laboratory")
	private Set<AttendanceDB> attendances;

	public LaboratoryDB() {}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLabNumber() {
		return labNumber;
	}
	public void setLabNumber(int labNumber) {
		this.labNumber = labNumber;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCurricula() {
		return curricula;
	}
	public void setCurricula(String curricula) {
		this.curricula = curricula;
	}
	
	public Set<AssignmentDB> getAssignments() {
		return assignments;
	}


	public void setAssignments(Set<AssignmentDB> assignments) {
		this.assignments = assignments;
	}
	
	public Set<AttendanceDB> getAttendances() {
		return attendances;
	}


	public void setAttendances(Set<AttendanceDB> attendances) {
		this.attendances = attendances;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

}
