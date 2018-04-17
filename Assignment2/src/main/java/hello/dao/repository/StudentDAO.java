package hello.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.StudentDB;

public interface StudentDAO extends JpaRepository<StudentDB,Integer> {
	
	StudentDB findByEmail(String email);

}
