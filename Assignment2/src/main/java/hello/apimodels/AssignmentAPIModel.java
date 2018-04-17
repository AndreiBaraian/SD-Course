package hello.apimodels;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import hello.service.bllmodel.LaboratoryBModel;

public class AssignmentAPIModel {
	
	private int id;
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime deadline;
	private String description;

	private LaboratoryBModel laboratory;
	
	public AssignmentAPIModel() {}

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

	public LaboratoryBModel getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(LaboratoryBModel laboratory) {
		this.laboratory = laboratory;
	}
	

}
