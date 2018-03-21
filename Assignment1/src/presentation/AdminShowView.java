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

public class AdminShowView {
	
	private Stage window;
	@SuppressWarnings("rawtypes")
	private TableView tableView;
	private ShowService showService;
	
	public AdminShowView(Stage window) {
		this.window = window;
		this.showService = new ShowService();
		display();
	}
	
	private void display() {
		window.setTitle("View of shows");
		
		List<String> notDisplay = new ArrayList<String>();
	    notDisplay.add("password");
		
	    
		Button addButton = new Button("Add show");
		addButton.setOnAction(e -> {
			new AddShowView(showService);
			loadData(notDisplay);
		});
		
		
		/*
		Button updateButton = new Button("Update show");
		updateButton.setOnAction(e -> {
			ObservableList<ObservableList> selectedCashier;
			selectedCashier = tableView.getSelectionModel().getSelectedItems();
			CashierModel cashierModel = convertRowToModel(selectedCashier);
			new UpdateView(cashierService,cashierModel);
			loadData(notDisplay);
		});
		
		Button deleteButton = new Button("Delete show");
		deleteButton.setOnAction(e -> {
			ObservableList<ObservableList> selectedCashier;
			selectedCashier = tableView.getSelectionModel().getSelectedItems();
			CashierModel cashierModel = convertRowToModel(selectedCashier);
			cashierService.deleteCashier(cashierModel);
			loadData(notDisplay);
		});
		
		*/
		
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> {
			new AdminMenuView(window);
		});
		
		Button logoutButton = new Button("Logout");
		logoutButton.setOnAction(e -> {
			new LoginView(window);
		});
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10,10,10,10));
		vBox.setSpacing(15);
		//vBox.getChildren().addAll(addButton, updateButton, deleteButton, backButton, logoutButton);
		vBox.getChildren().addAll(addButton, backButton, logoutButton);
		
        tableView = new TableView<>();
		
		HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(vBox,tableView);
        
        initData(notDisplay);
		
		Scene scene = new Scene(hBox,700,450);
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
		/*
		cashierModel.setId(Integer.parseInt((String)list.get(0).get(0)));
		cashierModel.setFirstName((String)list.get(0).get(2));
		cashierModel.setLastName((String)list.get(0).get(3));
		cashierModel.setUsername((String)list.get(0).get(1));
		*/
		return showModel;
	}

}
