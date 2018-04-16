package hello.dao.dbModel;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "laboratories")
public class LaboratoryDTO {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "labNumber")
	private int labNumber;
	
	@Column(name = "dateOfLab")
	private Timestamp date;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "curricula")
	private String curricula;
	
	public LaboratoryDTO() {}
	
	
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
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
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
	
}
