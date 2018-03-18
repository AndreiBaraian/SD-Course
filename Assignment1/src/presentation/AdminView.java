package presentation;

import business.services.AdminService;
import business.services.CashierService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdminView {
	
	private Stage window;
	private AdminService adminService;
	private CashierService cashierService;
	
	public AdminView(Stage window) {
		this.window = window;
		this.adminService = new AdminService();
		this.cashierService = new CashierService();
		initialize();
	}
	
	private void initialize() {
		window.setTitle("Admin View");
		
		Label label = new Label("Helllo");
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(10);
		grid.setHgap(10);
		GridPane.setConstraints(label, 0, 0);
		
		Button addButton = new Button("Add Cashier");
		GridPane.setConstraints(addButton, 0, 1);
		addButton.setOnAction(e -> {
			RegisterView registerView = new RegisterView(cashierService);
		});
		
		grid.getChildren().addAll(label, addButton);
		
		Scene scene = new Scene(grid,500,450);
		window.setScene(scene);
		window.show();
	}

}
