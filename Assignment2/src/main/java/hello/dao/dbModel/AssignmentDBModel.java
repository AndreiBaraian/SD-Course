package hello.dao.dbModel;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "assignments")
public class AssignmentDBModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
	@Column(name = "deadline")
	private LocalDateTime deadline;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "lab_id")
	private LaboratoryDB laboratory;
	
	public AssignmentDBModel() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LaboratoryDB getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(LaboratoryDB laboratory) {
		this.laboratory = laboratory;
	}

}
