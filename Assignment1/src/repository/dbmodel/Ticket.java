package repository.dbmodel;

public class Ticket {
	
	private int id;
	private int seatRow;
	private int seatCol;
	private String showName;
	
	public Ticket() {}
	
	public Ticket(int id, int seatRow, int seatCol, String showName) {
		this.id = id;
		this.seatRow = seatRow;
		this.seatCol = seatCol;
		this.showName = showName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}

	public int getSeatCol() {
		return seatCol;
	}

	public void setSeatCol(int seatCol) {
		this.seatCol = seatCol;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

}
