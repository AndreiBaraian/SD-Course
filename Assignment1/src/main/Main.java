package main;

import model.Show;
import services.ShowService;

public class Main {
	
	public static void main(String[] args){
		
		Show show1 = new Show("Ballet", "Swan Lake", "Ionut Caras, Victor Rebengiuc", "18/12/2017", 340);
		ShowService showService = new ShowService();
		showService.addShow(show1);
		
	}

}
