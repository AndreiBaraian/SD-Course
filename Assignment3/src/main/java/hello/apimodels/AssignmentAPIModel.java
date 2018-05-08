package hello.apimodels;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AssignmentAPIModel {
	
	private int id;
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime deadline;
	private String description;
	
	public AssignmentAPIModel() {}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "AssignmentAPIModel [id=" + id + ", name=" + name + ", deadline=" + deadline + ", description="
				+ description + "]";
	}

}
