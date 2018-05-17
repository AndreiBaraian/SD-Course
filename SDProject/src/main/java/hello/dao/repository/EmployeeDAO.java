package hello.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.EmployeeDB;

public interface EmployeeDAO extends JpaRepository<EmployeeDB,Integer>{

}
