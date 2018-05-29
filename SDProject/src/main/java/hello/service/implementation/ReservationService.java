package hello.service.implementation;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.ActivityDB;
import hello.dao.dbModel.CustomerDB;
import hello.dao.dbModel.ReservationDB;
import hello.dao.repository.ActivityDAO;
import hello.dao.repository.CustomerDAO;
import hello.dao.repository.ReservationDAO;
import hello.service.bllmodel.ReservationBModel;
import hello.service.interfaces.IReservationService;

@Service
public class ReservationService implements IReservationService {

	@Autowired
	private ReservationDAO reservationDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private ActivityDAO activityDAO;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<ReservationBModel> getAllReservations() {
		List<ReservationDB> list = reservationDAO.findAll();
		List<ReservationBModel> resultList = list.parallelStream().map(x -> mapper.map(x, ReservationBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public ReservationBModel getReservationById(int id) {
		Optional<ReservationDB> reservationDB = reservationDAO.findById(id);
		if(!reservationDB.isPresent())
			return null;
		ReservationBModel reservation = mapper.map(reservationDB.get(), ReservationBModel.class);
		return reservation;
		
	}

	@Override
	public boolean addReservation(int customerId, int activityId, ReservationBModel reservation) {
		ReservationDB reservationDB = mapper.map(reservation, ReservationDB.class);
		reservationDB.setReferenceNumber(getNextReference());
		if(reservationDAO.findByReferenceNumber(reservation.getReferenceNumber()) != null)
			return false;
		Optional<CustomerDB> customerDB = customerDAO.findById(customerId);
		Optional<ActivityDB> activityDB = activityDAO.findById(activityId);
		activityDB.get().setAvailableSpots(activityDB.get().getAvailableSpots() - 1);
		activityDAO.save(activityDB.get());
		customerDB.get().setBalance((customerDB.get().getBalance() - (int)reservation.getDeposit()));
		reservationDB.setActivity(activityDB.get());
		reservationDB.setCustomer(customerDB.get());
		reservationDAO.save(reservationDB);
		return true;
	}
	
	private String getNextReference() {
		List<ReservationDB> allReservations = reservationDAO.findAll();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		StringBuilder newSysReference = new StringBuilder();
		newSysReference.append("RES");
		newSysReference.append("-");
		newSysReference.append(LocalDateTime.now().format(formatter));
		newSysReference.append("-");
		if(allReservations.isEmpty()) {
			newSysReference.append("00000000");
		}
		else {
			Comparator<String> sysReferenceComparator = new Comparator<String>() {
				@Override
				public int compare(String ref1, String ref2) {
					Long refNo1 = Long.parseLong(ref1.substring(15));
					Long refNo2 = Long.parseLong(ref2.substring(15));
					return refNo1.compareTo(refNo2);
				}
			};
			String currentSysReference = allReservations.parallelStream().map(p->p.getReferenceNumber()).max(sysReferenceComparator).get();
			Long currentNumber = Long.parseLong(currentSysReference.substring(15));
			currentNumber++;
			DecimalFormat myFormatter = new DecimalFormat("00000000");
			newSysReference.append(myFormatter.format(currentNumber));
		}
		return newSysReference.toString();
	}

	@Override
	public boolean updateReservation(int id, ReservationBModel reservation) {
		Optional<ReservationDB> reservationDB = reservationDAO.findById(id);
		if(reservationDB.isPresent()) {
			ReservationDB resDB = reservationDB.get();
			resDB.setDeposit(reservation.getDeposit());
			resDB.setReferenceNumber(reservation.getReferenceNumber());
			reservationDAO.save(resDB);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteReservation(int id) {
		Optional<ReservationDB> reservationDB = reservationDAO.findById(id);
		if(reservationDB.isPresent()) {
			reservationDAO.delete(reservationDB.get());
			return true;
		}
		return false;
	}
	
	
	
}
