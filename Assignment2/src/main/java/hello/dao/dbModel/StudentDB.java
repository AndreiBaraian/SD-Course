package hello.dao.dbModel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@DiscriminatorValue("student")
public class StudentDB extends UserDB {

	@Column(name = "group_id")
	private String group;
	
	@Column(name = "hobby")
	private String hobby;
	
	@Column(name = "token")
	private String token;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy="student")
	private Set<AttendanceDB> attendances;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy="student")
	private Set<SubmissionDB> submissions;
	
	public StudentDB() {}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Set<AttendanceDB> getAttendances() {
		return attendances;
	}

	public void setAttendances(Set<AttendanceDB> attendances) {
		this.attendances = attendances;
	}

	public Set<SubmissionDB> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(Set<SubmissionDB> submissions) {
		this.submissions = submissions;
	}
	
}
