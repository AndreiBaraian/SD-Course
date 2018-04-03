package presentation;

import java.util.ArrayList;
import java.util.List;

import business.model.ShowModel;
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

public class AvailableTicketView {
	
	private TicketService ticketService;
	private ShowService showService;
	private ShowModel showModel;
	@SuppressWarnings("rawtypes")
	private TableView tableView;
	
	public AvailableTicketView(ShowService showService, ShowModel showModel) {
		this.ticketService = new TicketService();
		this.showService = showService;
		this.showModel = showModel;
		display();
	}
	
	@SuppressWarnings("unchecked")
	public void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Select a ticket");
		
		List<String> notDisplay = new ArrayList<String>();
	    notDisplay.add("showId");
	    notDisplay.add("id");
		
		
		Button submitBtn = new Button("Submit");
		submitBtn.setOnAction(e -> {
			ObservableList<ObservableList> selectedTicket;
			selectedTicket = tableView.getSelectionModel().getSelectedItems();
			TicketModel ticketModel = convertRowToModel(selectedTicket);
			ticketService.sellTicket(ticketModel);
			if(showService.decrementTickets(showModel))
				AlertBox.display("Tickets","The limit has not been exceeded");
			else
				AlertBox.display("Tickets","There are no more tickets for this show!");
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
	
	/*
	private void loadData(List<String> notDisplay) {
		List<TicketModel> tickets = ticketService.findAvailableTickets(showModel);
		System.out.println(tickets.size());
        GenericTableView.createRows(tableView, tickets, notDisplay);
	}
	*/
	
	private void initData(List<String> notDisplay) {
		List<TicketModel> tickets = ticketService.findAvailableTickets(showModel);
	    GenericTableView.createColumns(tableView,tickets,notDisplay);
        GenericTableView.createRows(tableView, tickets, notDisplay);
	}
	
	private TicketModel convertRowToModel(@SuppressWarnings("rawtypes") ObservableList<ObservableList> list) {
		TicketModel ticketModel = new TicketModel();
		ticketModel.setSeatRow(Integer.parseInt((String)list.get(0).get(0)));
		ticketModel.setSeatCol(Integer.parseInt((String)list.get(0).get(1)));
		ticketModel.setShowId(showModel.getId());
		return ticketModel;
	}
	

}
