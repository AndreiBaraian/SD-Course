package business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import business.model.ShowModel;
import business.model.TicketModel;
import main.SeatMap;
import repository.AbstractRepo;
import repository.TicketRepo;
import repository.dbmodel.Ticket;

public class TicketService {
	
	private AbstractRepo<Ticket> ticketRepo;
	private ModelMapper modelMapper;
	
	public TicketService() {
		this.ticketRepo = new TicketRepo();
		this.modelMapper = new ModelMapper();
	}
	
	public List<TicketModel> findAll() {
		List<Ticket> list = ticketRepo.findAll();
		List<TicketModel> resList = list.parallelStream().map(r -> modelMapper.map(r, TicketModel.class)).collect(Collectors.toList());
		return resList;
	}
	
	public List<TicketModel> findTicketsForShow(int showId) {
		List<Ticket> list = ticketRepo.findAll();
		List<TicketModel> resList = list.parallelStream().map(r -> modelMapper.map(r, TicketModel.class)).collect(Collectors.toList()).parallelStream().filter(r -> r.getShowId() == showId).collect(Collectors.toList());
		return resList;
	}
	
	public List<TicketModel> findAvailableTickets(ShowModel showModel) {
		List<TicketModel> takenTickets = findTicketsForShow(showModel.getId());
		List<TicketModel> allTickets = new ArrayList<TicketModel>();
		for(int i=0;i<SeatMap.maxRow;i++)
			for(int j=0;j<SeatMap.maxCol;j++)
				allTickets.add(new TicketModel(0,i,j,showModel.getId()));
		List<TicketModel> availableTickets = new ArrayList<TicketModel>();
		for(TicketModel t : allTickets) {
			boolean ok = false;
			for(TicketModel r : takenTickets)
				if(r.getSeatRow() == t.getSeatRow() && t.getSeatCol() == r.getSeatCol())
					ok = true;
			if(!ok)
				availableTickets.add(t);
		}
		return availableTickets;
	}
	
	public void sellTicket(TicketModel ticketModel) {
		Ticket ticketDB = modelMapper.map(ticketModel, Ticket.class);
		ticketRepo.insert(ticketDB);
	}
	
	public void deleteTicket(TicketModel ticketModel) {
		Ticket ticketDB = ticketRepo.findById(ticketModel.getId());
		ticketRepo.delete(ticketDB);
	}

}
