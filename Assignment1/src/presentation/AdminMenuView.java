package presentation;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminMenuView {
	
	private Stage window;
	
	public AdminMenuView(Stage window) {
		this.window = window;
		display();
	}
	
	public void display() {
		window.setTitle("Menu");
		
		Button cashierButton = new Button("Cashiers");
		cashierButton.setOnAction(e -> {
			new AdminCashierView(window);
		});
		
		Button showButton = new Button("Shows");
		showButton.setOnAction(e -> {
			new AdminShowView(window);
		});
		
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> {
			new LoginView(window);
		});
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.setSpacing(20);
		vbox.getChildren().addAll(cashierButton, showButton, backButton);
		
		Scene scene = new Scene(vbox,500,450);
		window.setScene(scene);
		window.show();
	}

}
