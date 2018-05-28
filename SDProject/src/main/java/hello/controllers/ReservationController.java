package hello.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.apimodel.ReservationAPIModel;
import hello.service.bllmodel.ReservationBModel;
import hello.service.interfaces.IReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private IReservationService reservationService;
	
	@Autowired
	private ModelMapper mapper;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ReservationAPIModel>> getAllReservations() {
		List<ReservationBModel> list = reservationService.getAllReservations();
		List<ReservationAPIModel> resultList = list.parallelStream().map(x -> mapper.map(x, ReservationAPIModel.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{reservationId}")
	public ResponseEntity<ReservationAPIModel> getReservationById(@PathVariable("reservationId") int reservationId){
		ReservationBModel reservationB = reservationService.getReservationById(reservationId);
		if(reservationB == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		ReservationAPIModel reservation = mapper.map(reservationB, ReservationAPIModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(reservation);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ReservationAPIModel> addReservation(@RequestParam int customerId, @RequestParam int activityId, @RequestBody ReservationAPIModel reservation){
		if(reservationService.addReservation(customerId, activityId, mapper.map(reservation, ReservationBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(reservation);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{reservationId}")
	public ResponseEntity<ReservationAPIModel> updateReservation(@PathVariable("reservationId") int reservationId,@RequestBody ReservationAPIModel reservation){
		if(reservationService.updateReservation(reservationId, mapper.map(reservation, ReservationBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(reservation);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{reservationId}")
	public ResponseEntity<ReservationAPIModel> deleteReservation(@PathVariable("reservationId") int reservationId){
		if(reservationService.deleteReservation(reservationId))
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}
