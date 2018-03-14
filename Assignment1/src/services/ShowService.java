package services;

import model.Show;
import repository.ShowRepo;

public class ShowService {
	
	private ShowRepo showRepo;
	
	public ShowService() {
		this.showRepo = new ShowRepo();
	}
	
	public void addShow(Show show) {
		showRepo.insert(show);
	}

}
