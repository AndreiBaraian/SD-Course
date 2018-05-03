package hello.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.AssignmentDB;

public interface AssignmentDAO extends JpaRepository<AssignmentDB,Integer> {

	AssignmentDB findByName(String name); 
	List<AssignmentDB> findAssignmentsByLaboratoryId(int labId);
	
}
