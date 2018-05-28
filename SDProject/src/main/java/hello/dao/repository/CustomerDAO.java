package hello.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.CustomerDB;

public interface CustomerDAO extends JpaRepository<CustomerDB,Integer> {
	
	public CustomerDB findByUsername(String username);

}
