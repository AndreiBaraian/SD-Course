package presentation;

import java.util.ArrayList;
import java.util.List;

import business.model.CashierModel;
import business.model.ShowModel;
import business.services.ShowService;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CashierMenuView {
	
	private Stage window;
	private ShowService showService;
	@SuppressWarnings("rawtypes")
	private TableView tableView;
	
	public CashierMenuView(Stage window) {
		this.window = window;
		this.showService = new ShowService();
		initialize();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		window.setTitle("View of shows");
		
		List<String> notDisplay = new ArrayList<String>();
	    notDisplay.add("");
		
	    
		Button sellButton = new Button("Sell Ticket");
		sellButton.setOnAction(e -> {
			ObservableList<ObservableList> selectedShow;
			selectedShow = tableView.getSelectionModel().getSelectedItems();
			ShowModel showModel = convertRowToModel(selectedShow);
			new AvailableTicketView(showService, showModel);
			loadData(notDisplay);
		});
		
		
		
		Button seeTicketsBtn = new Button("See tickets");
		seeTicketsBtn.setOnAction(e -> {
			ObservableList<ObservableList> selectedShow;
			selectedShow = tableView.getSelectionModel().getSelectedItems();
			ShowModel showModel = convertRowToModel(selectedShow);
			new TicketsView(showService,showModel);
			loadData(notDisplay);
		});
		
		/*
		
		Button deleteButton = new Button("Delete cashier");
		deleteButton.setOnAction(e -> {
			ObservableList<ObservableList> selectedCashier;
			selectedCashier = tableView.getSelectionModel().getSelectedItems();
			CashierModel cashierModel = convertRowToModel(selectedCashier);
			cashierService.deleteCashier(cashierModel);
			loadData(notDisplay);
		});
		
		*/
		
		Button logoutButton = new Button("Logout");
		logoutButton.setOnAction(e -> {
			new LoginView(window);
		});
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10,10,10,10));
		vBox.setSpacing(15);
		vBox.getChildren().addAll(sellButton, seeTicketsBtn, logoutButton);
		
        tableView = new TableView<>();
		
		HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(vBox,tableView);
        
        initData(notDisplay);
		
		Scene scene = new Scene(hBox,800,450);
		window.setScene(scene);
		window.show();
	}
	
	private void loadData(List<String> notDisplay) {
		List<ShowModel> shows = showService.findAll();
        GenericTableView.createRows(tableView, shows, notDisplay);
	}
	
	private void initData(List<String> notDisplay) {
		List<ShowModel> shows = showService.findAll();
	    GenericTableView.createColumns(tableView,shows,notDisplay);
        GenericTableView.createRows(tableView, shows, notDisplay);
	}
	
	private ShowModel convertRowToModel(@SuppressWarnings("rawtypes") ObservableList<ObservableList> list) {
		ShowModel showModel = new ShowModel();
		showModel.setId(Integer.parseInt((String)list.get(0).get(0)));
		showModel.setGenre((String)list.get(0).get(1));
		showModel.setTitle((String)list.get(0).get(2));
		showModel.setDistributionList((String)list.get(0).get(3));
		showModel.setDateOfShow((String)list.get(0).get(4));
		showModel.setNumberOfTickets(Integer.parseInt((String)list.get(0).get(5)));
		showModel.setRemainingTickets(Integer.parseInt((String)list.get(0).get(6)));
		return showModel;
	}

}
