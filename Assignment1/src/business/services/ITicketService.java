package business.services;

import java.util.List;

import business.model.ShowModel;
import business.model.TicketModel;

public interface ITicketService {
	
	public List<TicketModel> findAll();
	
	public List<TicketModel> findTicketsForShow(int showId);
	
	public List<TicketModel> findAvailableTickets(ShowModel showModel);
	
	public void sellTicket(TicketModel ticketModel);
	
	public void deleteTicket(TicketModel ticketModel);
	
	public void updateTicket(TicketModel ticketModel);

}
