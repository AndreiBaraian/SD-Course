package presentation;

import java.util.ArrayList;
import java.util.List;

import business.model.CashierModel;
import business.services.CashierService;
import business.services.ICashierService;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminCashierView {
	
	private Stage window;
	private ICashierService cashierService;
	@SuppressWarnings("rawtypes")
	private TableView tableView;
	
	public AdminCashierView(Stage window) {
		this.window = window;
		this.cashierService = new CashierService();
		initialize();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		window.setTitle("View of cashiers");
		
		List<String> notDisplay = new ArrayList<String>();
	    notDisplay.add("password");
		
		Button addButton = new Button("Add Cashier");
		addButton.setOnAction(e -> {
			new RegisterView(cashierService);
			loadData(notDisplay);
		});
		
		Button updateButton = new Button("Update cashier");
		updateButton.setOnAction(e -> {
			ObservableList<ObservableList> selectedCashier;
			selectedCashier = tableView.getSelectionModel().getSelectedItems();
			CashierModel cashierModel = convertRowToModel(selectedCashier);
			new UpdateCashierView(cashierService,cashierModel);
			loadData(notDisplay);
		});
		
		Button deleteButton = new Button("Delete cashier");
		deleteButton.setOnAction(e -> {
			ObservableList<ObservableList> selectedCashier;
			selectedCashier = tableView.getSelectionModel().getSelectedItems();
			CashierModel cashierModel = convertRowToModel(selectedCashier);
			cashierService.deleteCashier(cashierModel);
			loadData(notDisplay);
		});
		
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
		vBox.getChildren().addAll(addButton, updateButton, deleteButton, backButton, logoutButton);
		
        tableView = new TableView<>();
		
		HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(vBox,tableView);
        
        initData(notDisplay);
		
		Scene scene = new Scene(hBox,500,450);
		window.setScene(scene);
		window.show();
	}
	
	private void loadData(List<String> notDisplay) {
		List<CashierModel> cashiers = cashierService.findAll();
        GenericTableView.createRows(tableView, cashiers, notDisplay);
	}
	
	private void initData(List<String> notDisplay) {
		List<CashierModel> cashiers = cashierService.findAll();
	    GenericTableView.createColumns(tableView,cashiers,notDisplay);
        GenericTableView.createRows(tableView, cashiers, notDisplay);
	}
	
	private CashierModel convertRowToModel(@SuppressWarnings("rawtypes") ObservableList<ObservableList> list) {
		CashierModel cashierModel = new CashierModel();
		cashierModel.setId(Integer.parseInt((String)list.get(0).get(0)));
		cashierModel.setFirstName((String)list.get(0).get(2));
		cashierModel.setLastName((String)list.get(0).get(3));
		cashierModel.setUsername((String)list.get(0).get(1));
		return cashierModel;
	}
	

}
