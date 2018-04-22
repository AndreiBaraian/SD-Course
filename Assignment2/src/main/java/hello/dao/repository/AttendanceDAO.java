package hello.dao.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.dao.dbModel.AttendanceDB;

@Repository
@Transactional
public interface AttendanceDAO extends JpaRepository<AttendanceDB,Integer> {

	public List<AttendanceDB> findAttendanceByLaboratoryId(int labId);
	
}
