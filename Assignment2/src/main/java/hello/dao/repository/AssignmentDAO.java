package hello.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.AssignmentDBModel;

public interface AssignmentDAO extends JpaRepository<AssignmentDBModel,Integer> {

	AssignmentDBModel findByName(String name); 
	
}
