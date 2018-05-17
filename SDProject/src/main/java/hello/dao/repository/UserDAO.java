package hello.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.UserDB;

public interface UserDAO extends JpaRepository<UserDB,Integer> {
	
	public UserDB findUserByEmail(String email);

}
