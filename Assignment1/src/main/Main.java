package main;

import java.security.NoSuchAlgorithmException;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import presentation.LoginView;

public class Main extends Application {
	
	Stage window;
	Button button;
	
	public static void main(String[] args) throws NoSuchAlgorithmException{
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		LoginView loginView = new LoginView(primaryStage);
		loginView.display();
		
	}

}
