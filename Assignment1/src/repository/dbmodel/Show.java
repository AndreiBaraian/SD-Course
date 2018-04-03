package repository.dbmodel;

public class Show {
	
	private int id;
	private String genre;
	private String title;
	private String distributionList;
	private String dateOfShow;
	private int numberOfTickets;
	private int remainingTickets;
	
	public Show() {}
	
	public Show(String genre, String title, String distributionList, String dateOfShow, int numberOfTickets) {
		this.genre = genre;
		this.title = title;
		this.distributionList = distributionList;
		this.dateOfShow = dateOfShow;
		this.numberOfTickets = numberOfTickets;
		this.setRemainingTickets(numberOfTickets); 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDistributionList() {
		return distributionList;
	}

	public void setDistributionList(String distributionList) {
		this.distributionList = distributionList;
	}

	public String getDateOfShow() {
		return dateOfShow;
	}

	public void setDateOfShow(String dateOfShow) {
		this.dateOfShow = dateOfShow;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public int getRemainingTickets() {
		return remainingTickets;
	}

	public void setRemainingTickets(int remainingTickets) {
		this.remainingTickets = remainingTickets;
	}
	
}
