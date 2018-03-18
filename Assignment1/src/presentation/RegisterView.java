package presentation;

import business.services.CashierService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RegisterView {
	
	private CashierService cashierService;
	
	public RegisterView(CashierService cashierService) {
		this.cashierService = cashierService;
		display();
	}
	
	public void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Register new cashier");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(10);
		grid.setHgap(10);
		
		Label firstNameLabel = new Label("First Name");
		GridPane.setConstraints(firstNameLabel, 0, 0);
		
		Label lastNameLabel = new Label("Last Name");
		GridPane.setConstraints(lastNameLabel, 0, 1);
		
		Label usernameLabel = new Label("Username");
		GridPane.setConstraints(usernameLabel, 0, 2);
		
		Label passLabel = new Label("Password");
		GridPane.setConstraints(passLabel, 0, 3);
		
		TextField firstNameInput = new TextField();
		GridPane.setConstraints(firstNameInput, 1, 0);
		
		TextField lastNameInput = new TextField();
		GridPane.setConstraints(lastNameInput, 1, 1);
		
		TextField usernameInput = new TextField();
		GridPane.setConstraints(usernameInput, 1, 2);
		
		TextField passInput = new TextField();
		GridPane.setConstraints(passInput, 1, 3);
		
		Button submitBtn = new Button("Submit");
		GridPane.setConstraints(submitBtn, 0, 4);
		
		submitBtn.setOnAction(e -> {
			cashierService.addCashier(firstNameInput.getText(), lastNameInput.getText(), usernameInput.getText(), passInput.getText());
			window.close();
		});
		
		grid.getChildren().addAll(submitBtn, firstNameInput, lastNameInput, usernameInput, passInput, firstNameLabel, lastNameLabel, usernameLabel, passLabel);
		Scene scene = new Scene(grid,300,250);
		window.setScene(scene);
		window.showAndWait();
	}

}
