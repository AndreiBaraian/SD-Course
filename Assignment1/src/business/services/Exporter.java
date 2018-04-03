package business.services;

import java.util.List;

import business.model.TicketModel;

public interface Exporter {
	
	public void export(List<TicketModel> tickets);

}
