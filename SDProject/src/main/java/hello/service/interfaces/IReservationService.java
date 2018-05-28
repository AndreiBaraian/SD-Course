package hello.service.interfaces;

import java.util.List;

import hello.service.bllmodel.ReservationBModel;

public interface IReservationService {
	
	public List<ReservationBModel> getAllReservations();
	
	public ReservationBModel getReservationById(int id);
	
	public boolean addReservation(int customerId, int activityId, ReservationBModel reservation);
	
	public boolean updateReservation(int id, ReservationBModel reservation);
	
	public boolean deleteReservation(int id);

}
