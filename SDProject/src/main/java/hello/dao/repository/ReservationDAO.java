package hello.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.ReservationDB;

public interface ReservationDAO extends JpaRepository<ReservationDB,Integer> {
	
	public ReservationDB findByReferenceNumber(String referenceNumber);

}
