package business.services;

import java.util.List;

import business.model.ShowModel;
import business.model.TicketModel;
import exceptions.InsertException;

public interface IShowService {
	
	public List<ShowModel> findAll();
	
	public void addShow(String genre, String title, String distribution, String date, int tickets) throws InsertException;
	
	public void updateShow(ShowModel showModel);
	
	public void deleteShow(ShowModel showModel);
	
	public boolean decrementTickets(ShowModel showModel);
	
	public void incrementTickets(ShowModel showModel);
	
	public void exportTickets(int showId, List<TicketModel> tickets);

}
