package presentation;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AdminView {
	
	private Stage window;
	
	public AdminView(Stage window) {
		this.window = window;
		initialize();
	}
	
	private void initialize() {
		window.setTitle("Admin View");
		
		Label label = new Label("Helllo");
		StackPane layout = new StackPane();
		layout.getChildren().add(label);
		
		Scene scene = new Scene(layout,300,250);
		window.setScene(scene);
		window.show();
	}

}
