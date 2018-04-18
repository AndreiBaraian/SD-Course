package hello.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.AssignmentDB;

public interface AssignmentDAO extends JpaRepository<AssignmentDB,Integer> {

	AssignmentDB findByName(String name); 
	
}
