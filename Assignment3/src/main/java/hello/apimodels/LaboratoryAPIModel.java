package hello.apimodels;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class LaboratoryAPIModel implements Serializable{
	
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	//@JsonIgnore
	private int id;
	private int labNumber;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime date;
	private String title;
	private String curricula;
	private String description;
	
	public LaboratoryAPIModel() {}
	
	public LaboratoryAPIModel(int labNumber, LocalDateTime date, String title, String curricula, String description) {
		this.labNumber = labNumber;
		this.date = date;
		this.title = title;
		this.curricula = curricula;
		this.description = description;
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

	@Override
	public String toString() {
		return "LaboratoryAPIModel [id=" + id + ", labNumber=" + labNumber + ", date=" + date + ", title=" + title
				+ ", curricula=" + curricula + ", description=" + description + "]";
	}

	/*
	public void setDate(String date) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.date = LocalDateTime.parse(date, formatter);
	}*/
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
