package presentation;

import business.services.IShowService;
import exceptions.InsertException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddShowView {
	
	private IShowService showService;
	
	public AddShowView(IShowService showService) {
		this.showService = showService;
		display();
	}
	
	public void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Add a new show");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(10);
		grid.setHgap(10);
		
		Label genreLabel = new Label("Genre");
		GridPane.setConstraints(genreLabel, 0, 0);
		
		Label titleLabel = new Label("Title");
		GridPane.setConstraints(titleLabel, 0, 1);
		
		Label distributionListLabel = new Label("Distribution");
		GridPane.setConstraints(distributionListLabel, 0, 2);
		
		Label dateOfShowLabel = new Label("Date");
		GridPane.setConstraints(dateOfShowLabel, 0, 3);
		
		Label numberOfTicketsLabel = new Label("Tickets");
		GridPane.setConstraints(numberOfTicketsLabel, 0, 4);
		
		TextField genreInput = new TextField();
		GridPane.setConstraints(genreInput, 1, 0);
		
		TextField titleInput = new TextField();
		GridPane.setConstraints(titleInput, 1, 1);
		
		TextField distributionInput = new TextField();
		GridPane.setConstraints(distributionInput, 1, 2);
		
		TextField dateInput = new TextField();
		GridPane.setConstraints(dateInput, 1, 3);
		
		TextField ticketsInput = new TextField();
		GridPane.setConstraints(ticketsInput, 1, 4);
		
		Button submitBtn = new Button("Submit");
		GridPane.setConstraints(submitBtn, 0, 5);
		
		submitBtn.setOnAction(e -> {
			try {
				showService.addShow(genreInput.getText(), titleInput.getText(), distributionInput.getText(), dateInput.getText(), Integer.parseInt(ticketsInput.getText()));
				window.close();
			} catch (InsertException e1) {
				AlertBox.display("Error", e1.getMessage());
			}
		});
		
		grid.getChildren().addAll(submitBtn, genreLabel, titleLabel, distributionListLabel, dateOfShowLabel, numberOfTicketsLabel, genreInput, titleInput, distributionInput, dateInput, ticketsInput);
		Scene scene = new Scene(grid,300,250);
		window.setScene(scene);
		window.showAndWait();
	}

}
