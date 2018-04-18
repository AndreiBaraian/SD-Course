package hello.service.bllmodel;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AssignmentBModel {
	
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
	private LocalDateTime deadline;
	private String description;
	
	public AssignmentBModel() {}

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
	
}
