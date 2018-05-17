package hello.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.ProfileDB;

public interface ProfileDAO extends JpaRepository<ProfileDB,Integer>{

}
