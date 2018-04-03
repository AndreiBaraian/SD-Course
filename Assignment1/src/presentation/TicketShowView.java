package presentation;

import java.util.ArrayList;
import java.util.List;

import business.model.TicketModel;
import business.services.ShowService;
import business.services.TicketService;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TicketShowView {
	
	private TicketService ticketService;
	private ShowService showService;
	@SuppressWarnings("rawtypes")
	private TableView tableView;
	
	public TicketShowView(ShowService showService) {
		this.ticketService = new TicketService();
		this.showService = showService;
		display();
	}
	
	public void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Select a ticket");
		
		List<String> notDisplay = new ArrayList<String>();
	    notDisplay.add("showId");
		
		
		Button submitBtn = new Button("Submit");
		submitBtn.setOnAction(e -> {
			//showService.addShow(genreInput.getText(), titleInput.getText(), distributionInput.getText(), dateInput.getText(), Integer.parseInt(ticketsInput.getText()));
			window.close();
		});
		
		tableView = new TableView<>();
		
		initData(notDisplay);
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.setSpacing(15);
		vbox.getChildren().addAll(tableView, submitBtn);
		
		Scene scene = new Scene(vbox,300,450);
		window.setScene(scene);
		window.showAndWait();
	}
	
	private void loadData(List<String> notDisplay) {
		List<TicketModel> tickets = ticketService.findAll();
        GenericTableView.createRows(tableView, tickets, notDisplay);
	}
	
	private void initData(List<String> notDisplay) {
		List<TicketModel> tickets = ticketService.findAll();
	    GenericTableView.createColumns(tableView,tickets,notDisplay);
        GenericTableView.createRows(tableView, tickets, notDisplay);
	}
	
	private TicketModel convertRowToModel(@SuppressWarnings("rawtypes") ObservableList<ObservableList> list) {
		TicketModel ticketModel = new TicketModel();
		ticketModel.setId(Integer.parseInt((String)list.get(0).get(0)));
		ticketModel.setSeatRow(Integer.parseInt((String)list.get(0).get(1)));
		ticketModel.setSeatCol(Integer.parseInt((String)list.get(0).get(2)));
		ticketModel.setShowId(Integer.parseInt((String)list.get(0).get(3)));
		return ticketModel;
	}

}
