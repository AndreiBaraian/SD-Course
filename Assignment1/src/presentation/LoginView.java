package presentation;

import business.services.AdminService;
import business.services.CashierService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginView {
	
	private Stage window;
	private AdminService adminService;
	private CashierService cashierService;
	
	public LoginView(Stage window) {
		this.window = window;
		this.adminService = new AdminService();
		this.cashierService = new CashierService();
		display();
	}
	
	public void display() {
		window.setTitle("Login");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label nameLabel = new Label("Username");
		GridPane.setConstraints(nameLabel,0,0);
		
		TextField nameInput = new TextField();
		GridPane.setConstraints(nameInput, 1, 0);
		
		Label passLabel = new Label("Password");
		GridPane.setConstraints(passLabel,0,1);
		
		PasswordField passInput = new PasswordField();
		GridPane.setConstraints(passInput, 1, 1);
		
		Button loginButton = new Button("Login");
		GridPane.setConstraints(loginButton, 1, 2);
		loginButton.setOnAction(e -> {
			if(adminService.checkPassword(nameInput.getText(), passInput.getText())) {
					new AdminMenuView(window);
			}
			else if(cashierService.checkPassword(nameInput.getText(), passInput.getText())) {
				new CashierMenuView(window);
			}
			else {
				AlertBox.display("Login failed","Wrong username or password!");
			}
		});
		
		grid.getChildren().addAll(nameLabel,nameInput,passLabel,passInput, loginButton);
		
		Scene scence = new Scene(grid,300,250);
		window.setScene(scence);
		window.show();
		
	}

}
