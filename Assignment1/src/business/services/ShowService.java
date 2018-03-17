package business.services;

import repository.AbstractRepo;
import repository.ShowRepo;
import repository.dbmodel.Show;

public class ShowService {
	
	private AbstractRepo<Show> showRepo;
	
	public ShowService() {
		this.showRepo = new ShowRepo();
	}
	
	public void addShow(Show show) {
		showRepo.insert(show);
	}

}
