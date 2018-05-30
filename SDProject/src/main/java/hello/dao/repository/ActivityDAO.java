package hello.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.ActivityDB;

public interface ActivityDAO extends JpaRepository<ActivityDB,Integer>{
	
	public ActivityDB findByName(String name);
	public List<ActivityDB> findByEmployeeId(int employeeId);

}
