package hello.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.ReservationDB;

public interface ReservationDAO extends JpaRepository<ReservationDB,Integer> {
	
	public ReservationDB findByReferenceNumber(String referenceNumber);
	public List<ReservationDB> findByActivityId(int activityId);
	public List<ReservationDB> findByCustomerId(int customerId);

}
