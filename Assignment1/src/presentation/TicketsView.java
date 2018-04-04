package presentation;

import java.util.ArrayList;
import java.util.List;

import business.model.ShowModel;
import business.model.TicketModel;
import business.services.IShowService;
import business.services.ITicketService;
import business.services.TicketService;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TicketsView {
	
	private ITicketService ticketService;
	private ShowModel showModel;
	private IShowService showService;
	@SuppressWarnings("rawtypes")
	private TableView tableView;
	
	public TicketsView(IShowService showService, ShowModel showModel) {
		this.ticketService = new TicketService();
		this.showModel = showModel;
		this.showService = showService;
		display();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Select a ticket");
		
		List<String> notDisplay = new ArrayList<String>();
	    notDisplay.add("showId");
	    //notDisplay.add("id");
		
		
		Button editBtn = new Button("Edit Ticket");
		editBtn.setOnAction(e -> {
			ObservableList<ObservableList> selectedTicket;
			selectedTicket = tableView.getSelectionModel().getSelectedItems();
			TicketModel ticketModel = convertRowToModel(selectedTicket);
			new EditTicketView(ticketService, ticketModel, showModel.getId());
			window.close();
		});
		
		Button cancelBtn = new Button("Cancel Ticket");
		cancelBtn.setOnAction(e -> {
			ObservableList<ObservableList> selectedTicket;
			selectedTicket = tableView.getSelectionModel().getSelectedItems();
			TicketModel ticketModel = convertRowToModel(selectedTicket);
			ticketService.deleteTicket(ticketModel);
			showService.incrementTickets(showModel);
			window.close();
		});
		
		tableView = new TableView<>();
		initData(notDisplay);
		
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.setSpacing(15);
		vbox.getChildren().addAll(editBtn, cancelBtn);
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setSpacing(15);
		hbox.getChildren().addAll(vbox,tableView);
		
		Scene scene = new Scene(hbox,400,450);
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
		List<TicketModel> tickets = ticketService.findTicketsForShow(showModel.getId());
	    GenericTableView.createColumns(tableView,tickets,notDisplay);
        GenericTableView.createRows(tableView, tickets, notDisplay);
	}
	
	private TicketModel convertRowToModel(@SuppressWarnings("rawtypes") ObservableList<ObservableList> list) {
		TicketModel ticketModel = new TicketModel();
		ticketModel.setId(Integer.parseInt((String)list.get(0).get(0)));
		ticketModel.setSeatRow(Integer.parseInt((String)list.get(0).get(1)));
		ticketModel.setSeatCol(Integer.parseInt((String)list.get(0).get(2)));
		ticketModel.setShowId(showModel.getId());
		return ticketModel;
	}

}
