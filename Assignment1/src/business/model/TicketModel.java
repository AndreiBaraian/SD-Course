package business.model;

public class TicketModel {
	
	private int id;
	private int seatRow;
	private int seatCol;
	private int showId;
	
	public TicketModel() {}
	
	public TicketModel(int id, int seatRow, int seatCol, int showId) {
		this.id = id;
		this.seatRow = seatRow;
		this.seatCol = seatCol;
		this.showId = showId;
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

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

}
