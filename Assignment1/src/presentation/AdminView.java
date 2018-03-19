package presentation;

import java.util.ArrayList;
import java.util.List;

import business.model.CashierModel;
import business.services.AdminService;
import business.services.CashierService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AdminView {
	
	private Stage window;
	private AdminService adminService;
	private CashierService cashierService;
	@SuppressWarnings("rawtypes")
	private TableView tableView;
	
	public AdminView(Stage window) {
		this.window = window;
		this.adminService = new AdminService();
		this.cashierService = new CashierService();
		initialize();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		window.setTitle("Admin View");
		
		List<String> notDisplay = new ArrayList<String>();
	    notDisplay.add("password");
		
		Button addButton = new Button("Add Cashier");
		addButton.setOnAction(e -> {
			RegisterView registerView = new RegisterView(cashierService);
			GenericTableView.createRows(tableView, cashierService.findAll(), notDisplay);
		});
		
		Button updateButton = new Button("Update cashier");
		updateButton.setOnAction(e -> {
			ObservableList<ObservableList> selectedCashier;
			selectedCashier = tableView.getSelectionModel().getSelectedItems();
			CashierModel cashierModel = convertRowToModel(selectedCashier);
			UpdateView updateView = new UpdateView(cashierService,cashierModel);
			GenericTableView.createRows(tableView, cashierService.findAll(), notDisplay);
		});
		
		Button deleteButton = new Button("Delete cashier");
		deleteButton.setOnAction(e -> {
			ObservableList<ObservableList> selectedCashier;
			selectedCashier = tableView.getSelectionModel().getSelectedItems();
			CashierModel cashierModel = convertRowToModel(selectedCashier);
			cashierService.deleteCashier(cashierModel);
			GenericTableView.createRows(tableView, cashierService.findAll(), notDisplay);
		});
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10,10,10,10));
		vBox.setSpacing(15);
		vBox.getChildren().addAll(addButton, updateButton, deleteButton);
		
        tableView = new TableView<>();
		
		HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(vBox,tableView);
        
        GenericTableView.createColumns(tableView,cashierService.findAll(),notDisplay);
        
        GenericTableView.createRows(tableView, cashierService.findAll(), notDisplay);
		
		Scene scene = new Scene(hBox,500,450);
		window.setScene(scene);
		window.show();
	}
	
	private CashierModel convertRowToModel(ObservableList<ObservableList> list) {
		CashierModel cashierModel = new CashierModel();
		cashierModel.setId(Integer.parseInt((String)list.get(0).get(0)));
		cashierModel.setFirstName((String)list.get(0).get(2));
		cashierModel.setLastName((String)list.get(0).get(3));
		cashierModel.setUsername((String)list.get(0).get(1));
		return cashierModel;
	}
	

}
