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
@Table(name = "activities")
public class ActivityDB {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "picture_link")
	private String pictureLink;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "max_persons")
	private int maxPersons;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
	@Column(name = "start_date")
	private LocalDateTime startDateActivity;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
	private Set<ReservationDB> reservations;
	
	public ActivityDB() {}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public LocalDateTime getStartDateActivity() {
		return startDateActivity;
	}

	public void setStartDateActivity(LocalDateTime startDateActivity) {
		this.startDateActivity = startDateActivity;
	}

	public int getMaxPersons() {
		return maxPersons;
	}

	public void setMaxPersons(int maxPersons) {
		this.maxPersons = maxPersons;
	}

	public Set<ReservationDB> getReservations() {
		return reservations;
	}

	public void setReservations(Set<ReservationDB> reservations) {
		this.reservations = reservations;
	}
	
}
