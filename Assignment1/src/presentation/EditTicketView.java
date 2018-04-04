package presentation;

import java.util.stream.Collectors;

import business.model.TicketModel;
import business.services.ITicketService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditTicketView {
	
	private ITicketService ticketService;
	private TicketModel ticketModel;
	private int showId;
	
	public EditTicketView(ITicketService ticketService, TicketModel ticketModel, int showId) {
		this.ticketService = ticketService;
		this.ticketModel = ticketModel;
		this.showId = showId;
		display();
	}
	
	public void display() {
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Update a new show");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(10);
		grid.setHgap(10);
		
		Label rowLabel = new Label("Row");
		GridPane.setConstraints(rowLabel, 0, 0);
		
		Label colLabel = new Label("Column");
		GridPane.setConstraints(colLabel, 0, 1);
		
		TextField rowInput = new TextField(Integer.toString(ticketModel.getSeatRow()));
		GridPane.setConstraints(rowInput, 1, 0);
		
		TextField colInput = new TextField(Integer.toString(ticketModel.getSeatCol()));
		GridPane.setConstraints(colInput, 1, 1);
		
		Button submitBtn = new Button("Submit");
		GridPane.setConstraints(submitBtn, 0, 5);
		
		submitBtn.setOnAction(e -> {
				updateTicket(Integer.parseInt(rowInput.getText()),Integer.parseInt(colInput.getText()));
				if(ticketService.findTicketsForShow(showId).parallelStream().filter(x -> x.getSeatRow() == ticketModel.getSeatRow() && x.getSeatCol() == ticketModel.getSeatCol()).collect(Collectors.toList()).isEmpty()) {
					ticketService.updateTicket(ticketModel);
					window.close();
				}
				else
					AlertBox.display("Error", "The seat is already occupied");
		});
		
		grid.getChildren().addAll(submitBtn, rowLabel, colLabel, rowInput, colInput);
		Scene scene = new Scene(grid,300,250);
		window.setScene(scene);
		window.showAndWait();
		
	}
		
	private void updateTicket(int seatRow, int seatCol) {
		ticketModel.setSeatRow(seatRow);
		ticketModel.setSeatCol(seatCol);
	}

}
